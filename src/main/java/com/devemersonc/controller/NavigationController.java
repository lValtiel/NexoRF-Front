package com.devemersonc.controller;

import com.devemersonc.model.CreateUpdateProductDTO;
import com.devemersonc.model.OrderResponseDTO;
import com.devemersonc.model.ProductResponseDTO;
import com.devemersonc.model.Producto;
import com.devemersonc.view.*;
import com.devemersonc.view.inventario.NuevoProducto;
import com.devemersonc.view.inventario.TopBarFactory;
import com.devemersonc.view.order.OrderDetailsView;
import com.devemersonc.view.picking.MenuPrincipalPicking;
import com.devemersonc.view.user.EditUserView;
import com.devemersonc.view.user.ListUser;
import com.devemersonc.view.user.UserRegister;
import javafx.application.Platform;
import javafx.scene.Node;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import com.devemersonc.service.OrderService;

public class NavigationController {

    private static NavigationController instance;
    private Stage stage;

    private InventarioView inventarioView;
    private OrdersView ordersView;

    public static NavigationController getInstance() {
        if (instance == null) instance = new NavigationController(){};
        return instance;
    }

    public void setStage(Stage s) {
        this.stage = s;
    }

    // Mostrar login
    public void showLogin() {
        stage.setTitle("Inicio de sesión");
        stage.setScene(new LoginView().getScene());
    }

    // Mostrar Inventario con su barra ORIGINAL
    public void showInventario() {

        if (inventarioView == null) {
            inventarioView = new InventarioView();
        }

        stage.setScene(inventarioView.getScene());

        // restaurar barra original
        inventarioView.restoreOriginalTopBar();

        // restaurar tabla de inventario
        inventarioView.restoreInventarioTable();
    }

    // Cambiar centro de Inventario
    public void setContent(Node node) {
        if (inventarioView != null) {
            inventarioView.getRoot().setCenter(node);
        }
    }

    // Cambiar SOLO la barra superior (Top) TEMPORALMENTE
    public void setTop(Node node) {
        if (inventarioView != null) {
            inventarioView.getRoot().setTop(node);
        }
    }

    public void showUpdateProducto(Producto producto, TableView<Producto> table) {
        new UpdateProductView().showUpdateModal(producto, table);
    }

    public void showFormProductEdit(Long product_id, CreateUpdateProductDTO dto, TableView<ProductResponseDTO> tabla) {
        new EditProductView(product_id, dto, tabla).showFormEditProduct();
    }

    public void showFormCreateProduct(TableView<ProductResponseDTO> tabla) {
        new NuevoProducto(tabla).showFormNuevoProducto();
    }

    public void showFormRegisterUser() {
        new UserRegister().getViewFormRegisterUser();
    }

    public void showListUsers() {
        new ListUser().listUsers();
    }

    public void showOrderDetailsView(OrderResponseDTO orderResponseDTO) {
        new OrderDetailsView().getDetailsOrder(orderResponseDTO);
    }

    public void refreshInventarioTable() {

        if (inventarioView != null) {
            inventarioView.refreshTable();
        }
    }

    public void refreshOrderTable() {

        Platform.runLater(() -> {

            if (ordersView != null) {

                ordersView.refreshTable();

                setContent(ordersView.getView());

            }
        });
    }
    public void showOrders() {

        if (ordersView == null) {
            ordersView = new OrdersView();
        }

        ordersView.refreshTable();
        setContent(ordersView.getView());
    }
}