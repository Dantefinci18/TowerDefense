package org.example.Vistas;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import org.example.Modelo.Arma.EstadoArma;
import org.example.Modelo.Arma.TipoArma;
import org.example.Modelo.Celda.Base;
import org.example.Modelo.Direccion;
import org.example.Modelo.TowerDefense;
import org.example.Repositorios.ImagenRotada;
import org.example.Modelo.Arma.Arma;
import org.example.Modelo.Celda.Celda;
import org.example.Modelo.Celda.TipoCelda;
import org.example.Modelo.Celda.Torre;
import org.example.Repositorios.RepositorioDeArmas;
import org.example.Repositorios.RepositorioDeCeldas;

public class VistaMapa extends VistaEntidades{
    private final Celda[][] mapa;
    private final RepositorioDeCeldas repositorioDeCeldas;
    private final RepositorioDeArmas repositorioDeArmas;
    private static final int VIDA_INICIAL_BASE = 80;

    public VistaMapa(Celda[][] mapa){
        this.mapa = mapa;
        this.repositorioDeCeldas = new RepositorioDeCeldas();
        this.repositorioDeArmas = new RepositorioDeArmas();
    }

    public void mostrar(GraphicsContext gc){
        for(int i = 0; i < mapa.length;i++){
            for(int j = 0; j < mapa[0].length; j++){
                Celda celda = mapa[i][j];
                ImagenRotada imagen = this.repositorioDeCeldas.obtenerImagenCelda(celda.getTipo());
                double x = j* TowerDefense.TAMANIO_CELDA;
                double y = i*TowerDefense.TAMANIO_CELDA;

                super.mostrarImagenRotada(gc,imagen,x,y);

                if(celda.getTipo() == TipoCelda.TORRE){
                    Torre torre = (Torre) celda;
                    mostrarArma(gc,torre.getArma(),x,y);
                }

                if(celda.getTipo() == TipoCelda.BASE){
                    Base base = (Base) celda;
                    int vida = base.getVida();
                    double centrox = x + TowerDefense.TAMANIO_CELDA/2.0;
                    super.mostrarVida(gc,centrox,y,vida,VIDA_INICIAL_BASE);
                }

            }
        }
    }

    private void mostrarArma(GraphicsContext gc, Arma arma, double x, double y){
        if(arma !=null){
            TipoArma tipo = arma.getTipo();
            EstadoArma estado = arma.getEstado();
            Direccion direccion = arma.getDireccion();
            int nivel = arma.getNivel();
            ImagenRotada imagen = this.repositorioDeArmas.obtenerImagenArma(tipo,estado,direccion,nivel);
            super.mostrarImagenRotada(gc,imagen,x,y);
        }
    }
}
