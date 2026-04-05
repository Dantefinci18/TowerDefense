package org.example.Vistas;

import javafx.scene.Scene;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.stage.Stage;
import javafx.geometry.Pos;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.VBox;
import javafx.scene.control.*;
import javafx.scene.paint.Color;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.Objects;

public class VistaInicio {
    private final Stage stage;

    public VistaInicio(Stage stage){
        this.stage = stage;
    }

    public void cargarEscenario(){
        ComboBox<String> selectorNivel = new ComboBox<>();
        selectorNivel.getItems().add("Nivel 1");
        selectorNivel.setValue("Nivel 1");
        Button btnContinuar = new Button("Continuar");

        btnContinuar.setOnAction(_ -> {
            String nivelSeleccionado = selectorNivel.getValue();
            VistaJuego juegoVista = new VistaJuego(this.stage,nivelSeleccionado);
            juegoVista.mostrar();
        });

        Image imageTitulo = new Image( Objects.requireNonNull(getClass().getResourceAsStream("/assets/titulo.png")));
        ImageView tituloView = new ImageView(imageTitulo);

        BackgroundFill fill = new BackgroundFill(Color.BLACK, null, null);
        Background background = new Background(fill);
        VBox layout = new VBox(20);
        layout.setBackground(background);
        layout.setAlignment(Pos.CENTER);
        layout.getChildren().addAll(tituloView, selectorNivel, btnContinuar);

        Scene scene = new Scene(layout, 800, 600);

        this.stage.setTitle("Tower Defense");
        this.stage.setScene(scene);
        this.stage.show();

        this.stage.setOnCloseRequest(e -> {
            javafx.application.Platform.exit();
        });
    }
}
