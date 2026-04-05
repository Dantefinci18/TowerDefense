package org.example.Repositorios;

import org.example.Modelo.Celda.TipoCelda;

import java.util.HashMap;
import javafx.scene.image.Image;
import java.util.Objects;

public class RepositorioDeCeldas {

    private final HashMap<TipoCelda, ImagenRotada> imagenesCelda;

    public RepositorioDeCeldas(){
        this.imagenesCelda = new HashMap<>();

        cargar(TipoCelda.BASE, "/assets/base.png", 0);
        cargar(TipoCelda.CAMINO_HORIZONTAL, "/assets/camino.png", 90);
        cargar(TipoCelda.CAMINO_VERTICAL, "/assets/camino.png", 0);
        cargar(TipoCelda.CURVA_ABAJO_DERECHA, "/assets/curva.png", 0);
        cargar(TipoCelda.CURVA_ARRIBA_DERECHA, "/assets/curva.png", -90);
        cargar(TipoCelda.CURVA_ARRIBA_IZQUIERDA, "/assets/curva.png", 180);
        cargar(TipoCelda.CURVA_ABAJO_IZQUIERDA, "/assets/curva.png", 90);
        cargar(TipoCelda.TORRE, "/assets/torre.png", 0);
        cargar(TipoCelda.VACIO, "/assets/terreno.png", 0);
    }

    private void cargar(TipoCelda tipo, String path, int rotacion){
        Image imagen = new Image(Objects.requireNonNull(getClass().getResourceAsStream(path)));
        ImagenRotada imagenRotada = new ImagenRotada(imagen,rotacion);
        this.imagenesCelda.put(tipo, imagenRotada);
    }

    public ImagenRotada obtenerImagenCelda(TipoCelda tipo){
        return this.imagenesCelda.get(tipo);
    }
}
