package org.example.Repositorios;

import org.example.Modelo.Arma.EstadoArma;
import org.example.Modelo.Arma.TipoArma;

import java.util.ArrayList;
import javafx.scene.image.Image;
import org.example.Modelo.Direccion;

import java.util.HashMap;
import java.util.Objects;

public class RepositorioDeArmas {
    private final HashMap<EstadoArma,ArrayList<Image>> imagenesMk1;
    private final HashMap<EstadoArma,ArrayList<Image>> imagenesMk2;
    private final HashMap<EstadoArma,ArrayList<Image>> imagenesMk3;
    private static final int CANTIDAD_DE_IMAGENES = 4;

    public RepositorioDeArmas(){
        this.imagenesMk1 = new HashMap<>();
        this.imagenesMk2 = new HashMap<>();
        this.imagenesMk3 = new HashMap<>();
        this.cargar(EstadoArma.QUIETO,".png",true);
        this.cargar(EstadoArma.DISPARANDO,".gif",false);
    }

    private void cargar(EstadoArma estado, String extencion, boolean con_mk3){
        ArrayList<Image> listaMk1 = new ArrayList<>();
        ArrayList<Image> listaMk2 = new ArrayList<>();
        ArrayList<Image> listaMk3 = new ArrayList<>();

        for(int i = 0; i < CANTIDAD_DE_IMAGENES; i++){
            String pathMk1 = "/assets/Armas/mk1/mk1_" + String.valueOf(i+1) + extencion;
            Image imagenMk1 = new Image(Objects.requireNonNull(getClass().getResourceAsStream(pathMk1)));
            String pathMk2 = "/assets/Armas/mk2/mk2_" + String.valueOf(i+1) + extencion;;
            Image imagenMk2 = new Image(Objects.requireNonNull(getClass().getResourceAsStream(pathMk2)));

            if(con_mk3) {
                String pathMk3 = "/assets/Armas/mk3/mk3_" + String.valueOf(i + 1) + extencion;
                Image imagenMk3 = new Image(Objects.requireNonNull(getClass().getResourceAsStream(pathMk3)));
                listaMk3.add(imagenMk3);
            }
            listaMk1.add(imagenMk1);
            listaMk2.add(imagenMk2);
        }

        this.imagenesMk1.put(estado,listaMk1);
        this.imagenesMk2.put(estado,listaMk2);
        this.imagenesMk3.put(estado,listaMk3);
    }

    public ImagenRotada obtenerImagenArma(TipoArma tipo, EstadoArma estado, Direccion direccion, int nivel){
        Image imagen = null;

        switch (tipo){
            case SIMPLE -> imagen = this.imagenesMk1.get(estado).get(nivel-1);
            case RADIAL -> imagen = this.imagenesMk2.get(estado).get(nivel-1);
            case AEREO -> imagen = this.imagenesMk3.get(estado).get(nivel-1);
        }

        int rotacion = (int)direccion.obtenerAngulo() + 90;

        return new ImagenRotada(imagen,rotacion);
    }
}
