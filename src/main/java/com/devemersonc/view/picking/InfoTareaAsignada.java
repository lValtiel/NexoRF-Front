package com.devemersonc.view.picking;

import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

public class InfoTareaAsignada {

    public GridPane getInfoTarea() {
        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.TOP_LEFT);
        gridPane.setHgap(15);
        gridPane.setVgap(20);
        gridPane.setPadding(new Insets(25));
        gridPane.getStyleClass().add("contenedor");

        Label tipoPedido = new Label("VE - 198662 - 01");
        tipoPedido.getStyleClass().add("tabla");
        tipoPedido.setPrefWidth(140);
        Label zona = new Label("B1PICK");
        zona.setPrefWidth(140);
        zona.getStyleClass().add("tabla");
        Label userAsignado = new Label("ealbina");
        userAsignado.setPrefWidth(140);
        userAsignado.getStyleClass().add("tabla");
        Button btnVolver = new Button("Volver");
        Button btnSiguiente = new Button("Siguiente");
        GridPane.setHalignment(btnSiguiente, HPos.RIGHT);

        btnVolver.setOnAction(e -> {
            TareaDePreparacion tareaDePreparacion = new TareaDePreparacion();
            Scene currentScene = btnVolver.getScene();
            currentScene.setRoot(tareaDePreparacion.getSceneTareaPrep());
        });

        btnSiguiente.setOnAction(e -> {
            TareaSiguiente tareaSiguiente = new TareaSiguiente();
            Scene currentScene = btnSiguiente.getScene();
            currentScene.setRoot(tareaSiguiente.getSceneTareaSiguiente());
        });

        gridPane.add(tipoPedido, 0,0);
        gridPane.add(zona, 0, 1);
        gridPane.add(userAsignado, 0, 2);
        gridPane.add(btnSiguiente, 0, 3);
        gridPane.add(btnVolver, 0, 3);

        return gridPane;
    }
}
