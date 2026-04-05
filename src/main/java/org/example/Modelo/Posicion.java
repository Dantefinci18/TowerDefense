package org.example.Modelo;

public class Posicion {
    private final int fila,columna;
    public Posicion(int fila, int columna){
        this.fila = fila;
        this.columna = columna;
    }

    public int getFila(){
        return this.fila;
    }

    public int getColumna(){
        return this.columna;
    }
}
