package org.example.Modelo.Enemigo;

import org.example.Modelo.Posicion;

import java.util.ArrayList;

public abstract class EnemigoDeTierra extends Enemigo {
    private ArrayList<Posicion> recorrido;

    public EnemigoDeTierra(int x, int y, int vida, int danio,ArrayList<Posicion> recorrido) {
        super(x, y, vida, 10,danio);
        this.recorrido = recorrido;
    }

    @Override
    public void avanzar() {

    }

    public abstract TipoEnemigo getTipo();

}