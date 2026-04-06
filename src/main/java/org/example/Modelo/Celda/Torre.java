package org.example.Modelo.Celda;

import org.example.Modelo.Arma.*;
import org.example.Modelo.Enemigo.Enemigo;
import org.example.Modelo.Posicion;

import java.util.HashSet;

public class Torre extends Celda{
    private final Posicion posicion;
    private Arma arma;

    public Torre(int fila, int columna){
        super(TipoCelda.TORRE);
        this.posicion = new Posicion(fila,columna);
    }

    public boolean agregarArma(TipoArma tipoArma, int dinero) {
        if (tipoArma.getCosto() <= dinero){
            if (this.arma == null) {
                switch (tipoArma){
                    case SIMPLE -> this.arma = new ArmaSimple(this.posicion);
                    case RADIAL -> this.arma = new ArmaRadial(this.posicion);
                    case AEREO -> this.arma = new ArmaAerea(this.posicion);
                }
                return true;
            }

            return this.arma.subirDeNivel();
        }

        return false;
    }

    public void disparar(HashSet<Enemigo> enemigos){
        if(this.arma != null) {
            this.arma.disparar(enemigos);
        }
    }

    public Arma getArma(){ return this.arma;}
}
