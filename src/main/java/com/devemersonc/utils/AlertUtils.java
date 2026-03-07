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
}
