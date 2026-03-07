package com.devemersonc.view.user;

import com.devemersonc.controller.UserController;
import com.devemersonc.model.CreateUser;
import com.devemersonc.model.RegisterUserRequest;
import com.devemersonc.model.RoleRequest;
import com.devemersonc.utils.AlertUtils;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class UserRegister {
    private final UserController userController = new UserController();

    public void getViewFormRegisterUser() {
        Stage modal = new Stage();
        modal.setTitle("Registrar nuevo usuario");
        modal.initModality(Modality.APPLICATION_MODAL);

        ComboBox<String> roles = new ComboBox<>();
        roles.getItems().addAll("ADMIN", "PICKER");
        roles.setValue("ADMIN");

        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setVgap(10);
        gridPane.setHgap(10);
        gridPane.setPadding(new Insets(20));

        TextField usernameTxt = new TextField();
        usernameTxt.getStyleClass().add("textfield");
        TextField nameTxt = new TextField();
        nameTxt.getStyleClass().add("textfield");
        TextField lastNameTxt = new TextField();
        lastNameTxt.getStyleClass().add("textfield");
        PasswordField passwordTxt = new PasswordField();
        passwordTxt.getStyleClass().add("textfield");
        TextField emailTxt = new TextField();
        emailTxt.getStyleClass().add("textfield");
        TextField rutTxt = new TextField();
        rutTxt.getStyleClass().add("textfield");
        Button btnGuardar = new Button("Guardar");
        GridPane.setHalignment(btnGuardar, HPos.RIGHT);
        GridPane.setHalignment(roles, HPos.RIGHT);

        gridPane.add(new Label("Rol"), 0,0);
        gridPane.add(roles, 1 ,0);

        gridPane.add(new Label("Nombre de usuario"), 0,1);
        gridPane.add(usernameTxt, 1,1);

        gridPane.add(new Label("Nombre"), 0,2);
        gridPane.add(nameTxt, 1,2);

        gridPane.add(new Label("Apellido"), 0,3);
        gridPane.add(lastNameTxt, 1,3);

        gridPane.add(new Label("Contraseña"), 0,4);
        gridPane.add(passwordTxt, 1,4);

        gridPane.add(new Label("Correo electrónico"), 0,5);
        gridPane.add(emailTxt, 1,5);

        gridPane.add(new Label("Rut"), 0,6);
        gridPane.add(rutTxt, 1,6);
        gridPane.add(btnGuardar, 1, 7);

        //Boton registrar usuario
        btnGuardar.setOnAction(e -> {
            try {
                String username = usernameTxt.getText();
                String name = nameTxt.getText();
                String lastName = lastNameTxt.getText();
                String password = passwordTxt.getText();
                String email = emailTxt.getText();
                String rut = rutTxt.getText();

                CreateUser createUser = new CreateUser(username, name, lastName, password, email, rut);

                String roleName = roles.getValue();

                if(roleName.equals("ADMIN")) {
                    roleName = "ROLE_ADMIN";
                }

                if(roleName.equals("PICKER")) {
                    roleName = "ROLE_PICKER";
                }

                RoleRequest roleRequest = new RoleRequest(roleName);

                RegisterUserRequest registerUserRequest = new RegisterUserRequest(createUser, roleRequest);
                userController.createUser(registerUserRequest);

                //alerta de usuario creado
                AlertUtils.userSuccessfullyRegistered();
                modal.close();
            }catch (Exception exception) {
                exception.printStackTrace();
            }
        });

        Scene root = new Scene(gridPane,400, 600);
        root.getStylesheets().add(getClass().getResource("/css/newUser.css").toExternalForm());
        modal.setScene(root);
        modal.showAndWait();
        modal.close();
    }
}
