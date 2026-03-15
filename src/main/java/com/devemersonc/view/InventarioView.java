package com.devemersonc.view;

import com.devemersonc.controller.NavigationController;
import com.devemersonc.controller.ProductController;
import com.devemersonc.utils.AlertUtils;
import com.devemersonc.model.CreateUpdateProductDTO;
import com.devemersonc.model.ProductResponseDTO;
import com.devemersonc.view.inventario.TopBarFactory;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;

public class InventarioView {
    private final ProductController productController = new ProductController();
    private TableView<ProductResponseDTO> tablaProductos;

    private Scene scene;
    private BorderPane root;

    // *** IMPORTANTE ***
    private VBox topContainer; // <--- ahora es atributo de clase

    public Scene getScene() {
        if (scene == null) {
            buildUI();
            scene = new Scene(root, 1250, 720);
            scene.getStylesheets().add(getClass().getResource("/css/inventario.css").toExternalForm());
        }
        return scene;
    }

    public BorderPane getRoot() {
        return root;
    }

    // Nuevo getter para que NavigationController pueda restaurar la barra original
    public VBox getTopContainer() {
        return topContainer;
    }

    private void buildUI() {
        root = new BorderPane();
        root.setPadding(new Insets(15));

        // Tabla productos
        tablaProductos = new TableView<>();
        tablaProductos.setMaxWidth(1200);
        tablaProductos.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY_FLEX_LAST_COLUMN);
        BorderPane.setAlignment(tablaProductos, Pos.CENTER);

        Button btnCrearProducto = new Button("Nuevo producto");
        btnCrearProducto.setOnAction(e -> NavigationController.getInstance().showFormCreateProduct(tablaProductos));

        // ComboBox filtro
        ComboBox<String> filtro = new ComboBox<>();
        filtro.getItems().addAll("Todos los productos", "Código", "Nombre", "Ubicación");
        filtro.setValue("Todos los productos");

        Label tituloLista = new Label("Lista Productos");
        tituloLista.getStyleClass().add("titulo-lista");
        HBox tituloWrapper = new HBox(tituloLista);
        tituloWrapper.setPadding(new Insets(0,0,20,0));
        tituloWrapper.setAlignment(Pos.CENTER);

        // Input búsqueda
        TextField busquedaField = new TextField();
        busquedaField.setPromptText("Ingrese valor...");

        // Botón buscar
        Button btnBuscar = new Button("Buscar");

        // Botón crear usuario
        Button btnCrearUsuario = new Button("Crear nuevo Usuario");
        btnCrearUsuario.setOnAction(e -> NavigationController.getInstance().showFormRegisterUser());

        // Botón lista de usuarios
        Button btnObtenerUsuarios = new Button("Lista de usuarios");
        btnObtenerUsuarios.setOnAction(e -> NavigationController.getInstance().showListUsers());

        btnBuscar.setOnAction(e -> {
            String criterio = filtro.getValue();
            String valor = busquedaField.getText();

            try {
                if (criterio.equals("Todos los productos")) {
                    if (valor.isBlank()) {
                        tablaProductos.setItems(FXCollections.observableArrayList(productController.loadAllProducts()));
                    } else {
                        AlertUtils.deleteSkuEntered();
                    }
                }
                else {
                    ProductResponseDTO dto = productController.loadProductBySku(valor);

                    if ((criterio.equals("Nombre") && dto.getName().equalsIgnoreCase(valor)) ||
                            (criterio.equals("Código") && dto.getSku().equalsIgnoreCase(valor))) {

                        tablaProductos.setItems(FXCollections.observableArrayList(dto));
                    }
                }
            } catch (Exception ex) {
                AlertUtils.productNotFound(valor);
            }
        });

        // *** TOP BAR 2 ***
        HBox topBar2 = new HBox(10);
        topBar2.setPadding(new Insets(10, 0, 20, 0));
        topBar2.setAlignment(Pos.TOP_CENTER);
        topBar2.getChildren().addAll(btnCrearProducto, filtro, busquedaField, btnBuscar, btnCrearUsuario, btnObtenerUsuarios);

        // *** AHORA topContainer ES UN ATRIBUTO ***
        topContainer = new VBox(10);
        topContainer.getChildren().addAll(topBar2, tituloWrapper);
        topBar2.setAlignment(Pos.CENTER);

        // Columnas tabla
        TableColumn<ProductResponseDTO, Void> colID = new TableColumn<>("N°");
        colID.setCellFactory(tc -> new TableCell<>() {
            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                setText(empty ? null : String.valueOf(getIndex() + 1));
            }
        });

        TableColumn<ProductResponseDTO, String> colSKU = new TableColumn<>("SKU");
        colSKU.setCellValueFactory(c -> new javafx.beans.property.SimpleStringProperty(c.getValue().getSku()));

        TableColumn<ProductResponseDTO, String> colNombre = new TableColumn<>("Nombre");
        colNombre.setCellValueFactory(c -> new javafx.beans.property.SimpleStringProperty(c.getValue().getName()));

        TableColumn<ProductResponseDTO, String> colCantidad = new TableColumn<>("Cantidad");
        colCantidad.setCellValueFactory(c -> new javafx.beans.property.SimpleStringProperty(String.valueOf(c.getValue().getAmount())));

        TableColumn<ProductResponseDTO, String> colUbicacion = new TableColumn<>("Ubicación");
        colUbicacion.setCellValueFactory(c -> new javafx.beans.property.SimpleStringProperty(c.getValue().getLocation()));

        TableColumn<ProductResponseDTO, Void> colAcciones = new TableColumn<>("Acciones");
        colAcciones.setCellFactory(tc -> new TableCell<>() {
            private Button btnEditar = new Button("Editar");
            private Button btnEliminar = new Button("Eliminar");
            private HBox cont = new HBox(5, btnEditar, btnEliminar);

            {
                btnEditar.setStyle("-fx-background-color: #2f6f4e; -fx-text-fill: white;");

                btnEditar.setOnMouseEntered(e ->
                        btnEditar.setStyle("-fx-background-color: #25563c; -fx-text-fill: white;")
                );

                btnEditar.setOnMouseExited(e ->
                        btnEditar.setStyle("-fx-background-color: #2f6f4e; -fx-text-fill: white;")
                );
                btnEliminar.setStyle("-fx-background-color: #B22222");
                btnEliminar.setOnMouseEntered(e ->
                        btnEliminar.setStyle("-fx-background-color: #8B0000; -fx-text-fill: white;")
                );

                btnEliminar.setOnMouseExited(e ->
                        btnEliminar.setStyle("-fx-background-color: #B22222; -fx-text-fill: white;")
                );
            }

            {
                cont.setAlignment(Pos.CENTER);

                btnEditar.setOnAction(e -> {
                    ProductResponseDTO sel = getTableView().getItems().get(getIndex());
                    CreateUpdateProductDTO dto = new CreateUpdateProductDTO(
                            sel.getSku(), sel.getName(), sel.getAmount(), sel.getLocation()
                    );
                    NavigationController.getInstance().showFormProductEdit(sel.getId(), dto, tablaProductos);
                });

            }

            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                setGraphic(empty ? null : cont);
            }
        });

        tablaProductos.getColumns().addAll(colID, colSKU, colNombre, colCantidad, colUbicacion, colAcciones);

        // Cargar datos
        try {
            tablaProductos.setItems(FXCollections.observableArrayList(productController.loadAllProducts()));
        } catch (Exception e) {
            e.printStackTrace();
        }

        VBox contenedorTop = new VBox();
        contenedorTop.getChildren().addAll(TopBarFactory.createTopBar(), topContainer);
        root.setTop(contenedorTop); // <-- ESTA ES LA BARRA ORIGINAL
        root.setCenter(tablaProductos);
    }

    public void restoreOriginalTopBar() {
        VBox contenedorTop = new VBox();
        contenedorTop.getChildren().addAll(
                TopBarFactory.createTopBar(),
                topContainer
        );
        root.setTop(contenedorTop);
    }

    public void restoreInventarioTable() {
        try {
            tablaProductos.setItems(FXCollections.observableArrayList(
                    productController.loadAllProducts()
            ));

            root.setCenter(tablaProductos); // ← Usamos la tabla ORIGINAL
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void refreshTable() {

        if(tablaProductos == null){
            return;
        }

        try {

            tablaProductos.setItems(
                    FXCollections.observableArrayList(
                            productController.loadAllProducts()
                    )
            );

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}