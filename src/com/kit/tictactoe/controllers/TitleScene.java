package com.kit.tictactoe.controllers;

import com.kit.tictactoe.Game;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class TitleScene {

    @FXML
    public TextField firstPlayerNickname;

    @FXML
    public TextField secondPlayerNickname;

    @FXML
    public Button startGameBtn;

    @FXML
    public void initialize() {
        validateNicknames();
    }

    public void startGame(ActionEvent actionEvent) {
        Button clickedButton = (Button) actionEvent.getTarget();
        Game newGame = new Game(firstPlayerNickname.getText(), secondPlayerNickname.getText());

        Scene gameScene = new Scene(newGame.getRootElement());
        ((Stage) clickedButton.getScene().getWindow()).setScene(gameScene);
    }

    public void validateNicknames() {
        String firstPlayerNick = firstPlayerNickname.getText();
        String secondPlayerNick = secondPlayerNickname.getText();

        if(firstPlayerNick.equals("") || secondPlayerNick.equals("") || firstPlayerNick.equals(secondPlayerNick)) {
            startGameBtn.setDisable(true);
        } else {
            startGameBtn.setDisable(false);
        }
    }
}
