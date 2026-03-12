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
        Label tituloNumOrder = new Label("N° de orden:");
        tituloNumOrder.getStyleClass().add("titulos");
        Label numOrder = new Label("ORD-001");
        numOrder.setPrefWidth(140);
        numOrder.setPadding(new Insets(0,0,0,5));
        numOrder.getStyleClass().add("tabla");

        Label tituloNumTareas = new Label("N° de tareas:");
        tituloNumTareas.getStyleClass().add("titulos");
        Label numTareas = new Label("4");
        numTareas.setPrefWidth(140);
        numTareas.setPadding(new Insets(0,5,0,5));
        numTareas.getStyleClass().add("tabla");

        Label tituloEstado = new Label("Estado:");
        tituloEstado.getStyleClass().add("titulos");
        Label estado = new Label("Pendiente");
        estado.setPrefWidth(140);
        estado.setPadding(new Insets(0,5,0,5));
        estado.getStyleClass().add("tabla");

        Button btnEntrar = new Button("Entrar");
        GridPane.setHalignment(btnEntrar, HPos.RIGHT);
        Button btnSalir = new Button("Volver");

        btnEntrar.setOnAction(e -> {
            TareaSiguiente tareaSiguiente = new TareaSiguiente();
            Scene currentScene = btnEntrar.getScene();
            currentScene.setRoot(tareaSiguiente.getSceneTareaSiguiente());
        });

        btnSalir.setOnAction(e -> {
            MenuPrincipalPicking menuPrincipalPicking = new MenuPrincipalPicking();
            Scene currentScene = btnSalir.getScene();
            currentScene.setRoot(menuPrincipalPicking.getSceneMenu());
        });

        gridPane.add(titulo, 0, 0);
        gridPane.add(tituloNumOrder, 0, 1);
        gridPane.add(numOrder, 0, 2);
        gridPane.add(tituloNumTareas, 0, 3);
        gridPane.add(numTareas, 0, 4);
        gridPane.add(tituloEstado, 0, 5);
        gridPane.add(estado, 0, 6);
        gridPane.add(btnEntrar, 0, 7);
        gridPane.add(btnSalir, 0, 7);

        return gridPane;
    }
}
