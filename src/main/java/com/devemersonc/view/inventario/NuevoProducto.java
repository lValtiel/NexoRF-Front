package com.devemersonc.view.inventario;

import com.devemersonc.controller.ProductController;
import com.devemersonc.model.CreateUpdateProductDTO;
import com.devemersonc.model.ProductResponseDTO;
import com.devemersonc.model.ValidationErrorProductDTO;
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
        modal.setTitle("Nuevo Producto");
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

        Label errorName = new Label();

        Label errorSku = new Label();

        Label errorAmount = new Label();

        Label errorLocation = new Label();

        errorName.setMaxWidth(Double.MAX_VALUE);
        errorSku.setMaxWidth(Double.MAX_VALUE);
        errorAmount.setMaxWidth(Double.MAX_VALUE);
        errorLocation.setMaxWidth(Double.MAX_VALUE);

        errorName.getStyleClass().add("error-label");
        errorSku.getStyleClass().add("error-label");
        errorAmount.getStyleClass().add("error-label");
        errorLocation.getStyleClass().add("error-label");

        gridPane.add(new Label("Nombre"), 0,0);
        gridPane.add(txtName, 1, 0);
        gridPane.add(errorName, 1, 1);

        gridPane.add(new Label("SKU"), 0, 2);
        gridPane.add(txtSKU, 1, 2);
        gridPane.add(errorSku, 1, 3);

        gridPane.add(new Label("Cantidad"), 0, 4);
        gridPane.add(txtAmount, 1, 4);
        gridPane.add(errorAmount, 1, 5);

        gridPane.add(new Label("Ubicación"), 0, 6);
        gridPane.add(txtLocation, 1, 6);
        gridPane.add(errorLocation, 1, 7);

        gridPane.add(btnGuardar, 1, 8);

        //Guardar cambios
        btnGuardar.setOnAction(e -> {
            try {

                errorName.setText("");
                errorSku.setText("");
                errorAmount.setText("");
                errorLocation.setText("");

                String sku = txtSKU.getText().trim();
                String name = txtName.getText().trim();
                String amountText = txtAmount.getText().trim();
                Integer amount = null;
                String location = txtLocation.getText().trim();

                try {
                    if(!amountText.isBlank()) {
                        amount = Integer.parseInt(amountText);
                    }
                }catch (NumberFormatException exception) {
                    errorAmount.setText("Debe ser un número válido");
                    return;
                }

                CreateUpdateProductDTO createUpdateProductDTO = new CreateUpdateProductDTO(sku, name, amount, location);

                ValidationErrorProductDTO errors = productController.createProduct(createUpdateProductDTO);

                if(errors != null) {
                    if(errors.getName() != null) {
                        errorName.setText(errors.getName());
                    }

                    if(errors.getSku() != null) {
                        errorSku.setText(errors.getSku());
                    }

                    if(errors.getAmount() != null) {
                        errorAmount.setText(errors.getAmount());
                    }

                    if(errors.getLocation() != null) {
                        errorLocation.setText(errors.getLocation());
                    }
                    return;
                }

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
