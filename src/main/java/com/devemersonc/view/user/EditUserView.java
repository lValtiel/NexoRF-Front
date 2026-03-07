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

public class EditUserView {
    private final UserResponseDTO userSelected;
    private final TableView<UserResponseDTO> tablaUsers;
    private final UserController userController = new UserController();

    public EditUserView(UserResponseDTO userSelected, TableView<UserResponseDTO> tablaUsers) {
        this.userSelected = userSelected;
        this.tablaUsers = tablaUsers;
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
        Button btnVolver = new Button("Volver");
        Button btnGuardar = new Button("Guardar");
        GridPane.setHalignment(btnGuardar, HPos.RIGHT);

        btnVolver.setOnAction(e -> {
            NavigationController.getInstance().showListUsers();
        });

        btnGuardar.setOnAction(e -> {
            System.out.println(userSelected.getId());
            try {
                CreateUser createUser = new CreateUser(
                        usernameTxt.getText(),
                        nameTxt.getText(),
                        lastNameTxt.getText(),
                        emailTxt.getText(),
                        rutTxt.getText()
                );
                userController.updateUser(userSelected.getId(), createUser);

                tablaUsers.setItems(
                        FXCollections.observableArrayList(userController.getUsers())
                );

                tablaUsers.refresh();
                NavigationController.getInstance().showListUsers();
            }catch (Exception exception) {
                System.out.println("Error");
            }
        });

        gridPane.add(new Label("Nombre de usuario"), 0,0);
        gridPane.add(usernameTxt, 1, 0);

        gridPane.add(new Label("Nombre"), 0, 1);
        gridPane.add(nameTxt, 1,1);

        gridPane.add(new Label("Apellido"), 0, 2);
        gridPane.add(lastNameTxt, 1, 2);

        gridPane.add(new Label("Correo electrónico"), 0 , 3);
        gridPane.add(emailTxt, 1, 3);

        gridPane.add(new Label("Rut"), 0, 4);
        gridPane.add(rutTxt, 1, 4);

        gridPane.add(btnVolver, 0, 5);
        gridPane.add(btnGuardar, 1, 5);

        Label titutlo = new Label("Actualizar usuario");
        titutlo.getStyleClass().add("titulo");

        VBox root = new VBox(15);
        root.setAlignment(Pos.TOP_CENTER);
        root.setPadding(new Insets(80, 0,0,0));
        root.getChildren().addAll(titutlo, gridPane);

        return root;
    }
}