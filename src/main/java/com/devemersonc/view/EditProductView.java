package com.devemersonc.view;

import com.devemersonc.controller.ProductController;
import com.devemersonc.model.CreateUpdateProductDTO;
import com.devemersonc.model.ProductResponseDTO;
import com.devemersonc.model.ValidationErrorProductDTO;
import javafx.collections.FXCollections;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import com.devemersonc.utils.AlertUtils;

public class EditProductView {
    private Long product_id;
    private final CreateUpdateProductDTO createUpdateProductDTO;
    private final ProductController productController = new ProductController();
    private final TableView<ProductResponseDTO> tabla;

    public EditProductView(Long product_id, CreateUpdateProductDTO createUpdateProductDTO, TableView<ProductResponseDTO> tabla) {
        this.product_id = product_id;
        this.createUpdateProductDTO = createUpdateProductDTO;
        this.tabla = tabla;
    }

    public void showFormEditProduct() {
        Stage modal = new Stage();
        modal.setTitle("Editar producto");
        modal.initModality(Modality.APPLICATION_MODAL);

        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setPadding(new Insets(20));
        gridPane.setVgap(10);
        gridPane.setHgap(10);

        TextField txtName = new TextField(createUpdateProductDTO.getName());
        txtName.getStyleClass().add("textfield");
        TextField txtSku = new TextField(createUpdateProductDTO.getSku());
        txtSku.getStyleClass().add("textfield");
        TextField txtAmount = new TextField(createUpdateProductDTO.getAmount().toString());
        txtAmount.getStyleClass().add("textfield");
        TextField txtLocation = new TextField(createUpdateProductDTO.getLocation());
        txtLocation.getStyleClass().add("textfield");
        Button btnSave = new Button("Guardar cambios");
        GridPane.setHalignment(btnSave, HPos.RIGHT);

        Label errorName = new Label();
        errorName.setMaxWidth(Double.MAX_VALUE);
        errorName.getStyleClass().add("error-label");

        Label errorSku = new Label();
        errorSku.setMaxWidth(Double.MAX_VALUE);
        errorSku.getStyleClass().add("error-label");

        Label errorAmount = new Label();
        errorAmount.setMaxWidth(Double.MAX_VALUE);
        errorAmount.getStyleClass().add("error-label");

        Label errorLocation = new Label();
        errorLocation.setMaxWidth(Double.MAX_VALUE);
        errorLocation.getStyleClass().add("error-label");

        gridPane.add(new Label("Nombre"), 0, 0);
        gridPane.add(txtName, 1, 0);
        gridPane.add(errorName, 1,1);

        gridPane.add(new Label("SKU"), 0, 2);
        gridPane.add(txtSku, 1, 2);
        gridPane.add(errorSku, 1,3);

        gridPane.add(new Label("Cantidad"), 0, 4);
        gridPane.add(txtAmount, 1, 4);
        gridPane.add(errorAmount, 1,5);

        gridPane.add(new Label("Ubicación"), 0, 6);
        gridPane.add(txtLocation, 1, 6);
        gridPane.add(errorLocation, 1,7);

        gridPane.add(btnSave, 1,8);

        //Guardar cambios
        btnSave.setOnAction(e -> {
            try {

                errorName.setText("");
                errorSku.setText("");
                errorAmount.setText("");
                errorLocation.setText("");

                String amountText = txtAmount.getText().trim();
                Integer amount = null;

                try {
                    if(!amountText.isBlank()) {
                        amount = Integer.parseInt(amountText);
                    }
                }catch (NumberFormatException exception){
                    errorAmount.setText("Debe ser un número válido");
                }

                createUpdateProductDTO.setName(txtName.getText());
                createUpdateProductDTO.setSku(txtSku.getText());
                createUpdateProductDTO.setAmount(amount);
                createUpdateProductDTO.setLocation(txtLocation.getText());
                ValidationErrorProductDTO errors = productController.updateProduct(product_id, createUpdateProductDTO);

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
            }catch (Exception ex) {
                AlertUtils.error409(ex.getMessage());
            }
        });

        Scene scene = new Scene(gridPane, 400, 600);
        scene.getStylesheets().add(getClass().getResource("/css/formEditProduct.css").toExternalForm());

        modal.setScene(scene);
        modal.showAndWait();
        modal.close();
    }
}
