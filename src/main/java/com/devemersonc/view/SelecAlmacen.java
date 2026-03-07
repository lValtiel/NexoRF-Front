package com.devemersonc.view;

import com.devemersonc.controller.NavigationController;
import com.devemersonc.view.picking.MenuPrincipalPicking;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

public class SelecAlmacen {

    public Parent getSelecAlmacen() {

        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.TOP_LEFT);
        gridPane.setHgap(15);
        gridPane.setVgap(20);
        gridPane.setPadding(new Insets(20));
        gridPane.getStyleClass().add("contenedor");

        Label seleccAlmacen = new Label("Selecc. almacén");
        seleccAlmacen.getStyleClass().add("label");
        Label opcion = new Label("0 CD");
        opcion.getStyleClass().add("label");

        TextField numField = new TextField();
        Button btnSiguiente = new Button("Siguiente");

        gridPane.add(seleccAlmacen, 0,0);
        gridPane.add(opcion, 0,1);
        gridPane.add(numField,0, 2);
        gridPane.add(btnSiguiente, 0, 3);
        GridPane.setHalignment(btnSiguiente, HPos.RIGHT);

        btnSiguiente.setOnAction(e -> {
            String text = numField.getText();
            try {
                int numIntField = Integer.parseInt(text);
                if(numIntField == 0) {
                    MenuPrincipalPicking menuPrincipalPicking = new MenuPrincipalPicking();
                    Scene currentScene = btnSiguiente.getScene();
                    currentScene.setRoot(menuPrincipalPicking.getSceneMenu());
                }else {
                    System.out.println("Solo recibe 0");
                }
            }catch (NumberFormatException ex) {

            }
        });
        return gridPane;
    }
}
