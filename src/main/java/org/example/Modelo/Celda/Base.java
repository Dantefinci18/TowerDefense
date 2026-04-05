package org.example.Modelo.Celda;

public class Base extends Celda{
    private int vida;

    public Base(int vida){
        super(TipoCelda.BASE);
        this.vida = vida;
    }

    public void recibirDanio(int danio){
        this.vida = this.vida-danio;
    }

    public int getVida() {
        return vida;
    }

    public boolean estaDestruida() {return this.vida <= 0;}
}
