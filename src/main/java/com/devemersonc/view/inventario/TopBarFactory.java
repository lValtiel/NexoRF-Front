package com.devemersonc.view.inventario;

import com.devemersonc.controller.NavigationController;
import com.devemersonc.view.OrdersView;
import com.devemersonc.view.PickingView;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;

public class TopBarFactory {

    public static HBox createTopBar() {
        Button btnCerrarSesion = new Button("Cerrar sesión");
        btnCerrarSesion.getStyleClass().add("btn-top-bar1_btnsesion");
        btnCerrarSesion.setOnAction(e -> NavigationController.getInstance().showLogin());

        Button btnOrdenes = new Button("Ordenes");
        btnOrdenes.getStyleClass().add("btn-top-bar1");

        Button btnSimuladorPicking = new Button("Simulador picking");
        btnSimuladorPicking.getStyleClass().add("btn-top-bar1");
        btnSimuladorPicking.setOnAction(e -> {
            new PickingView().showViewPicking();
        });

        btnOrdenes.setOnAction(e -> {
            NavigationController.getInstance().setContent(
                    new OrdersView().getView()
            );

            NavigationController.getInstance().setTop(
                    TopBarFactory.createTopBarWithBack(() -> NavigationController.getInstance().showInventario())
            );
        });

        Label titulo = new Label("NexoRF");
        titulo.getStyleClass().add("titulo-topbar");

        Region spacerLeft = new Region();
        Region spacerRight = new Region();
        HBox.setHgrow(spacerLeft, Priority.ALWAYS);
        HBox.setHgrow(spacerRight, Priority.ALWAYS);

        HBox topBar = new HBox(10);
        topBar.setPadding(new Insets(5,10,5,10));
        topBar.setAlignment(Pos.CENTER);
        topBar.getChildren().addAll(btnCerrarSesion, spacerLeft, titulo, spacerRight, btnOrdenes, btnSimuladorPicking);
        topBar.setMaxWidth(Double.MAX_VALUE);
        topBar.getStyleClass().add("top-bar-container");

        return topBar;
    }

    public static VBox createTopBarWithBack(Runnable onBack) {
        HBox topBar = createTopBar(); // tu barra original: cerrar sesión, órdenes, simulador

        Label tituloOrdenes = new Label("Lista Ordenes");
        tituloOrdenes.getStyleClass().add("titulo-lista");
        HBox tituloWrapper = new HBox(tituloOrdenes);
        tituloWrapper.setPadding(new Insets(20,0,20,0));
        tituloWrapper.setAlignment(Pos.CENTER);

        Button btnVolver = new Button("Volver");
        btnVolver.setStyle(
                "-fx-background-color: #5A7D9A;" +   // azul grisáceo elegante
                        "-fx-text-fill: white;" +            // texto blanco
                        "-fx-font-size: 14px;" +
                        "-fx-font-weight: bold;" +
                        "-fx-background-radius: 6;" +        // esquinas redondeadas
                        "-fx-padding: 8 15 8 15;"
        );

        // hover: más oscuro
        btnVolver.setOnMouseEntered(e -> btnVolver.setStyle(
                "-fx-background-color: #4A6C85;" +   // azul más oscuro al pasar el mouse
                        "-fx-text-fill: white;" +
                        "-fx-font-size: 14px;" +
                        "-fx-font-weight: bold;" +
                        "-fx-background-radius: 6;" +
                        "-fx-padding: 8 15 8 15;"
        ));

    // restaurar estilo al salir
        btnVolver.setOnMouseExited(e -> btnVolver.setStyle(
                "-fx-background-color: #5A7D9A;" +   // vuelve al azul original
                        "-fx-text-fill: white;" +
                        "-fx-font-size: 14px;" +
                        "-fx-font-weight: bold;" +
                        "-fx-background-radius: 6;" +
                        "-fx-padding: 8 15 8 15;"
        ));


        btnVolver.setOnAction(e -> onBack.run());

        topBar.getChildren().addAll( btnVolver);
        //topBar.getChildren().add(container);
        VBox container = new VBox(10);
        container.getChildren().addAll(topBar, tituloWrapper);
        return container;
    }
}
