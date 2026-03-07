package com.devemersonc.view.picking;

import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

public class MenuPrincipalPicking {

    public GridPane getSceneMenu() {
        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.TOP_LEFT);
        gridPane.setHgap(15);
        gridPane.setVgap(5);
        gridPane.setPadding(new Insets(20));
        gridPane.getStyleClass().add("contenedor");

        Label titulo = new Label("Menú princ.");
        Label opcion_0 = new Label("0 Admin tareas");
        Label opcion_1 = new Label("1 Empaque");
        Label opcion_2 = new Label("2 Consulta Stock");
        Label opcion_3 = new Label("4 Cerrar Sesión");

        TextField entradaNum = new TextField();
        Button btnSiguiente = new Button("Siguiente");
        GridPane.setHalignment(btnSiguiente, HPos.RIGHT);

        gridPane.add(titulo, 0, 0);
        gridPane.add(opcion_0, 0, 1);
        gridPane.add(opcion_1, 0, 2);
        gridPane.add(opcion_2, 0, 3);
        gridPane.add(opcion_3, 0, 4);
        gridPane.add(entradaNum, 0, 5);
        gridPane.add(btnSiguiente, 0, 6);

        btnSiguiente.setOnAction(e -> {
            TareaDePreparacion tareaDePreparacion = new TareaDePreparacion();
            Scene scene = btnSiguiente.getScene();
            scene.getStylesheets().add(getClass().getResource("/css/tareaPreparacion.css").toExternalForm());
            scene.setRoot(tareaDePreparacion.getSceneTareaPrep());
        });

        return gridPane;
    }
}
