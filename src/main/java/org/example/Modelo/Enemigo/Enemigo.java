package org.example.Modelo.Enemigo;

public abstract class Enemigo {
    protected float x,y;
    private int vida;
    protected int velocidad;
    private int danio;
    public Enemigo(int x, int y, int vida, int velocidad,int danio){
        this.x = x;
        this.y = y;
        this.vida = vida;
        this.velocidad = velocidad;
        this.danio = danio;
    }

    public abstract void avanzar();

    public abstract TipoEnemigo getTipo();
}
