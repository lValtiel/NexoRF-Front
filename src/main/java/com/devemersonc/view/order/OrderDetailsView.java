package com.devemersonc.view.order;

import com.devemersonc.model.OrderResponseDTO;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class OrderDetailsView {

    public void getDetailsOrder(OrderResponseDTO orderResponseDTO) {
        Stage modal = new Stage();
        modal.setTitle("Detalles de pedido");
        modal.initModality(Modality.APPLICATION_MODAL);

        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setVgap(10);
        gridPane.setHgap(10);
        gridPane.setPadding(new Insets(20));

        Label tituloOrden = new Label("Orden " + orderResponseDTO.getNumOrder());
        tituloOrden.getStyleClass().add("titulo-orden");
        GridPane.setMargin(tituloOrden, new Insets(0,0,20,0));

        Label orderNum = new Label("Numero de Orden: ");
        Label orderNumTxt = new Label(orderResponseDTO.getNumOrder());

        Label fechaCrea = new Label("Fecha de creación: ");
        Label fechaCreaTxt = new Label(orderResponseDTO.getCreatedAt().toString());

        Label estado = new Label("Estado");
        Label estadoTxt = new Label(orderResponseDTO.getState());

        Region espacio1 = new Region();
        Region espacio2 = new Region();

        Label detalles_de_pedido = new Label("Detalles del pedido: ");
        detalles_de_pedido.getStyleClass().add("detalles_de_pedido");
        GridPane.setMargin(detalles_de_pedido, new Insets(0,0,30,0));


        int rowIndex = 6; //Empezamos debajo del título
        int num = 1;
        for(var line: orderResponseDTO.getOrderLines()) {
            Region region = new Region();
            Label titulo = new Label("Producto " + num);
            titulo.getStyleClass().add("titulo-productos");
            Label skuLabel = new Label("SKU: ");
            Label skuValue = new Label(line.getSku());

            Label nameLabel = new Label("Nombre producto: ");
            Label nameValue = new Label(line.getName());

            Label qtyLabel = new Label("Cantidad: ");
            Label qtyValue = new Label(String.valueOf(line.getQuantity()));

            Label locLabel = new Label("Ubicación:");
            Label locValue = new Label(line.getLocation());

            Label stateLabel = new Label("Estado:");
            Label stateValue = new Label(line.getState());

            gridPane.add(region, 0, rowIndex++);
            gridPane.add(titulo,0, rowIndex++);
            gridPane.add(skuLabel, 0, rowIndex);
            gridPane.add(skuValue, 1, rowIndex++);
            gridPane.add(nameLabel, 0, rowIndex);
            gridPane.add(nameValue, 1, rowIndex++);
            gridPane.add(qtyLabel, 0, rowIndex);
            gridPane.add(qtyValue, 1, rowIndex++);
            gridPane.add(locLabel, 0, rowIndex);
            gridPane.add(locValue, 1, rowIndex++);
            gridPane.add(stateLabel, 0, rowIndex);
            gridPane.add(stateValue, 1, rowIndex++);

            rowIndex++;
            num++;
        }

        gridPane.add(tituloOrden, 0,0);

        gridPane.add(orderNum, 0,1);
        gridPane.add(orderNumTxt, 1,1);

        gridPane.add(fechaCrea, 0,2);
        gridPane.add(fechaCreaTxt, 1,2);

        gridPane.add(estado, 0,3);
        gridPane.add(estadoTxt, 1,3);

        gridPane.add(espacio1, 0,4);

        gridPane.add(espacio2, 0,5);

        gridPane.add(detalles_de_pedido, 0,6);

        ScrollPane scrollPane = new ScrollPane(gridPane);
        scrollPane.setFitToWidth(true);

        Scene root = new Scene(scrollPane
                , 600, 600);
        root.getStylesheets().add(getClass().getResource("/css/orderDetailsView.css").toExternalForm());
        modal.setScene(root);
        modal.showAndWait();
        modal.close();
    }
}
