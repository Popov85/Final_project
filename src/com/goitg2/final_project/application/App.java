package com.goitg2.final_project.application;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Entry point
 * @author Andrii Popov
 */

public class App extends Application {

        public static void main(String[] args) {
                launch(args);
        }

        @Override
        public void start(Stage primaryStage) throws IOException {
                Parent root = FXMLLoader.load(getClass().getResource("../forms/UserForm.fxml"));
                primaryStage.setTitle("Validator");
                primaryStage.setScene(new Scene(root, 600, 262));
                primaryStage.show();
        }
}
