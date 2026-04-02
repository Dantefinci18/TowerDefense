package org.example.Modelo;

import org.example.Modelo.Arma.Arma;
import org.example.Modelo.Arma.ArmaAerea;
import org.example.Modelo.Arma.ArmaRadial;
import org.example.Modelo.Arma.ArmaSimple;
import org.example.Modelo.Celda.Celda;
import org.example.Modelo.Celda.TipoCelda;
import org.example.Modelo.Celda.Torre;
import org.example.Modelo.Enemigo.Enemigo;
import org.example.Modelo.LogicaDeNiveles.NivelLoader;

import java.io.FileNotFoundException;
import java.util.ArrayList;

public class TowerDefense {
    private Posicion posicionBase;
    private Posicion posicionSpawn;
    private ArrayList<Enemigo> enemigos;
    private final Celda[][] mapa;
    private int dinero;
    private static final int DINERO_INICIAL = 100;
    public static final int FPS = 60;
    public static final long MS_PER_FRAME = 1000 / FPS;

    public TowerDefense(String nivel){
        NivelLoader loader= new NivelLoader(nivel);
        this.mapa = loader.getMapa();
        this.dinero = DINERO_INICIAL;
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
        System.out.println(celda.getTipo().toString());
        if(celda.getTipo() == TipoCelda.TORRE){
            Torre torre = (Torre)celda;
            if (torre.agregarArma(arma,this.dinero)){
                this.dinero -= arma.getCosto();
            }
        }
    }

    public void actualizar() {
        for(var enemigo: this.enemigos){
            enemigo.avanzar();
        }
    }

}
