package org.example.Repositorios;

import org.example.Modelo.Enemigo.Direccion;
import org.example.Modelo.Enemigo.TipoEnemigo;

import java.util.HashMap;
import java.util.Objects;
import javafx.scene.image.Image;

public class RepositorioDeEnemigos {
    private final HashMap<TipoEnemigo, Image> imagenesEnemigos;

    public RepositorioDeEnemigos(){
        this.imagenesEnemigos = new HashMap<>();
        cargar(TipoEnemigo.TANQUE,"/assets/Enemigos/tanque.gif");
        cargar(TipoEnemigo.BUNKER,"/assets/Enemigos/bunker.gif");
        cargar(TipoEnemigo.AVION,"/assets/Enemigos/avion.gif");
    }

    private void cargar(TipoEnemigo tipo, String path){
        Image imagen = new Image(Objects.requireNonNull(getClass().getResourceAsStream(path)));
        this.imagenesEnemigos.put(tipo,imagen);
    }

    public ImagenRotada obtenerImagenEnemigo(TipoEnemigo tipo,Direccion direccion){
        int rotacion = (int)direccion.obtenerAngulo() + 90;
        Image imagen = this.imagenesEnemigos.get(tipo);
        return new ImagenRotada(imagen,rotacion);
    }
}
