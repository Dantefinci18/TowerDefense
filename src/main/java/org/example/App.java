package org.example;

import javafx.application.Application;
import javafx.stage.Stage;


public class App extends Application {

    @Override
    public void start(Stage stage) {
        VistaInicio inicio = new VistaInicio(stage);
        inicio.cargarEscenario();
    }

    public static void main(String[] args) {
        launch();
    }
}