package com.devemersonc.view;

import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

public class IntroIDEquipoView {

    public Parent showIntroIDEquipoView() {
        GridPane gridPane = new GridPane();
        gridPane.getStyleClass().add("contenedor");
        gridPane.setAlignment(Pos.TOP_LEFT);
        gridPane.setHgap(15);
        gridPane.setVgap(20);
        gridPane.setPadding(new Insets(20));

        Label idEquipoLabel = new Label("ID. equipo: ");


        TextField idEquipoField = new TextField();

        Button btnSiguiente = new Button("Siguiente");
        btnSiguiente.setOnAction(e -> {
            if(idEquipoField.getText().equals("TM")) {
                SelecAlmacen selecAlmacen = new SelecAlmacen();
                Scene currentScene = btnSiguiente.getScene();
                currentScene.setRoot(selecAlmacen.getSelecAlmacen());
            }
        });

        gridPane.add(idEquipoLabel, 0, 0);
        gridPane.add(idEquipoField, 0, 1);
        gridPane.add(btnSiguiente, 0, 2);
        GridPane.setHalignment(btnSiguiente, HPos.RIGHT);
        return gridPane;
    }
}
