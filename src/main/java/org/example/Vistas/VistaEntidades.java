package org.example.Vistas;

import javafx.scene.canvas.GraphicsContext;
import org.example.Repositorios.ImagenRotada;

public class VistaEntidades {
    protected static final int TAMANIO_CELDA = 96;

    protected void mostrarImagenRotada(GraphicsContext gc, ImagenRotada imagen, double x, double y){

        if (imagen.getRotacion() == 0) {
            gc.drawImage(imagen.getImagen(), x, y, TAMANIO_CELDA, TAMANIO_CELDA);

        } else {
            gc.save();
            double centroX = x + TAMANIO_CELDA / 2.0;
            double centroY = y + TAMANIO_CELDA / 2.0;

            gc.translate(centroX, centroY);
            gc.rotate(imagen.getRotacion());

            gc.drawImage(imagen.getImagen(), -TAMANIO_CELDA / 2.0, -TAMANIO_CELDA / 2.0,
                    TAMANIO_CELDA, TAMANIO_CELDA);

            gc.restore();
        }
    }
}
