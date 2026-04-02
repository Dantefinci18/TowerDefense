package org.example.Modelo.Celda;

public class Celda {
    private TipoCelda tipo;

    public Celda(TipoCelda tipo){
        this.tipo = tipo;
    }

    public TipoCelda getTipo(){return this.tipo;}
}
