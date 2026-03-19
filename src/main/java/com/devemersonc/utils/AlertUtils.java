package com.devemersonc.utils;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

import java.util.Optional;

public class AlertUtils {

    public static void showNumberFormatError(String campo) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error de formato");
        alert.setHeaderText("Valor inválido en el campo: " + campo);
        alert.showAndWait();
    }

    public static boolean showOptionsEliminate() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Eliminar Producto");
        alert.setHeaderText("¿Seguro que desea eliminar este producto?");
        alert.setContentText("Esta acción no se puede deshacer");

        Optional<ButtonType> result = alert.showAndWait();
        return result.isPresent() && result.get() == ButtonType.OK;
    }

    public static void productNotFound(String sku) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText("Producto " + sku + " no encontrado.");
        alert.showAndWait();
    }

    public static void enterValueError() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText("Debe ingresar un valor");
        alert.showAndWait();
    }

    public static void deleteSkuEntered() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText("Procura antes borrar el código ingresado.");
        alert.showAndWait();
    }

    public static void errorTyped() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText("Error de tipado, verifica el valor ingresado o la opcion elegida.");
        alert.showAndWait();
    }

    public static void userSuccessfullyRegistered() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Registro exitoso");
        alert.setHeaderText(null);
        alert.setContentText("Usuario registrado con éxito");
        alert.showAndWait();
    }

    public static boolean showInfo() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(null);
        alert.setContentText("Pedido terminado");

        Optional<ButtonType> result = alert.showAndWait();
        return result.isPresent() && result.get() == ButtonType.OK;
    }

    public static void errorQuantity(String string) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setHeaderText(null);
        alert.setContentText(string);
        alert.showAndWait();
    }

    public static void error() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setHeaderText(null);
        alert.setContentText("Ocurrió un error inesperado.");
        alert.showAndWait();
    }

    public static boolean confirmLogout() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Cerrar Sesión");
        alert.setHeaderText("¿Seguro que desea cerrar sesión?");
        alert.setContentText("Se cerrará la sesión actual.");

        ButtonType btnSi = new ButtonType("Si");
        ButtonType btnNo = new ButtonType("No");

        alert.getButtonTypes().setAll(btnSi, btnNo);

        Optional<ButtonType> result = alert.showAndWait();

        return result.isPresent() && result.get() == btnSi;
    }

    public static void apiConnectionError() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error de conexión");
        alert.setHeaderText("No se pudo conectar al servidor");
        alert.setContentText("Verifique que el backend esté ejecutándose.");
        alert.show();
    }

    public static void error409(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setContentText(message);
        alert.showAndWait();
    }
}