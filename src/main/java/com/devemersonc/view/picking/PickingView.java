package com.devemersonc.view.picking;

import com.devemersonc.controller.AuthController;
import com.devemersonc.controller.NavigationController;
import com.devemersonc.view.IntroIDEquipoView;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class PickingView {

    public void showViewPicking() {
        Stage modal = new Stage();
        modal.setTitle("System");

        //GridPane para alinear etiquetas y campos
        GridPane gridPane = new GridPane();
        gridPane.getStyleClass().add("contenedor_picking");
        gridPane.setAlignment(Pos.TOP_LEFT);
        gridPane.setHgap(15); // espacio horizontal entre columnas
        gridPane.setVgap(20); // espacio vertical entre filas
        gridPane.setPadding(new Insets(20));

        Label usrLabel = new Label("USR");
        usrLabel.getStyleClass().add("usrLabel");
        TextField usrField = new TextField();
        usrField.setPrefWidth(200);

        Label cntrLabel = new Label("CNTR");
        cntrLabel.getStyleClass().add("cntrLabel");
        PasswordField cntrField = new PasswordField();
        usrField.setPrefWidth(200);

        Label errorLabel = new Label();
        errorLabel.setMaxWidth(Double.MAX_VALUE);
        errorLabel.getStyleClass().add("error-label");

        Button btnSiguiente = new Button("Entrar");

        btnSiguiente.setOnAction(e -> {
            String username = usrField.getText();
            String password = cntrField.getText();

            AuthController authController = new AuthController();

            boolean success = authController.login(username, password);

            if(success) {
                errorLabel.setVisible(false);
                IntroIDEquipoView introIDEquipoView = new IntroIDEquipoView();
                Scene scene = new Scene(introIDEquipoView.showIntroIDEquipoView(), 400, 500);
                scene.getStylesheets().add(getClass().getResource("/css/intro.css").toExternalForm());
                modal.setScene(scene);
            }else {
                errorLabel.setText("Nombre de usuario o contraseña incorrecta");
                errorLabel.setVisible(true);
            }
        });

        //Añadir al grid_ columna 0 = label, columna 1 = campo
        gridPane.add(usrLabel, 0, 0);
        gridPane.add(usrField, 1, 0);

        gridPane.add(cntrLabel, 0, 1);
        gridPane.add(cntrField, 1 ,1);
        gridPane.add(errorLabel, 1,2);

        gridPane.add(btnSiguiente,1,3);
        GridPane.setHalignment(btnSiguiente, HPos.RIGHT);

        Scene scene = new Scene(gridPane, 400, 500);
        scene.getStylesheets().add(getClass().getResource("/css/picking.css").toExternalForm());
        modal.setScene(scene);
        modal.showAndWait();
    }
}