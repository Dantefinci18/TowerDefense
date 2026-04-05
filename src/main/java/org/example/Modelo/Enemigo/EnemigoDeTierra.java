package org.example.Modelo.Enemigo;

import org.example.Modelo.Posicion;

import java.util.ArrayList;

public abstract class EnemigoDeTierra extends Enemigo {
    private ArrayList<Posicion> recorrido;
    private int indiceActual;
    private static final int TAMANIO_CELDA = 96;

    public EnemigoDeTierra(double x, double y, int vida, int danio,ArrayList<Posicion> recorrido) {
        super(x, y, vida, 1,danio);
        this.recorrido = recorrido;
        this.indiceActual = 0;
    }

    @Override
    public void avanzar() {
        Posicion destinoActual = this.recorrido.get(this.indiceActual);
        double dx = destinoActual.getColumna()*TAMANIO_CELDA - this.x;
        double dy = destinoActual.getFila()*TAMANIO_CELDA - this.y;

        double distancia = Math.sqrt(dx * dx + dy * dy);

        if (distancia < this.velocidad) {
            this.x = destinoActual.getColumna() * TAMANIO_CELDA;
            this.y = destinoActual.getFila() * TAMANIO_CELDA;

            if (this.recorrido.size() - 1 > this.indiceActual) {
                this.indiceActual++;
            }
        } else {

            dx /= distancia;
            dy /= distancia;

            this.x += dx * this.velocidad;
            this.y += dy * this.velocidad;

            this.direccion.reestablecer(dx,dy);
        }

    }

    @Override
    public boolean llegoALaBase(){ return this.recorrido.size()-1 <= this.indiceActual;}

    public abstract TipoEnemigo getTipo();

}