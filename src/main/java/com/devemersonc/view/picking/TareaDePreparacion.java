package com.devemersonc.view.picking;

import com.devemersonc.controller.NavigationController;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

public class TareaDePreparacion {

    public GridPane getSceneTareaPrep() {
        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.TOP_LEFT);
        gridPane.setHgap(15);
        gridPane.setVgap(20);
        gridPane.setPadding(new Insets(25));
        gridPane.getStyleClass().add("contenedor");

        Label titulo = new Label("TAREA DE PREPARACION");
        Label numTareas = new Label("N° Tar: 1");
        numTareas.setPrefWidth(140);
        numTareas.getStyleClass().add("tabla");
        Label kilos = new Label("kilos: 102.6 kg");
        kilos.setPrefWidth(140);
        kilos.getStyleClass().add("tabla");
        Label vol = new Label("VOL: .25 M3");
        vol.setPrefWidth(140);
        vol.getStyleClass().add("tabla");

        Button btnEntrar = new Button("Entrar");
        GridPane.setHalignment(btnEntrar, HPos.RIGHT);
        Button btnSalir = new Button("Volver");

        btnEntrar.setOnAction(e -> {
            InfoTareaAsignada info = new InfoTareaAsignada();
            Scene currentScene = btnEntrar.getScene();
            currentScene.setRoot(info.getInfoTarea());

            /*TareaSiguiente tareaSiguiente = new TareaSiguiente();
            Scene currentScene = btnEntrar.getScene();
            currentScene.setRoot(tareaSiguiente.getSceneTareaSiguiente());*/
        });

        btnSalir.setOnAction(e -> {
            MenuPrincipalPicking menuPrincipalPicking = new MenuPrincipalPicking();
            Scene currentScene = btnSalir.getScene();
            currentScene.setRoot(menuPrincipalPicking.getSceneMenu());
        });

        gridPane.add(titulo,
                0, 0);
        gridPane.add(numTareas, 0, 1);
        gridPane.add(kilos, 0, 2);
        gridPane.add(vol, 0, 3);
        gridPane.add(btnEntrar, 0, 4);
        gridPane.add(btnSalir, 0, 4);

        return gridPane;
    }
}
