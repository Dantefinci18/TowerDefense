package org.example.Modelo.Enemigo;

public abstract class Enemigo {
    protected double x,y;
    private int vida;
    protected int velocidad;
    protected Direccion direccion;
    private final int danio;

    public Enemigo(double x, double y, int vida, int velocidad,int danio){
        this.x = x;
        this.y = y;
        this.vida = vida;
        this.velocidad = velocidad;
        this.danio = danio;
        this.direccion = new Direccion(0,0);
    }

    public Direccion getDireccion(){ return this.direccion;}

    public double getX(){return this.x;}

    public double getY(){return this.y;}

    public int getDanio(){return this.danio;}

    public abstract void avanzar();

    public abstract boolean llegoALaBase();

    public abstract TipoEnemigo getTipo();
}
