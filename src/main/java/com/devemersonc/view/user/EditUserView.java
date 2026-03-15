package com.devemersonc.view.user;

import com.devemersonc.controller.NavigationController;
import com.devemersonc.controller.UserController;
import com.devemersonc.model.CreateUser;
import com.devemersonc.model.UserResponseDTO;
import javafx.collections.FXCollections;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.Map;

public class EditUserView {
    private final UserResponseDTO userSelected;
    private final TableView<UserResponseDTO> tablaUsers;
    private final UserController userController = new UserController();
    private final Stage modal;

    public EditUserView(UserResponseDTO userSelected, TableView<UserResponseDTO> tablaUsers, Stage modal) {
        this.userSelected = userSelected;
        this.tablaUsers = tablaUsers;
        this.modal = modal;
    }

    public VBox formEditUser() {
        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setHgap(15);
        gridPane.setVgap(20);
        gridPane.setPadding(new Insets(20));

        TextField usernameTxt = new TextField(userSelected.getUsername());
        usernameTxt.getStyleClass().add("textfield");
        TextField nameTxt = new TextField(userSelected.getName());
        nameTxt.getStyleClass().add("textfield");
        TextField lastNameTxt = new TextField(userSelected.getLastName());
        lastNameTxt.getStyleClass().add("textfield");
        TextField emailTxt = new TextField(userSelected.getEmail());
        emailTxt.getStyleClass().add("textfield");
        TextField rutTxt = new TextField(userSelected.getRut());
        rutTxt.getStyleClass().add("textfield");
        Button btnGuardar = new Button("Guardar");
        GridPane.setHalignment(btnGuardar, HPos.RIGHT);

        Label errorUsername = new Label();
        errorUsername.setMaxWidth(Double.MAX_VALUE);
        errorUsername.getStyleClass().add("error-label");

        Label errorName = new Label();
        errorName.setMaxWidth(Double.MAX_VALUE);
        errorName.getStyleClass().add("error-label");

        Label errorLastName = new Label();
        errorLastName.setMaxWidth(Double.MAX_VALUE);
        errorLastName.getStyleClass().add("error-label");

        Label errorEmail = new Label();
        errorEmail.setMaxWidth(Double.MAX_VALUE);
        errorEmail.getStyleClass().add("error-label");

        Label errorRut = new Label();
        errorRut.setMaxWidth(Double.MAX_VALUE);
        errorRut.getStyleClass().add("error-label");

        gridPane.add(new Label("Nombre de usuario"), 0,0);
        gridPane.add(usernameTxt, 1, 0);
        gridPane.add(errorUsername, 1,1);

        gridPane.add(new Label("Nombre"), 0, 2);
        gridPane.add(nameTxt, 1,2);
        gridPane.add(errorName, 1,3);

        gridPane.add(new Label("Apellido"), 0, 4);
        gridPane.add(lastNameTxt, 1, 4);
        gridPane.add(errorLastName, 1,5);

        gridPane.add(new Label("Correo electrónico"), 0 , 6);
        gridPane.add(emailTxt, 1, 6);
        gridPane.add(errorEmail, 1, 7);

        gridPane.add(new Label("Rut"), 0, 8);
        gridPane.add(rutTxt, 1, 8);
        gridPane.add(errorRut, 1,9);

        gridPane.add(btnGuardar, 1, 10);

        Label titutlo = new Label("Actualizar usuario");
        titutlo.getStyleClass().add("titulo");

        btnGuardar.setOnAction(e -> {
            try {
                errorUsername.setText("");
                errorName.setText("");
                errorLastName.setText("");
                errorEmail.setText("");
                errorRut.setText("");

                CreateUser createUser = new CreateUser(
                        usernameTxt.getText(),
                        nameTxt.getText(),
                        lastNameTxt.getText(),
                        emailTxt.getText(),
                        rutTxt.getText()
                );
                Map<String, String> errors = userController.updateUser(userSelected.getId(), createUser);

                if(errors != null && !errors.isEmpty()) {
                    for (Map.Entry<String, String> entry : errors.entrySet()) {
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

                tablaUsers.setItems(
                        FXCollections.observableArrayList(userController.getUsers())
                );

                tablaUsers.refresh();

                modal.close();

                ListUser listUser = new ListUser();
                listUser.listUsers();
            }catch (Exception exception) {
                exception.printStackTrace();
            }
        });

        modal.setTitle("Actualizar Usuario");
        VBox root = new VBox(15);
        root.setAlignment(Pos.TOP_CENTER);
        root.setPadding(new Insets(80, 0,0,0));
        root.getChildren().addAll(titutlo, gridPane);

        return root;
    }
}