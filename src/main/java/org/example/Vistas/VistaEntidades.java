package org.example.Vistas;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import org.example.Modelo.TowerDefense;
import org.example.Repositorios.ImagenRotada;

public class VistaEntidades {

    protected void mostrarImagenRotada(GraphicsContext gc, ImagenRotada imagen, double x, double y){

        if (imagen.getRotacion() == 0) {
            gc.drawImage(imagen.getImagen(), x, y, TowerDefense.TAMANIO_CELDA, TowerDefense.TAMANIO_CELDA);

        } else {
            gc.save();
            double centroX = x + TowerDefense.TAMANIO_CELDA / 2.0;
            double centroY = y + TowerDefense.TAMANIO_CELDA / 2.0;

            gc.translate(centroX, centroY);
            gc.rotate(imagen.getRotacion());

            gc.drawImage(imagen.getImagen(), -TowerDefense.TAMANIO_CELDA / 2.0, -TowerDefense.TAMANIO_CELDA / 2.0,
                    TowerDefense.TAMANIO_CELDA, TowerDefense.TAMANIO_CELDA);

            gc.restore();
        }
    }

    protected void mostrarVida(GraphicsContext gc, double x, double y, double vidaActual,double vidaInicial) {

        final double ANCHO_BARRA = 40;
        final double ALTO_BARRA = 6;

        double porcentaje = vidaActual / vidaInicial ;


        gc.setFill(Color.BLACK);
        gc.fillRect(x-ANCHO_BARRA/2.0, y, ANCHO_BARRA, ALTO_BARRA);

        gc.setFill(Color.WHITE);
        gc.fillRect(x-ANCHO_BARRA/2.0, y, ANCHO_BARRA* porcentaje , ALTO_BARRA);
    }
}
