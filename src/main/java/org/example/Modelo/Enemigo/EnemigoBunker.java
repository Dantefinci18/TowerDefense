package org.example.Modelo.Enemigo;

import org.example.Modelo.Posicion;

import java.util.ArrayList;

public class EnemigoBunker extends EnemigoDeTierra {
    public EnemigoBunker(double x, double y, ArrayList<Posicion> recorrido){
        super(x,y,200,20,recorrido);
    }

    @Override
    public TipoEnemigo getTipo(){
        return TipoEnemigo.BUNKER;
    }
}
