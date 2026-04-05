package org.example.Modelo;

import org.example.Modelo.Arma.Arma;
import org.example.Modelo.Arma.ArmaAerea;
import org.example.Modelo.Arma.ArmaRadial;
import org.example.Modelo.Arma.ArmaSimple;
import org.example.Modelo.Celda.Base;
import org.example.Modelo.Celda.Celda;
import org.example.Modelo.Celda.TipoCelda;
import org.example.Modelo.Celda.Torre;
import org.example.Modelo.Enemigo.*;
import org.example.Modelo.LogicaDeNiveles.NivelLoader;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;

public class TowerDefense {
    private Posicion posicionBase;
    private Posicion posicionSpawn;
    private ArrayList<Posicion> camino;
    private ArrayList<Enemigo> enemigosVisibles;
    private ArrayDeque<TipoEnemigo> oleadaActual;
    private ArrayDeque<ArrayDeque<TipoEnemigo>> oleadas;
    private final Celda[][] mapa;
    private int dinero;
    private long tiempoDespuesDelSpawn;
    private boolean victoria;
    private static final int DINERO_INICIAL = 100;
    public static final int FPS = 60;
    public static final long MS_PER_FRAME = 1000 / FPS;
    private static final long TIEMPO_SPAWN_MS = 10 * 1000;
    private static final long TAMANIO_CELDA = 96;

    public TowerDefense(String nivel){
        NivelLoader loader= new NivelLoader(nivel);
        this.mapa = loader.getMapa();
        this.posicionSpawn = loader.getPosicionSpawn();
        this.posicionBase = loader.getPosicionBase();
        this.enemigosVisibles = new ArrayList<>();
        this.oleadas = loader.getOleadas();
        this.oleadaActual = this.oleadas.poll();
        this.camino =new ArrayList<>(Arrays.asList(loader.getCamino()));
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

    public Celda[][] getMapa(){return this.mapa;}

    public int grtDinero(){return this.dinero;}

    public boolean sePuedeComprarArmaSimple(){return this.dinero >= ArmaSimple.COSTO;}

    public boolean sePuedeComprarArmaRadial(){return this.dinero >= ArmaRadial.COSTO;}

    public boolean sePuedeComprarArmaAerea(){return this.dinero >= ArmaAerea.COSTO;}

    public int obtenerLargoDelMapa(){
        return this.mapa.length;
    }

    public int obtenerAnchoDelMapa(){
        return this.mapa[0].length;
    }

    public void agregarArmaATorre(Arma arma, int fila, int columna){
        Celda celda = mapa[fila][columna];
        if(celda.getTipo() == TipoCelda.TORRE){
            Torre torre = (Torre)celda;
            if (torre.agregarArma(arma,this.dinero)){
                this.dinero -= arma.getCosto();
            }
        }
    }

    public void actualizar() {
        if(this.tiempoDespuesDelSpawn >= TIEMPO_SPAWN_MS){
            this.tiempoDespuesDelSpawn = 0;

            if(this.oleadaActual == null){
                this.victoria = true;
            } else if(this.oleadaActual.isEmpty()) {
                this.oleadaActual = this.oleadas.poll();
            } else {
                TipoEnemigo tipo = this.oleadaActual.poll();
                int fila = this.posicionSpawn.getFila();
                int columna = this.posicionSpawn.getColumna();
                Enemigo enemigo = this.obtenerEnemigoSegunTipo(tipo,fila,columna);
                this.enemigosVisibles.add(enemigo);
            }
        }
        this.tiempoDespuesDelSpawn += MS_PER_FRAME;

        for(var enemigo: this.enemigosVisibles){
            enemigo.avanzar();
        }
    }

    public ArrayList<Enemigo> obtenerEnemigos(){ return this.enemigosVisibles;}

    public boolean hayVictoria(){
        return this.victoria;
    }

    public boolean hayDerrota(){
        Base base = (Base) this.mapa[this.posicionBase.getFila()][this.posicionBase.getColumna()];
        return base.estaDestruida();
    }

}
