package com.devemersonc.view;

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
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

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

        gridPane.add(new Label("Nombre"), 0, 0);
        gridPane.add(txtName, 1, 0);

        gridPane.add(new Label("SKU"), 0, 1);
        gridPane.add(txtSku, 1, 1);

        gridPane.add(new Label("Cantidad"), 0, 2);
        gridPane.add(txtAmount, 1, 2);

        gridPane.add(new Label("Ubicación"), 0, 3);
        gridPane.add(txtLocation, 1, 3);

        gridPane.add(btnSave, 1,4);

        //Guardar cambios
        btnSave.setOnAction(e -> {
            try {
                createUpdateProductDTO.setName(txtName.getText());
                createUpdateProductDTO.setSku(txtSku.getText());
                createUpdateProductDTO.setAmount(Integer.parseInt(txtAmount.getText()));
                createUpdateProductDTO.setLocation(txtLocation.getText());
                productController.updateProduct(product_id, createUpdateProductDTO);

                tabla.setItems(
                        FXCollections.observableArrayList(productController.loadAllProducts())
                );

                tabla.refresh();
                modal.close();
            }catch (Exception ex) {
                System.out.println("Error");
            }
        });

        Scene scene = new Scene(gridPane, 400, 600);
        scene.getStylesheets().add(getClass().getResource("/css/formEditProduct.css").toExternalForm());

        modal.setScene(scene);
        modal.showAndWait();
        modal.close();
    }
}
