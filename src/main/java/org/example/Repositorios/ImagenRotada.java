package org.example.Repositorios;
import javafx.scene.image.Image;

public class ImagenRotada {
    private final Image imagen;
    private final int rotacion;

    public ImagenRotada(Image imagen, int rotacion){
        this.imagen = imagen;
        this.rotacion = rotacion;
    }

    public Image getImagen() { return this.imagen; }
    public int getRotacion() { return  this.rotacion; }
}
