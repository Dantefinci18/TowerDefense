package org.example.Modelo.Enemigo;

import org.example.Modelo.Posicion;

public class EnemigoAvion extends Enemigo {
    private Posicion celdaBase;
    public EnemigoAvion(int x, int y, Posicion celdaBase){
        super(x,y,100,20,15);
    }

    @Override
    public void avanzar(){
    }

    @Override
    public TipoEnemigo getTipo(){
        return TipoEnemigo.AVION;
    }
}
