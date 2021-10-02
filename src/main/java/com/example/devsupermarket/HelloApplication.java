package com.example.devsupermarket;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import view.Login;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        new Login().start(stage);
    }

    public static void main(String[] args) {
        launch();
    }
}