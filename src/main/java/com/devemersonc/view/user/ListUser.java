package com.devemersonc.view.user;

import com.devemersonc.controller.NavigationController;
import com.devemersonc.controller.UserController;
import com.devemersonc.model.UserResponseDTO;
import com.devemersonc.utils.AlertUtils;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.util.Optional;

public class ListUser {
    private final UserController userController = new UserController();

    public void listUsers() {
        Stage modal = new Stage();
        modal.setTitle("Lista de usuarios");
        modal.initModality(Modality.APPLICATION_MODAL);

        BorderPane borderPane = new BorderPane();
        borderPane.setPadding(new Insets(15));

        //Tabla usuarios
        TableView<UserResponseDTO> tablaUsers = new TableView<>();
        tablaUsers.setMaxWidth(800);
        tablaUsers.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY_FLEX_LAST_COLUMN);
        BorderPane.setAlignment(tablaUsers, Pos.CENTER);

        //ComboBox (select)



        //Columnas
        TableColumn<UserResponseDTO, Void> colID = new TableColumn<>("N°");
        colID.setCellFactory(tc -> new TableCell<>(){
                @Override
                protected void updateItem(Void item, boolean empty) {
                    super.updateItem(item, empty);
                    if(empty) {
                        setText(null);
                    } else {
                        setText(String.valueOf(getIndex() + 1));
                    }
                }
            }
        );

        TableColumn<UserResponseDTO, String> colUsername = new TableColumn<>("Nombre de usuario");
        colUsername.setCellValueFactory(c -> new javafx.beans.property.SimpleStringProperty(c.getValue().getUsername()));

        TableColumn<UserResponseDTO, Void> colAcciones = new TableColumn<>("Acciones");
        colAcciones.setCellFactory(tc -> new TableCell<>(){
            private final Button btnEditar = new Button("Editar");
            private final Button btnEliminar = new Button("Eliminar");
            private final HBox contenedor = new HBox(5, btnEditar, btnEliminar);

            {
                contenedor.setAlignment(Pos.CENTER);

                btnEditar.setOnAction(e -> {
                    UserResponseDTO selected = getTableView().getItems().get(getIndex());

                    EditUserView editUserView = new EditUserView(selected, tablaUsers);
                    Scene root = new Scene(editUserView.formEditUser(), 800, 600);
                    root.getStylesheets().add(getClass().getResource("/css/formEditUser.css").toExternalForm());
                    modal.setScene(root);
                });

                btnEliminar.setOnAction(e -> {
                    UserResponseDTO selected = getTableView().getItems().get(getIndex());

                    if(selected == null){
                        return;
                    }

                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setTitle("Confirmación");
                    alert.setHeaderText("Eliminar usuario");
                    alert.setContentText("¿Seguro que desea eliminar a este usuario?");

                    Optional<ButtonType> result = alert.showAndWait();

                    if(result.isPresent() && result.get() == ButtonType.OK) {
                        try {
                            userController.deleteUser(selected.getId());
                            tablaUsers.getItems().remove(selected);
                        }catch (Exception exception) {
                            exception.printStackTrace();

                            Alert error = new Alert(Alert.AlertType.ERROR);
                            error.setTitle("Error");
                            error.setHeaderText("No se pudo eliminar el usuario");
                            error.setContentText(exception.getMessage());
                            error.showAndWait();
                        }
                    }
                });
            }

            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                if(empty) {
                    setGraphic(null);
                }else {
                    setGraphic(contenedor);
                }
            }
        });

        tablaUsers.getColumns().addAll(colID, colUsername, colAcciones);

        //Datos
        try {
            ObservableList<UserResponseDTO> data = FXCollections.observableArrayList(userController.getUsers());
            tablaUsers.setItems(data);
        }catch (Exception exception) {
            exception.printStackTrace();
        }

        //Poner tabla en centro de borderpane
        borderPane.setCenter(tablaUsers);

        Label titutlo = new Label("Lista de usuarios");
        titutlo.getStyleClass().add("titulo");

        VBox root = new VBox(15);
        root.setAlignment(Pos.TOP_CENTER);
        root.setPadding(new Insets(80, 0,0,0));
        root.getChildren().addAll(titutlo, borderPane);

        Scene scene = new Scene(root, 800, 600);
        modal.setScene(scene);
        scene.getStylesheets().add(getClass().getResource("/css/listUser.css").toExternalForm());
        modal.showAndWait();
    }
}
