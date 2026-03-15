package com.devemersonc.view;

import com.devemersonc.controller.NavigationController;
import com.devemersonc.model.OrderResponseDTO;
import com.devemersonc.service.OrderService;
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;

import java.time.format.DateTimeFormatter;
import java.util.List;

public class OrdersView {
    private final OrderService orderService = new OrderService();
    private final DateTimeFormatter formatter =
            DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
    private TableView<OrderResponseDTO> table = new TableView<>();
    private BorderPane layout = new BorderPane();

    public OrdersView() {
        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY_FLEX_LAST_COLUMN);
        table.getColumns().clear();

        TableColumn<OrderResponseDTO, String> colNum = new TableColumn<>("Número");
        colNum.setCellValueFactory(c -> new javafx.beans.property.SimpleStringProperty(c.getValue().getNumOrder()));

        TableColumn<OrderResponseDTO, String> colFecha = new TableColumn<>("Fecha creación");
        colFecha.setCellValueFactory(c ->
                new SimpleStringProperty(
                        c.getValue().getCreatedAt().format(formatter)
                ));

        TableColumn<OrderResponseDTO, String> colEstado = new TableColumn<>("Estado");
        //colEstado.setCellValueFactory(c -> new javafx.beans.property.SimpleStringProperty(c.getValue().getState()));
        colEstado.setCellValueFactory(cellData ->
                new SimpleStringProperty(cellData.getValue().getState())
        );

        colEstado.setCellFactory(tc -> new TableCell<>() {
            @Override
            protected void updateItem(String estado, boolean empty) {
                super.updateItem(estado, empty);

                if (empty || estado == null) {
                    setGraphic(null);
                    setText(null);
                } else {
                    Label lblEstado = new Label(estado);

                    // círculo indicador
                    javafx.scene.shape.Circle circle = new javafx.scene.shape.Circle(6); // radio 6px
                    if (estado.equalsIgnoreCase("Pendiente")) {
                        circle.setFill(javafx.scene.paint.Color.RED);
                        lblEstado.setTextFill(javafx.scene.paint.Color.WHITE);

                    } else if (estado.equalsIgnoreCase("Completado")) {
                        lblEstado.setTextFill(javafx.scene.paint.Color.WHITE);

                        circle.setFill(javafx.scene.paint.Color.GREEN);
                    } else {
                        lblEstado.setTextFill(javafx.scene.paint.Color.WHITE);
                    }

                    HBox box = new HBox(5, lblEstado, circle);
                    box.setAlignment(Pos.CENTER);

                    setGraphic(box);
                    setText(null); // importante: no usar setText junto con setGraphic
                }
            }
        });

        TableColumn<OrderResponseDTO, Void> colAccion = new TableColumn<>("Acción");
        colAccion.setCellFactory(tc -> new TableCell<>() {
            private final Button btnVer = new Button("Ver detalles");
            private final HBox contenedor = new HBox(5, btnVer);

            {
                contenedor.setAlignment(Pos.CENTER);

                btnVer.setOnAction(e -> {
                    OrderResponseDTO order = getTableView().getItems().get(getIndex());
                    NavigationController.getInstance().showOrderDetailsView(order);
                });
            }

            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                if(empty) {
                    setGraphic(null);
                }else {
                    setGraphic(contenedor);
                }
            }
        });

        table.getColumns().addAll(colNum, colFecha, colEstado, colAccion);

        layout.setCenter(table);
        refreshTable();
    }

    public BorderPane getView() {
        return layout;
    }

    public void refreshTable() {
        try {

            List<OrderResponseDTO> orders = orderService.getOrders();

            table.setItems(
                    FXCollections.observableArrayList(orders)
            );

            table.refresh();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
