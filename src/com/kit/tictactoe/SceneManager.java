package com.kit.tictactoe;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class SceneManager {

    private static Map<Integer, Scene> scenes;

    public static final Integer TITLE_SCENE_ID = 1;
    public static final Integer GAME_SCENE_ID = 2;

    static {
        scenes = new HashMap<>();

        try {
            Parent root = FXMLLoader.load(SceneManager.class.getResource("/title_scene_content.fxml"));

            if(root != null) {
                scenes.put(TITLE_SCENE_ID, new Scene(root));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static Scene getSceneById(Integer sceneId) {
        return scenes.get(sceneId);
    }
}
