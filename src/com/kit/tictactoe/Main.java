package com.kit.tictactoe;

import javafx.application.Application;
import javafx.geometry.Rectangle2D;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds();

        primaryStage.setX(primaryScreenBounds.getMinX() + 500);
        primaryStage.setY(primaryScreenBounds.getMinY() + 250);

        primaryStage.setTitle("TicTacToe JavaFX");
        primaryStage.setScene(SceneManager.getSceneById(SceneManager.TITLE_SCENE_ID));
        primaryStage.setResizable(false);
        primaryStage.show();
    }
}
