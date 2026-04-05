package org.example.Modelo.Enemigo;

import org.example.Modelo.Posicion;

public class EnemigoAvion extends Enemigo {
    private Posicion celdaBase;
    private static final int TAMANIO_CELDA = 96;

    public EnemigoAvion(double x, double y, Posicion celdaBase){
        super(x,y,100,3,15);
        this.celdaBase = celdaBase;
    }

    @Override
    public void avanzar(){
        double dx = this.celdaBase.getColumna()*TAMANIO_CELDA - this.x;
        double dy = this.celdaBase.getFila()*TAMANIO_CELDA - this.y;

        double distancia = Math.sqrt(dx * dx + dy * dy);

        dx /= distancia;
        dy /= distancia;

        this.x += dx * this.velocidad;
        this.y += dy * this.velocidad;

        this.direccion.reestablecer(dx,dy);
    }

    @Override
    public boolean llegoALaBase(){
        int fila = (int)(this.y/TAMANIO_CELDA);
        int columna = (int)(this.x/TAMANIO_CELDA);

        return fila == this.celdaBase.getFila() && columna == this.celdaBase.getColumna();
    }

    @Override
    public TipoEnemigo getTipo(){
        return TipoEnemigo.AVION;
    }
}
