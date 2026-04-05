package org.example.Vistas;

import javafx.animation.AnimationTimer;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import org.example.Modelo.Arma.*;
import org.example.Modelo.TowerDefense;
import javafx.scene.image.ImageView;
import javafx.scene.image.Image;
import javafx.scene.control.Button;

import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.geometry.Insets;
import org.example.Repositorios.RepositorioDeArmas;


public class VistaJuego {
    private final VistaEscenario escenario;
    private final Stage stage;
    private final TowerDefense juego;
    private final Button botonMk1;
    private final Button botonMk2;
    private final Button botonMk3;
    private final Label labelDinero;
    private Arma armaSelecionada;
    private static final int TAMANIO_CELDA = 96;

    public VistaJuego(Stage stage, String nivel){
        this.stage = stage;
        nivel = nivel.replace(" ", "");
        this.juego = new TowerDefense("Niveles/" + nivel + ".json");
        int ancho = this.juego.obtenerAnchoDelMapa() * TAMANIO_CELDA;
        int largo = this.juego.obtenerLargoDelMapa() * TAMANIO_CELDA;
        this.escenario = new VistaEscenario(this.juego,ancho,largo);
        RepositorioDeArmas repositorioDeArmas = new RepositorioDeArmas();
        this.botonMk1 = crearBotonConImagenArma(new ArmaSimple());
        this.botonMk2 = crearBotonConImagenArma(new ArmaRadial());
        this.botonMk3 = crearBotonConImagenArma(new ArmaAerea());
        this.labelDinero = new Label();
    }

    public void mostrar(){
        VBox root = new VBox();
        HBox mapaHBox = new HBox();
        mapaHBox.getChildren().add(this.escenario);
        HBox vistaDeArmas = new HBox();
        root.getChildren().addAll(mapaHBox,vistaDeArmas);

        VBox vboxArmas = new VBox();
        vistaDeArmas.getChildren().add(vboxArmas);

        HBox hboxArmas = new HBox(10);
        BackgroundFill fondo = new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY);
        vistaDeArmas.setBackground(new Background(fondo));
        this.labelDinero.setBackground(new Background(fondo));
        this.labelDinero.setTextFill(Color.WHITE);
        this.labelDinero.setFont(Font.font(15));
        vboxArmas.setBackground(new Background(fondo));
        vboxArmas.getChildren().addAll(this.labelDinero,hboxArmas);

        hboxArmas.setPadding(new Insets(10));
        hboxArmas.setBackground(new Background(fondo));
        hboxArmas.getChildren().addAll(this.botonMk1, this.botonMk2, this.botonMk3);

        this.escenario.setOnMouseClicked(e -> {
            if (this.armaSelecionada != null) {
                int fila = (int) (e.getY() / TAMANIO_CELDA);
                int columna = (int) (e.getX() / TAMANIO_CELDA);
                this.juego.agregarArmaATorre(this.armaSelecionada,fila,columna);
                this.armaSelecionada = null;
            }
        });

        AnimationTimer actualizacion = new AnimationTimer() {
            static final long NANOSECONDS_PER_FRAME = TowerDefense.MS_PER_FRAME * 1_000_000;

            long accumulatedNanos = 0;
            long last = 0;

            @Override
            public void handle(long now) {
                if (last == 0) {
                    last = now;
                }
                long dt = now - last;
                last = now;

                accumulatedNanos += dt;
                while (accumulatedNanos > NANOSECONDS_PER_FRAME) {
                    accumulatedNanos -= NANOSECONDS_PER_FRAME;
                    actualizarJuego();
                }
            }
        };

        actualizacion.start();
        Scene scene = new Scene(root);

        this.stage.setTitle("Tower Defense");
        this.stage.setScene(scene);
        this.stage.setResizable(false);
        this.stage.show();
    }

    private Button crearBotonConImagenArma(Arma arma) {
        RepositorioDeArmas repositorioDeArmas = new RepositorioDeArmas();
        Image imagen = repositorioDeArmas.obtenerImagenArma(arma.getTipo(), 1);
        Button boton = new Button();
        boton.setGraphic(new ImageView(imagen));

        BackgroundFill fondo = new BackgroundFill(Color.BLACK, new CornerRadii(5), Insets.EMPTY);
        boton.setBackground(new Background(fondo));

        BorderStroke borde = new BorderStroke(
                Color.WHITE,
                BorderStrokeStyle.SOLID,
                new CornerRadii(5),
                new BorderWidths(2)
        );

        boton.setBorder(new Border(borde));
        boton.setFocusTraversable(true);
        boton.setOnAction(e -> this.armaSelecionada = arma);

        return boton;
    }

    private void actualizarJuego(){
        this.botonMk1.setDisable(!this.juego.sePuedeComprarArmaSimple());
        this.botonMk2.setDisable(!this.juego.sePuedeComprarArmaRadial());
        this.botonMk3.setDisable(!this.juego.sePuedeComprarArmaAerea());
        int monedas = this.juego.grtDinero();
        this.labelDinero.setText("Monedas: " + monedas);
        this.escenario.render();
        this.juego.actualizar();
    }
}
