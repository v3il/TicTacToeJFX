package com.kit.tictactoe;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/title_scene_content.fxml"));
        Scene scene = new Scene(root);

        primaryStage.setTitle("TicTacToe JavaFX");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
