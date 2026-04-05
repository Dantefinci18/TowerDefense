package org.example.Vistas;

import javafx.scene.canvas.Canvas;
import org.example.Modelo.TowerDefense;

public class VistaEscenario extends Canvas {
    private final VistaMapa vistaMapa;
    private final VistaEnemigos vistaEnemigos;

    public VistaEscenario(TowerDefense juego, int ancho, int largo){
        super(ancho,largo);
        this.vistaMapa = new VistaMapa(juego.getMapa());
        this.vistaEnemigos = new VistaEnemigos(juego.obtenerEnemigos());
    }

    public void render(){
        var gc = this.getGraphicsContext2D();
        gc.clearRect(0, 0, gc.getCanvas().getWidth(), gc.getCanvas().getHeight());
        this.vistaMapa.mostrar(gc);
        this.vistaEnemigos.mostrar(gc);
    }
}
