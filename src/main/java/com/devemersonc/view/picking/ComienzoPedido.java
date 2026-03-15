package com.devemersonc.view.picking;

import com.devemersonc.controller.OrderController;
import com.devemersonc.model.SessionManager;
import com.devemersonc.utils.AlertUtils;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import com.devemersonc.model.OrderResponseDTO;
import com.devemersonc.model.OrderLineResponseDTO;
import com.devemersonc.controller.ProductController;
import com.devemersonc.model.ProductResponseDTO;
import com.devemersonc.view.InventarioView;
import com.devemersonc.controller.NavigationController;

import java.awt.*;
import java.util.List;

public class ComienzoPedido {
    private final OrderController orderController = new OrderController();
    private final ProductController productController = new ProductController();
    private List<OrderLineResponseDTO> orderLines;
    private int tareaActual = 0;

    public GridPane getComienzoPedidoView() {
        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.TOP_LEFT);
        gridPane.setHgap(15);
        gridPane.setVgap(20);
        gridPane.setPadding(new Insets(25));
        gridPane.getStyleClass().add("contenedor");

        ColumnConstraints col1 = new ColumnConstraints();
        col1.setPrefWidth(100);
        col1.setMinWidth(100);
        col1.setMaxWidth(100);

        ColumnConstraints col2 = new ColumnConstraints();
        col2.setPrefWidth(200);
        col2.setMinWidth(150);
        col2.setMaxWidth(250);

        ColumnConstraints col3 = new ColumnConstraints();
        col3.setPrefWidth(80);
        col3.setMinWidth(50);
        col3.setMaxWidth(100);

        ColumnConstraints col4 = new ColumnConstraints();
        col4.setPrefWidth(80);
        col4.setMinWidth(50);
        col4.setMaxWidth(100);

        ColumnConstraints col5 = new ColumnConstraints();
        col5.setPrefWidth(80);
        col5.setMinWidth(50);
        col5.setMaxWidth(100);

        ColumnConstraints col6 = new ColumnConstraints();
        col6.setPrefWidth(80);
        col6.setMinWidth(50);
        col6.setMaxWidth(100);

        ColumnConstraints col7 = new ColumnConstraints();
        col7.setPrefWidth(80);
        col7.setMinWidth(50);
        col7.setMaxWidth(100);

        gridPane.getColumnConstraints().addAll(col1, col2, col3, col4, col5, col6, col7);

        OrderResponseDTO currentOrder = SessionManager.getCurrentOrder();

        if(currentOrder == null || currentOrder.getOrderLines() == null || currentOrder.getOrderLines().isEmpty()) {
            Label error = new Label("No hay tareas para esta orden");
            gridPane.add(error, 0,0);
            return gridPane;
        }

        orderLines = currentOrder.getOrderLines();

        OrderLineResponseDTO tarea = orderLines.get(tareaActual);

        Label numOrdenText = new Label("N°ord. ");
        Label numOrden = new Label(currentOrder != null ? currentOrder.getNumOrder() : "");
        numOrden.setPrefWidth(200);
        numOrden.getStyleClass().add("tabla");

        Label ubicacionText = new Label("Ubica.");
        Label ubicacion = new Label(tarea.getLocation());
        ubicacion.setPrefWidth(200);
        ubicacion.getStyleClass().add("tabla");
        Label ubicacionFieldText = new Label("Cod. ubic.");
        TextField cod_ubicacion = new TextField();

        Label nombreProductoText = new Label("Nom. prod.");
        Label nombreProducto = new Label("");
        Label espacio = new Label("   ");

        Label cantidadText = new Label("Ctd.");
        Label cantidad = new Label("");
        cantidad.getStyleClass().add("tabla");
        cantidad.setPrefWidth(200);
        Label cantidadFieldText = new Label("Ctd.");
        TextField entrada_cantidad = new TextField();

        Button btnSiguiente = new Button("Siguiente");

        cod_ubicacion.textProperty().addListener((observable, oldValue, newValue) -> {

            OrderLineResponseDTO tareaActualLinea = orderLines.get(tareaActual);

            if(newValue.equals(tareaActualLinea.getLocation())) {
                nombreProducto.setText(tareaActualLinea.getName());
                cantidad.setText(String.valueOf(tareaActualLinea.getQuantity()));
            }else {
                nombreProducto.setText("");
                cantidad.setText("");
            }
        });

        btnSiguiente.setOnAction(e -> {

            try {
                OrderLineResponseDTO tareaActualLinea = orderLines.get(tareaActual);
                int cantidadIngresada = Integer.parseInt(entrada_cantidad.getText());

                if(cantidadIngresada == tareaActualLinea.getQuantity()) {
                    productController.pickProduct(tareaActualLinea.getProductId(), cantidadIngresada);
                    NavigationController.getInstance().refreshInventarioTable();

                    tareaActual++;

                    if(tareaActual >= orderLines.size()) {
                        AlertUtils.showInfo();
                        btnSiguiente.setDisable(true);
                        return;
                    }

                    OrderLineResponseDTO siguiente = orderLines.get(tareaActual);

                    ubicacion.setText(siguiente.getLocation());
                    nombreProducto.setText("");
                    cantidad.setText("");

                    cod_ubicacion.clear();
                    entrada_cantidad.clear();
                }else {
                    AlertUtils.errorQuantity("Cantidad incorrecta");
                }
            }catch (NumberFormatException exception) {
                AlertUtils.errorQuantity("Cantidad inválida");
            }
        });

        gridPane.add(numOrdenText, 0, 0);
        gridPane.add(numOrden, 1, 0);

        gridPane.add(ubicacionText, 0, 1);
        gridPane.add(ubicacion, 1, 1);

        gridPane.add(ubicacionFieldText, 0, 2);
        gridPane.add(cod_ubicacion, 1, 2);

        gridPane.add(nombreProductoText, 0, 3);
        gridPane.add(nombreProducto, 1, 3);

        gridPane.add(espacio, 0, 4);

        gridPane.add(cantidadText, 0, 5);
        gridPane.add(cantidad, 1, 5);
        gridPane.add(cantidadFieldText, 0, 6);
        gridPane.add(entrada_cantidad, 1, 6);

        gridPane.add(btnSiguiente, 1, 7);
        GridPane.setHalignment(btnSiguiente, HPos.RIGHT);

        return gridPane;
    }
}
