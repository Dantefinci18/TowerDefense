package org.example.Modelo.Arma;

import org.example.Modelo.Enemigo.Enemigo;
import org.example.Modelo.Enemigo.TipoEnemigo;
import org.example.Modelo.Posicion;
import org.example.Modelo.TowerDefense;

import java.util.HashSet;

import static org.example.Modelo.Enemigo.TipoEnemigo.AVION;

public class ArmaSimple extends Arma {
    private double tiempoSinDisparar;
    private static final int DANIO = 20;
    private static final int RADIO_DE_ALCAZE = 3;

    public ArmaSimple(Posicion posicion) {
        super(TipoArma.SIMPLE,posicion);
        this.tiempoSinDisparar = 0;
    }

    private boolean estaDentroDelRadio(int fila, int columna){
        return this.posicion.getColumna()-RADIO_DE_ALCAZE < columna && columna < this.posicion.getColumna()+RADIO_DE_ALCAZE
                && this.posicion.getFila() - RADIO_DE_ALCAZE < fila && fila < this.posicion.getFila() + RADIO_DE_ALCAZE;
    }

    @Override
    public void disparar(HashSet<Enemigo> enemigos){
        this.tiempoSinDisparar++;

        if (this.tiempoSinDisparar < TowerDefense.FPS) return;
        for (Enemigo enemigo : enemigos) {
            int filaEnemigo = (int) (enemigo.getY() / TowerDefense.TAMANIO_CELDA);
            int columnaEnemigo = (int) (enemigo.getX() / TowerDefense.TAMANIO_CELDA);

            if (enemigo.getTipo() != TipoEnemigo.AVION && this.estaDentroDelRadio(filaEnemigo, columnaEnemigo)) {
                enemigo.recibirDanio(DANIO);
                double xTorre = this.posicion.getColumna() * TowerDefense.TAMANIO_CELDA;
                double yTorre = this.posicion.getFila() * TowerDefense.TAMANIO_CELDA;

                double dx = enemigo.getX() - xTorre;
                double dy = enemigo.getY() - yTorre;

                this.direccion.reestablecer(dx, dy);
                this.estado = EstadoArma.DISPARANDO;
                this.tiempoSinDisparar = 0;
                return;
            }
        }

        this.estado = EstadoArma.QUIETO;

    }
}
