package com.devemersonc.view;

import com.devemersonc.controller.AuthController;
import com.devemersonc.controller.NavigationController;
import com.devemersonc.utils.AlertUtils;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

import java.awt.*;

public class LoginView {

    public Scene getScene() {
        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setHgap(10);
        gridPane.setVgap(10);

        Label title = new Label("Inicio de Sesión");
        title.getStyleClass().add("titulo");

        Label usernameLabel = new Label("Nombre de usuario:");
        TextField usernameField = new TextField();
        usernameLabel.getStyleClass().add("labels");
        usernameField.getStyleClass().add("textfield");

        Label passwordLabel = new Label("Contraseña:");
        PasswordField passwordField = new PasswordField();
        passwordLabel.getStyleClass().add("labels");
        passwordField.getStyleClass().add("textfield");

        Label errorLabel = new Label();
        errorLabel.setStyle("-fx-text-fill: red; -fx-font-size: 12;");

        Button btnLogin = new Button("Iniciar sesión");
        btnLogin.getStyleClass().add("btnLogin");

        //Redireccionar a Dashboard
        btnLogin.setOnAction(e -> {
            String username = usernameField.getText();
            String password = passwordField.getText();

            AuthController authController = new AuthController();

            boolean success = authController.login(username, password);

            if(success) {
                errorLabel.setVisible(false);
                NavigationController.getInstance().showInventario();
            }else {
                errorLabel.setText("Nombre de usuario o contraseña incorrecta.");
                errorLabel.setVisible(true);
            }
        });

        gridPane.add(usernameLabel, 0, 0);
        gridPane.add(usernameField,1 , 0);
        gridPane.add(passwordLabel, 0, 1);
        gridPane.add(passwordField, 1, 1);
        gridPane.add(errorLabel, 1,2);
        gridPane.add(btnLogin, 1, 3);

        GridPane.setHalignment(btnLogin, HPos.RIGHT);

        VBox contenedor = new VBox(150);
        contenedor.setAlignment(Pos.CENTER);
        contenedor.getChildren().addAll(title, gridPane);
        contenedor.getStyleClass().add("contenedor");
        contenedor.setMaxWidth(500);
        VBox.setMargin(title, new Insets(80, 0, 0,0));
        VBox.setMargin(gridPane, new Insets(0, 0, 150,0));

        VBox root = new VBox();
        root.setAlignment(Pos.CENTER);
        root.getChildren().add(contenedor);

        Scene scene = new Scene(root, 1250, 720);
        scene.getStylesheets().add(getClass().getResource("/css/style.css").toExternalForm());
        return scene;
    }
}
