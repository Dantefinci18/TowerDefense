package org.example.Modelo;

import org.example.Modelo.Arma.*;
import org.example.Modelo.Celda.Base;
import org.example.Modelo.Celda.Celda;
import org.example.Modelo.Celda.TipoCelda;
import org.example.Modelo.Celda.Torre;
import org.example.Modelo.Enemigo.*;
import org.example.Modelo.LogicaDeNiveles.NivelLoader;

import java.util.*;

public class TowerDefense {
    private final Posicion posicionBase;
    private final Posicion posicionSpawn;
    private final ArrayList<Posicion> camino;
    private final HashSet<Enemigo> enemigosVisibles;
    private ArrayDeque<TipoEnemigo> oleadaActual;
    private final ArrayDeque<ArrayDeque<TipoEnemigo>> oleadas;
    private final Celda[][] mapa;
    private final HashSet<Torre> torres;
    private int dinero;
    private long tiempoDespuesDelSpawn;
    private boolean victoria;
    private static final int DINERO_INICIAL = 100;
    public static final int FPS = 60;
    public static final long MS_PER_FRAME = 1000 / FPS;
    private static final long TIEMPO_SPAWN_MS = 10 * 1000;
    public static final int TAMANIO_CELDA = 96;
    private static final int MONEDAS_POR_MUERTE = 50;

    public TowerDefense(String nivel){
        NivelLoader loader= new NivelLoader(nivel);
        this.mapa = loader.getMapa();
        this.posicionSpawn = loader.getPosicionSpawn();
        this.posicionBase = loader.getPosicionBase();
        this.enemigosVisibles = new HashSet<>();
        this.oleadas = loader.getOleadas();
        this.oleadaActual = this.oleadas.poll();
        this.camino =new ArrayList<>(Arrays.asList(loader.getCamino()));
        this.torres = loader.getTorres();
        this.dinero = DINERO_INICIAL;
        this.tiempoDespuesDelSpawn = 0;
        this.victoria = false;
    }

    private Enemigo obtenerEnemigoSegunTipo(TipoEnemigo tipo, int fila,int columna){
        double x = columna*TAMANIO_CELDA;
        double y = fila*TAMANIO_CELDA;
        Enemigo enemigo = null;

        switch(tipo){
            case TANQUE -> enemigo = new EnemigoTanque(x,y,this.camino);
            case BUNKER -> enemigo = new EnemigoBunker(x,y,this.camino);
            case AVION -> enemigo = new EnemigoAvion(x,y,this.posicionBase);
        }

        return enemigo;
    }

    private void actualizarSpawn(){
        if(this.tiempoDespuesDelSpawn >= TIEMPO_SPAWN_MS){
            this.tiempoDespuesDelSpawn = 0;

            if(this.oleadaActual != null && !oleadaActual.isEmpty()){
                TipoEnemigo tipo = this.oleadaActual.poll();
                int fila = this.posicionSpawn.getFila();
                int columna = this.posicionSpawn.getColumna();
                Enemigo enemigo = this.obtenerEnemigoSegunTipo(tipo,fila,columna);
                this.enemigosVisibles.add(enemigo);

            } else {
                this.oleadaActual = this.oleadas.poll();

                if(this.oleadaActual == null){
                    this.victoria = true;
                }
            }
        }

        this.tiempoDespuesDelSpawn += MS_PER_FRAME;
    }

    private void moverEnemigos(){
        Iterator<Enemigo> it = this.enemigosVisibles.iterator();

        while (it.hasNext()) {
            Enemigo enemigo = it.next();
            enemigo.avanzar();

            if(enemigo.estaDestruido()){
                it.remove();
                this.dinero += MONEDAS_POR_MUERTE;
            }else if (enemigo.llegoALaBase()) {
                Base base = (Base) this.mapa[this.posicionBase.getFila()][this.posicionBase.getColumna()];
                base.recibirDanio(enemigo.getDanio());
                it.remove();
            }
        }
    }

    private void dispararTorres(){
        for(Torre torre: this.torres){
            torre.disparar(this.enemigosVisibles);
        }
    }

    public Celda[][] getMapa(){return this.mapa;}

    public int grtDinero(){return this.dinero;}

    public boolean sePuedeComprarArmaSimple(){return this.dinero >= TipoArma.SIMPLE.getCosto();}

    public boolean sePuedeComprarArmaRadial(){return this.dinero >= TipoArma.RADIAL.getCosto();}

    public boolean sePuedeComprarArmaAerea(){return this.dinero >= TipoArma.AEREO.getCosto();}

    public int obtenerLargoDelMapa(){
        return this.mapa.length;
    }

    public int obtenerAnchoDelMapa(){
        return this.mapa[0].length;
    }

    public void agregarArmaATorre(TipoArma tipoArma, int fila, int columna){
        Celda celda = mapa[fila][columna];
        if(celda.getTipo() == TipoCelda.TORRE){
            Torre torre = (Torre)celda;
            if (torre.agregarArma(tipoArma,this.dinero)){
                this.dinero -= tipoArma.getCosto();
            }
        }
    }

    public void actualizar() {
        this.actualizarSpawn();
        this.moverEnemigos();
        this.dispararTorres();
    }

    public HashSet<Enemigo> obtenerEnemigos(){ return this.enemigosVisibles;}

    public boolean hayVictoria(){
        return this.victoria;
    }

    public boolean hayDerrota(){
        Base base = (Base) this.mapa[this.posicionBase.getFila()][this.posicionBase.getColumna()];
        return base.estaDestruida();
    }

}
