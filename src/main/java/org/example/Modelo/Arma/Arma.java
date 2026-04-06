package org.example.Modelo.Arma;

import org.example.Modelo.Direccion;
import org.example.Modelo.Enemigo.Enemigo;
import org.example.Modelo.Posicion;

import java.util.HashSet;

public abstract class Arma {
    protected Posicion posicion;
    protected Direccion direccion;
    private int nivel;
    private final TipoArma tipo;
    protected EstadoArma estado;
    private static final int NIVEL_INICIAL = 1;

    public Arma(TipoArma tipo,Posicion posicion){
        this.tipo = tipo;
        this.posicion = posicion;
        this.nivel = NIVEL_INICIAL;
        this.estado = EstadoArma.QUIETO;
        this.direccion = new Direccion(0,0);
    }

    public abstract void disparar(HashSet<Enemigo> enemigos);

    public TipoArma getTipo(){return this.tipo;}

    public int getNivel(){return this.nivel;}

    public EstadoArma getEstado() {return this.estado;}

    public Direccion getDireccion(){return this.direccion;}

    public boolean subirDeNivel(){
        if(this.nivel < 4){
            this.nivel++;
            return true;
        }

        return false;
    }
}
