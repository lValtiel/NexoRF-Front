package com.devemersonc;

import com.devemersonc.controller.NavigationController;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main extends Application {
    @Override
    public void start(Stage stage) {
        NavigationController.getInstance().setStage(stage);
        NavigationController.getInstance().showLogin();

        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}