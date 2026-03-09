package com.devemersonc.view;

import com.devemersonc.model.Producto;
import com.devemersonc.utils.AlertUtils;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class UpdateProductView {

    public void showUpdateModal(Producto producto, TableView<Producto> tabla) {
        /*Stage modal = new Stage();
        modal.setTitle("Actualizar producto");
        modal.initModality(Modality.APPLICATION_MODAL);

        VBox layout = new VBox(15);
        layout.setAlignment(Pos.CENTER);

        Label skuLabel = new Label("SKU: ");
        TextField skuField = new TextField(producto.getSku());

        Label nombreLabel = new Label("Nombre: ");
        TextField nombreField = new TextField(producto.getNombre());

        Label cantidadLabel = new Label("Cantidad: ");
        TextField cantidadField = new TextField(Integer.toString(producto.getCantidad()));

        Label ubicacionLabel = new Label("Ubicación: ");
        TextField ubicacionField = new TextField(producto.getUbicacion());

        Button btnGuardarCambios = new Button("Guardar");
        btnGuardarCambios.setOnAction(e -> {
            try {
                producto.setSku(skuField.getText());
                producto.setNombre(nombreField.getText());
                producto.setCantidad(Integer.parseInt(cantidadField.getText()));
                producto.setUbicacion(ubicacionField.getText());

                tabla.refresh();
                modal.close();
            }catch (NumberFormatException ex) {
                AlertUtils.showNumberFormatError("Cantidad");
            }
        });

        layout.getChildren().addAll(skuLabel, skuField, nombreLabel, nombreField, cantidadLabel, cantidadField, ubicacionLabel, ubicacionField, btnGuardarCambios);

        Scene scene = new Scene(layout, 400, 300) ;
        modal.setScene(scene);
        modal.showAndWait();*/
    }
}
