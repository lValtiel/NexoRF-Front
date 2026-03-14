package com.devemersonc.view.picking;

import com.devemersonc.model.SessionManager;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import com.devemersonc.model.OrderResponseDTO;

public class TareaSiguiente {

    public GridPane getSceneTareaSiguiente() {

        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.TOP_LEFT);
        gridPane.setHgap(15);
        gridPane.setVgap(20);
        gridPane.setPadding(new Insets(25));
        gridPane.getStyleClass().add("contenedor");

        Label sigTarea = new Label("Siguiente Tarea:");
        Label presEnter = new Label("PRESIONE ENTER");

        Label opcion_0 = new Label("0. Comenzar pedido");
        Label opcion_2 = new Label("2. Cerrar pedido.");
        TextField entrada = new TextField();
        entrada.getStyleClass().add("entrada");
        entrada.setPrefWidth(140);
        Button btnEntrar = new Button("Entrar");
        GridPane.setHalignment(btnEntrar, HPos.RIGHT);
        Button btnVolver = new Button("Volver");

        btnVolver.setOnAction(e -> {
            TareaDePreparacion tareaDePreparacion = new TareaDePreparacion();
            Scene currentScene = btnVolver.getScene();
            currentScene.setRoot(tareaDePreparacion.getSceneTareaPrep());
        });

        btnEntrar.setOnAction(e -> {
            String text = entrada.getText();
            try {
                int numEntradaField = Integer.parseInt(text);

                switch (numEntradaField) {
                    case 0:
                        ComienzoPedido comienzoPedido = new ComienzoPedido();
                        Scene currentScene = btnEntrar.getScene();
                        currentScene.setRoot(comienzoPedido.getComienzoPedidoView());
                        break;
                    case 1:
                        System.out.println("Consulta última tarea.");
                        break;
                    case 2:
                        System.out.println("Cerrar pedido");
                        break;
                }
            }catch (NumberFormatException exception) {
                exception.fillInStackTrace();
            }
        });

        gridPane.add(sigTarea,0, 1);
        gridPane.add(presEnter, 0, 2);
        gridPane.add(opcion_0, 0, 3);
        gridPane.add(opcion_2, 0, 4);
        gridPane.add(entrada, 0 ,5);
        gridPane.add(btnEntrar, 0, 6);
        gridPane.add(btnVolver, 0, 6);

        return gridPane;
    }
}