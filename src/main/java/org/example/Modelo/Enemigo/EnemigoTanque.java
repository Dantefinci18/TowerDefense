package org.example.Modelo.Enemigo;

import org.example.Modelo.Posicion;

import java.util.ArrayList;

public class EnemigoTanque extends EnemigoDeTierra {
    public EnemigoTanque(int x, int y, ArrayList<Posicion> recorrido){
        super(x,y,100,10,recorrido);
    }

    @Override
    public TipoEnemigo getTipo(){
        return TipoEnemigo.TANQUE;
    }
}
