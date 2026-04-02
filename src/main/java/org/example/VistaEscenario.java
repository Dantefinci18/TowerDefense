package org.example;

import javafx.scene.canvas.Canvas;
import org.example.Modelo.Arma.TipoArma;
import org.example.Modelo.Celda.Celda;
import org.example.Modelo.TowerDefense;

public class VistaEscenario extends Canvas {
    private VistaMapa vistaMapa;

    public VistaEscenario(TowerDefense juego, int ancho, int largo){
        super(ancho,largo);
        this.vistaMapa = new VistaMapa(juego.getMapa());
    }

    public void render(){
        var gc = this.getGraphicsContext2D();
        gc.clearRect(0, 0, gc.getCanvas().getWidth(), gc.getCanvas().getHeight());
        this.vistaMapa.mostrar(gc);
    }
}
