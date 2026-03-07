package com.devemersonc.view.inventario;

import com.devemersonc.controller.ProductController;
import com.devemersonc.model.CreateUpdateProductDTO;
import com.devemersonc.model.ProductResponseDTO;
import javafx.collections.FXCollections;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class NuevoProducto {
    private final ProductController productController = new ProductController();
    private final TableView<ProductResponseDTO> tabla;

    public NuevoProducto(TableView<ProductResponseDTO> tabla) {
        this.tabla = tabla;
    }

    public void showFormNuevoProducto() {
        Stage modal = new Stage();
        modal.setTitle("Nuevo producto");
        modal.initModality(Modality.APPLICATION_MODAL);

        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setVgap(10);
        gridPane.setHgap(10);
        gridPane.setPadding(new Insets(20));

        TextField txtName = new TextField();
        txtName.getStyleClass().add("textfield");
        TextField txtSKU = new TextField();
        txtSKU.getStyleClass().add("textfield");
        TextField txtAmount = new TextField();
        txtAmount.getStyleClass().add("textfield");
        TextField txtLocation = new TextField();
        txtLocation.getStyleClass().add("textfield");
        Button btnGuardar = new Button("Guardar");
        GridPane.setHalignment(btnGuardar, HPos.RIGHT);

        gridPane.add(new Label("Nombre"), 0,0);
        gridPane.add(txtName, 1, 0);

        gridPane.add(new Label("SKU"), 0, 1);
        gridPane.add(txtSKU, 1, 1);

        gridPane.add(new Label("Cantidad"), 0, 2);
        gridPane.add(txtAmount, 1, 2);

        gridPane.add(new Label("Ubicación"), 0, 3);
        gridPane.add(txtLocation, 1, 3);
        gridPane.add(btnGuardar, 1, 4);

        //Guardar cambios
        btnGuardar.setOnAction(e -> {
            try {
                String sku = txtSKU.getText();
                String name = txtName.getText();
                int amount = Integer.parseInt(txtAmount.getText());
                String location = txtLocation.getText();

                CreateUpdateProductDTO createUpdateProductDTO = new CreateUpdateProductDTO(sku, name, amount, location);

                productController.createProduct(createUpdateProductDTO);

                tabla.setItems(
                        FXCollections.observableArrayList(productController.loadAllProducts())
                );

                tabla.refresh();
                modal.close();
            }catch (Exception exception){
                exception.printStackTrace();
            }
        });

        Scene scene = new Scene(gridPane, 400, 600);
        scene.getStylesheets().add(getClass().getResource("/css/nuevoProducto.css").toExternalForm());
        modal.setScene(scene);
        modal.showAndWait();
        modal.close();
    }
}
