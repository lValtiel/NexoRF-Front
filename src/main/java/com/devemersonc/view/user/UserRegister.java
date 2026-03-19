package com.devemersonc.view.user;

import com.devemersonc.controller.UserController;
import com.devemersonc.model.CreateUser;
import com.devemersonc.model.RegisterUserRequest;
import com.devemersonc.model.RoleRequest;
import com.devemersonc.model.ValidationErrorUserDTO;
import com.devemersonc.utils.AlertUtils;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.util.Map;

public class UserRegister {
    private final UserController userController = new UserController();

    public void getViewFormRegisterUser() {
        Stage modal = new Stage();
        modal.setTitle("Registrar Usuario");
        modal.initModality(Modality.APPLICATION_MODAL);

        ComboBox<String> roles = new ComboBox<>();
        roles.getItems().addAll("ADMIN");
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

        Label errorUsername = new Label();
        errorUsername.setMaxWidth(Double.MAX_VALUE);
        errorUsername.getStyleClass().add("error-label");

        Label errorName = new Label();
        errorName.setMaxWidth(Double.MAX_VALUE);
        errorName.getStyleClass().add("error-label");

        Label errorLastName = new Label();
        errorLastName.setMaxWidth(Double.MAX_VALUE);
        errorLastName.getStyleClass().add("error-label");

        Label errorPassword = new Label();
        errorPassword.setMaxWidth(Double.MAX_VALUE);
        errorPassword.getStyleClass().add("error-label");

        Label errorEmail = new Label();
        errorEmail.setMaxWidth(Double.MAX_VALUE);
        errorEmail.getStyleClass().add("error-label");

        Label errorRut = new Label();
        errorRut.setMaxWidth(Double.MAX_VALUE);
        errorRut.getStyleClass().add("error-label");

        gridPane.add(new Label("Rol"), 0,0);
        gridPane.add(roles, 1,0);

        gridPane.add(new Label("Nombre de usuario"), 0,1);
        gridPane.add(usernameTxt, 1,1);
        gridPane.add(errorUsername, 1,2);

        gridPane.add(new Label("Nombre"), 0,3);
        gridPane.add(nameTxt, 1,3);
        gridPane.add(errorName, 1,4);

        gridPane.add(new Label("Apellido"), 0,5);
        gridPane.add(lastNameTxt, 1,5);
        gridPane.add(errorLastName, 1,6);

        gridPane.add(new Label("Contraseña"), 0,7);
        gridPane.add(passwordTxt, 1,7);
        gridPane.add(errorPassword, 1,8);

        gridPane.add(new Label("Correo electrónico"), 0,9);
        gridPane.add(emailTxt, 1,9);
        gridPane.add(errorEmail, 1,10);

        gridPane.add(new Label("Rut"), 0,11);
        gridPane.add(rutTxt, 1,11);
        gridPane.add(errorRut, 1,12);

        gridPane.add(btnGuardar, 1,13);

        //Boton registrar usuario
        btnGuardar.setOnAction(e -> {
            try {
                errorUsername.setText("");
                errorName.setText("");
                errorLastName.setText("");
                errorPassword.setText("");
                errorEmail.setText("");
                errorRut.setText("");


                String username = usernameTxt.getText().trim();
                String name = nameTxt.getText().trim();
                String lastName = lastNameTxt.getText().trim();
                String password = passwordTxt.getText().trim();
                String email = emailTxt.getText().trim();
                String rut = rutTxt.getText().trim();


                CreateUser createUser = new CreateUser(username, name, lastName, password, email, rut);

                String roleName = roles.getValue();

                if(roleName.equals("ADMIN")) {
                    roleName = "ROLE_ADMIN";
                }

                RoleRequest roleRequest = new RoleRequest(roleName);

                RegisterUserRequest registerUserRequest = new RegisterUserRequest(createUser, roleRequest);
                Map<String, String> errors = userController.createUser(registerUserRequest);

                if(errors != null) {
                    for(Map.Entry<String, String> entry : errors.entrySet()) {
                        String key = entry.getKey().replace("user.", "");
                        String message = entry.getValue();

                        switch (key) {
                            case "username":
                                errorUsername.setText(message);
                                break;
                            case "name":
                                errorName.setText(message);
                                break;
                            case "lastName":
                                errorLastName.setText(message);
                                break;
                            case "password":
                                errorPassword.setText(message);
                                break;
                            case "email":
                                errorEmail.setText(message);
                                break;
                            case "rut":
                                errorRut.setText(message);
                                break;
                        }
                    }
                    return;
                }

                //alerta de usuario creado
                AlertUtils.userSuccessfullyRegistered();
                modal.close();
            }catch (Exception exception) {
                AlertUtils.error409(exception.getMessage());
            }
        });

        Scene root = new Scene(gridPane,400, 600);
        root.getStylesheets().add(getClass().getResource("/css/newUser.css").toExternalForm());
        modal.setScene(root);
        modal.showAndWait();
        modal.close();
    }
}
