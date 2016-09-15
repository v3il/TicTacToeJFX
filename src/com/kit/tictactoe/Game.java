package com.kit.tictactoe;

import com.kit.tictactoe.cells.Cell;
import com.kit.tictactoe.cells.CellPosition;
import com.kit.tictactoe.cells.CircleCellState;
import com.kit.tictactoe.cells.CrossCellState;
import javafx.event.EventTarget;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

import static com.kit.tictactoe.Constants.COLS;
import static com.kit.tictactoe.Constants.ROWS;

public class Game {

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

        player1 = new Player(firstPlayerNick, new CrossCellState());
        player2 = new Player(secondPlayerNick, new CircleCellState());

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
        CellPosition cellPositionOnBoard = clickedCell.getPositionOnBoard();

        int rowPos = cellPositionOnBoard.getRowPosition();
        int colPos = cellPositionOnBoard.getColPosition();

        boolean isMiddleCell = rowPos == colPos;

        boolean isWinningHorizontal = true;

        for(int colCount = 0; colCount < COLS; colCount++) {
            Cell currentCellInHorizontal = board.getCellAtPosition(rowPos, colCount);
            isWinningHorizontal = isWinningHorizontal
                    && clickedCell.getCurrentCellState().getCellStateId()
                    == currentCellInHorizontal.getCurrentCellState().getCellStateId();
        }

        boolean isWinningVertical = true;

        for(int rowCount = 0; rowCount < ROWS; rowCount++) {
            Cell currentCellInVertical = board.getCellAtPosition(rowCount, colPos);
            isWinningVertical = isWinningVertical
                    && clickedCell.getCurrentCellState().getCellStateId()
                    == currentCellInVertical.getCurrentCellState().getCellStateId();
        }

        boolean isGameOver = isWinningHorizontal || isWinningVertical;

        if(isMiddleCell) {
            boolean isWinningDiagonal = true;
            int cellIndex = 0;

            while(cellIndex < ROWS) {
                Cell currentCellInDiagonal = board.getCellAtPosition(cellIndex, cellIndex);
                isWinningDiagonal = isWinningDiagonal
                        && clickedCell.getCurrentCellState().getCellStateId()
                        == currentCellInDiagonal.getCurrentCellState().getCellStateId();

                cellIndex++;
            }

            isGameOver = isGameOver || isWinningDiagonal;
        }

        System.out.println(isGameOver);

        return isGameOver;
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
