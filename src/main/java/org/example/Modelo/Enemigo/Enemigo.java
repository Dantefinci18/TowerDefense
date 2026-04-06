package org.example.Modelo.Enemigo;

import org.example.Modelo.Direccion;

public abstract class Enemigo {
    protected double x,y;
    private final int vidaInicial;
    private int vidaActual;
    protected int velocidad;
    protected Direccion direccion;
    private final int danio;

    public Enemigo(double x, double y, int vida, int velocidad,int danio){
        this.x = x;
        this.y = y;
        this.vidaInicial = vida;
        this.vidaActual = vida;
        this.velocidad = velocidad;
        this.danio = danio;
        this.direccion = new Direccion(0,0);
    }

    public Direccion getDireccion(){ return this.direccion;}

    public double getX(){return this.x;}

    public double getY(){return this.y;}

    public int getDanio(){return this.danio;}

    public int getVidaActual(){return this.vidaActual;}

    public int getVidaInicial(){return this.vidaInicial;}

    public void recibirDanio(int danio){this.vidaActual = this.vidaActual - danio;}

    public boolean estaDestruido(){return this.vidaActual <= 0;}

    public abstract void avanzar();

    public abstract boolean llegoALaBase();

    public abstract TipoEnemigo getTipo();
}
