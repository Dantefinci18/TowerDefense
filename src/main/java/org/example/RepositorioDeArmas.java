package org.example;

import org.example.Modelo.Arma.TipoArma;

import java.util.ArrayList;
import javafx.scene.image.Image;
import java.util.Objects;

public class RepositorioDeArmas {
    private final ArrayList<Image> imagenesMk1;
    private final ArrayList<Image> imagenesMk2;
    private final ArrayList<Image> imagenesMk3;
    private static final int CANTIDAD_DE_IMAGENES = 4;

    public RepositorioDeArmas(){
        this.imagenesMk1 = new ArrayList<>();
        this.imagenesMk2 = new ArrayList<>();
        this.imagenesMk3 = new ArrayList<>();

        for(int i = 0; i < CANTIDAD_DE_IMAGENES; i++){
            String pathMk1 = "/assets/Armas/mk1/mk1_" + String.valueOf(i+1) + ".png";
            Image imagenMk1 = new Image(Objects.requireNonNull(getClass().getResourceAsStream(pathMk1)));
            String pathMk2 = "/assets/Armas/mk2/mk2_" + String.valueOf(i+1) + ".png";;
            Image imagenMk2 = new Image(Objects.requireNonNull(getClass().getResourceAsStream(pathMk2)));
            String pathMk3 = "/assets/Armas/mk3/mk3_" + String.valueOf(i+1) + ".png";;
            Image imagenMk3 = new Image(Objects.requireNonNull(getClass().getResourceAsStream(pathMk3)));

            this.imagenesMk1.add(imagenMk1);
            this.imagenesMk2.add(imagenMk2);
            this.imagenesMk3.add(imagenMk3);
        }
    }

    public Image obtenerImagenArma(TipoArma tipo, int nivel){
        Image imagen = null;
        switch (tipo){
            case SIMPLE -> imagen = this.imagenesMk1.get(nivel-1);
            case RADIAL -> imagen = this.imagenesMk2.get(nivel-1);
            case AEREO -> imagen = this.imagenesMk3.get(nivel-1);
        }

        return imagen;
    }
}
