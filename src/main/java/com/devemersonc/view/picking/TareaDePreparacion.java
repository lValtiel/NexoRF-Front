package com.devemersonc.view.picking;

import com.devemersonc.controller.NavigationController;
import com.devemersonc.controller.OrderController;
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

import java.util.List;

public class TareaDePreparacion {
    private final OrderController orderController = new OrderController();

    public GridPane getSceneTareaPrep() {
        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.TOP_LEFT);
        gridPane.setHgap(15);
        gridPane.setVgap(20);
        gridPane.setPadding(new Insets(25));
        gridPane.getStyleClass().add("contenedor");

        List<OrderResponseDTO> ordenes = orderController.getOrders();

        OrderResponseDTO ordenSeleccionada = null;

        for(OrderResponseDTO order : ordenes) {
            if("PENDIENTE".equalsIgnoreCase(order.getState())) {
                ordenSeleccionada = order;
                break;
            }
        }

        SessionManager.setCurrentOrder(ordenSeleccionada);

        Label titulo = new Label("TAREA DE PREPARACION");
        Label tituloNumOrder = new Label("N° de orden:");
        tituloNumOrder.getStyleClass().add("titulos");
        Label numOrder = new Label(ordenSeleccionada.getNumOrder());
        numOrder.setPrefWidth(140);
        numOrder.setPadding(new Insets(0,0,0,5));
        numOrder.getStyleClass().add("tabla");

        Label tituloNumTareas = new Label("N° de tareas:");
        tituloNumTareas.getStyleClass().add("titulos");
        Label numTareas = new Label(Integer.toString(ordenSeleccionada.getOrderLines().size()));
        numTareas.setPrefWidth(140);
        numTareas.setPadding(new Insets(0,5,0,5));
        numTareas.getStyleClass().add("tabla");

        Label tituloEstado = new Label("Estado:");
        tituloEstado.getStyleClass().add("titulos");
        Label estado = new Label(ordenSeleccionada.getState());
        estado.setPrefWidth(140);
        estado.setPadding(new Insets(0,5,0,5));
        estado.getStyleClass().add("tabla");

        Button btnEntrar = new Button("Entrar");
        GridPane.setHalignment(btnEntrar, HPos.RIGHT);
        Button btnSalir = new Button("Volver");

        btnEntrar.setOnAction(e -> {
            OrderResponseDTO currentOrder = SessionManager.getCurrentOrder();
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
