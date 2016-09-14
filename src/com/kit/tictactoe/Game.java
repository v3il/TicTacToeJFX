package com.kit.tictactoe;

import com.sun.javafx.geom.Point2D;
import javafx.event.EventTarget;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Random;

public class Game {

    private static final int ROWS = 3;
    private static final int COLS = 3;

    private Board board;
    private String firstPlayerNick;
    private String secondPlayerNick;

    private PlayerQueue playerQueue;
    private Player player1, player2;

    public Game(String firstPlayerNick, String secondPlayerNick) {
        this.firstPlayerNick = firstPlayerNick;
        this.secondPlayerNick = secondPlayerNick;

        board = new Board(ROWS, COLS);
        board.getStylesheets().add(getClass().getResource("/style.css").toExternalForm());

        player1 = new Player(firstPlayerNick, CellStatesFactory.CROSS_CELL_ID);
        player2 = new Player(secondPlayerNick, CellStatesFactory.CIRCLE_CELL_ID);

        playerQueue = new PlayerQueue(new Player[] {player1, player2});

        board.setOnMouseClicked(event -> {
            EventTarget clickTarget = event.getTarget();

            if(clickTarget instanceof Cell) {
                Cell clickedCell = (Cell) clickTarget;
                Player currentPlayer = playerQueue.getCurrentPlayer();
                clickedCell.selectCell(currentPlayer.getPlayerMarkStyleId());

                if(isGameOver(clickedCell)) {
                    Parent root = null;

                    try {
                        root = FXMLLoader.load(getClass().getResource("/title_scene_content.fxml"));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    if(root == null) {
                        System.exit(0);
                    } else {
                        Scene scene = new Scene(root);
                        ((Stage) board.getScene().getWindow()).setScene(scene);
                    }
                } else {
                    playerQueue.changePlayer();
                }
            }
        });
    }

    private boolean isGameOver(Cell clickedCell) {
        // TODO: 14.09.16 Implement me
        
        CellPosition cellPositionOnBoard = clickedCell.getPositionOnBoard();

        int rowPos = cellPositionOnBoard.getRowPosition();
        int colPos = cellPositionOnBoard.getColPosition();

        return rowPos == colPos;
    }

    private boolean hasWinner() {
        return false;
    }

    private boolean isDraw() {
        return false;
    }

    public Board getRootElement() {
        return board;
    }
}
