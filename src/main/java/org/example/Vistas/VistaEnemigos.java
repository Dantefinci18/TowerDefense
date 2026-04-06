package org.example.Vistas;

import javafx.scene.canvas.GraphicsContext;
import org.example.Modelo.Enemigo.Direccion;
import org.example.Modelo.Enemigo.Enemigo;
import org.example.Modelo.Enemigo.TipoEnemigo;
import org.example.Repositorios.ImagenRotada;
import org.example.Repositorios.RepositorioDeEnemigos;
import java.util.HashSet;

public class VistaEnemigos extends VistaEntidades{
    private final HashSet<Enemigo> enemigos;
    private final RepositorioDeEnemigos repositorioDeEnemigos;

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

            super.mostrarImagenRotada(gc,imagen,x,y);
        }
    }
}
