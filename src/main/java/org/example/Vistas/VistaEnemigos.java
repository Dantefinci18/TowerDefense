package org.example.Vistas;

import javafx.scene.canvas.GraphicsContext;
import org.example.Modelo.Enemigo.Direccion;
import org.example.Modelo.Enemigo.Enemigo;
import org.example.Modelo.Enemigo.TipoEnemigo;
import org.example.Repositorios.ImagenRotada;
import org.example.Repositorios.RepositorioDeEnemigos;

import java.util.ArrayList;
import java.util.HashSet;

public class VistaEnemigos {
    private final HashSet<Enemigo> enemigos;
    private final RepositorioDeEnemigos repositorioDeEnemigos;
    private static final int TAMANIO_CELDA = 96;

    public VistaEnemigos(HashSet<Enemigo> enemigos){
        this.enemigos = enemigos;
        this.repositorioDeEnemigos = new RepositorioDeEnemigos();
    }

    public void mostrar(GraphicsContext gc){
        for(Enemigo enemigo: this.enemigos){
            TipoEnemigo tipo = enemigo.getTipo();
            Direccion direccion = enemigo.getDireccion();
            ImagenRotada imagen = this.repositorioDeEnemigos.obtenerImagenEnemigo(tipo,direccion);

            double x = enemigo.getX();
            double y = enemigo.getY();

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
}
