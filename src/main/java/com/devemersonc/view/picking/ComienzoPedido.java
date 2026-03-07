package com.devemersonc.view.picking;

import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;

import java.awt.*;

public class ComienzoPedido {

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
        col4.setPrefWidth(80);
        col4.setMinWidth(50);
        col4.setMaxWidth(100);

        ColumnConstraints col6 = new ColumnConstraints();
        col4.setPrefWidth(80);
        col4.setMinWidth(50);
        col4.setMaxWidth(100);

        ColumnConstraints col7 = new ColumnConstraints();
        col4.setPrefWidth(80);
        col4.setMinWidth(50);
        col4.setMaxWidth(100);

        gridPane.getColumnConstraints().addAll(col1, col2, col3, col4, col5, col6, col7);

        Label numOrdenText = new Label("N°ord. ");
        Label numOrden = new Label("0000001434");
        numOrden.setPrefWidth(200);
        numOrden.getStyleClass().add("tabla");

        Label ubicacionText = new Label("Ubica.");
        Label ubicacion = new Label("R73 - 14- 10M");
        ubicacion.setPrefWidth(200);
        ubicacion.getStyleClass().add("tabla");
        Label ubicacionFieldText = new Label("Cod. ubic.");
        TextField cod_ubicacion = new TextField();

        Label nombreProductoText = new Label("Nom. prod.");
        Label nombreProducto = new Label("TUTTE TOALLAS HUMEDAS BEBE X 80 UN");
        Label espacio = new Label("   ");

        Label udmText = new Label("Udm");
        Label udm = new Label("CAJA");
        udm.getStyleClass().add("tabla");
        udm.setPrefWidth(200);

        Label cantidadText = new Label("Ctd.");
        Label cantidad = new Label("15");
        cantidad.getStyleClass().add("tabla");
        cantidad.setPrefWidth(200);
        Label cantidadFieldText = new Label("Ctd.");
        TextField entrada_cantidad = new TextField();

        Button btnSiguiente = new Button("Siguiente");

        gridPane.add(numOrdenText, 0, 0);
        gridPane.add(numOrden, 1, 0);
        gridPane.add(ubicacionText, 0, 1);
        gridPane.add(ubicacion, 1, 1);
        gridPane.add(ubicacionFieldText, 0, 2);
        gridPane.add(cod_ubicacion, 1, 2);
        gridPane.add(nombreProductoText, 0, 3);
        gridPane.add(nombreProducto, 1, 3);
        gridPane.add(espacio, 0, 4);
        gridPane.add(udmText, 0, 5);
        gridPane.add(udm, 1, 5);
        gridPane.add(cantidadText, 0, 6);
        gridPane.add(cantidad, 1, 6);
        gridPane.add(cantidadFieldText, 0, 7);
        gridPane.add(entrada_cantidad, 1, 7);
        gridPane.add(btnSiguiente, 1, 8);
        GridPane.setHalignment(btnSiguiente, HPos.RIGHT);

        return gridPane;
    }
}
