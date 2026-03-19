package com.devemersonc;

import com.devemersonc.controller.NavigationController;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import com.devemersonc.utils.AlertUtils;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.sql.Connection;
import java.sql.DriverManager;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main extends Application {
    @Override
    public void start(Stage stage) {
        NavigationController.getInstance().setStage(stage);
        NavigationController.getInstance().showLogin();

        stage.getIcons().add(new Image(getClass().getResource("/images/icon.png").toExternalForm()));

        stage.show();

        if(!testApiConnection()) {
            AlertUtils.apiConnectionError();
        }
    }

    public static boolean testApiConnection() {
        try {

            HttpClient client = HttpClient.newHttpClient();

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create("http://localhost:8080/api/users"))
                    .GET()
                    .build();

            HttpResponse<String> response =
                    client.send(request, HttpResponse.BodyHandlers.ofString());

            // 200 = OK
            // 401 = requiere login pero el servidor está vivo
            return response.statusCode() == 200 || response.statusCode() == 401;

        } catch (Exception e) {
            return false;
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}