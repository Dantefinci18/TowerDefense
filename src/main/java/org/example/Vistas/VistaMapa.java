package org.example.Vistas;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import org.example.Modelo.Celda.Base;
import org.example.Repositorios.ImagenRotada;
import org.example.Modelo.Arma.Arma;
import org.example.Modelo.Celda.Celda;
import org.example.Modelo.Celda.TipoCelda;
import org.example.Modelo.Celda.Torre;
import org.example.Repositorios.RepositorioDeArmas;
import org.example.Repositorios.RepositorioDeCeldas;

public class VistaMapa {
    private final Celda[][] mapa;
    private final RepositorioDeCeldas repositorioDeCeldas;
    private final RepositorioDeArmas repositorioDeArmas;
    private static final int TAMANIO_CELDA = 96;

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
                double x = j*TAMANIO_CELDA;
                double y = i*TAMANIO_CELDA;

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

                if(celda.getTipo() == TipoCelda.TORRE){
                    Torre torre = (Torre) celda;
                    mostrarArma(gc,torre.getArma(),x,y);
                }

                if(celda.getTipo() == TipoCelda.BASE){
                    Base base = (Base) celda;
                    int vida = base.getVida();
                    double centrox = x + TAMANIO_CELDA/2.0;
                    this.dibujarVidaBase(gc,centrox,y,vida);
                }

            }
        }
    }

    private void mostrarArma(GraphicsContext gc, Arma arma, double x, double y){
        if(arma !=null){
            Image imagen = this.repositorioDeArmas.obtenerImagenArma(arma.getTipo(), arma.getNivel());
            gc.drawImage(imagen, x, y, TAMANIO_CELDA, TAMANIO_CELDA);
        }
    }

    private void dibujarVidaBase(GraphicsContext gc, double x, double y, int vida) {

        final double VIDA_MAXIMA = 80;
        final double ANCHO_BARRA = 40;
        final double ALTO_BARRA = 6;

        double porcentaje = vida / VIDA_MAXIMA ;


        gc.setFill(Color.BLACK);
        gc.fillRect(x-ANCHO_BARRA/2.0, y, ANCHO_BARRA, ALTO_BARRA);

        gc.setFill(Color.WHITE);
        gc.fillRect(x-ANCHO_BARRA/2.0, y, ANCHO_BARRA* porcentaje , ALTO_BARRA);
    }
}
