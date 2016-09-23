package com.kit.tictactoe;

import com.kit.tictactoe.cells.Cell;
import com.kit.tictactoe.cells.CellPosition;
import com.kit.tictactoe.cells.CircleCellState;
import com.kit.tictactoe.cells.CrossCellState;
import javafx.event.EventTarget;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

import static com.kit.tictactoe.Constants.COLS;
import static com.kit.tictactoe.Constants.ROWS;

public class Game {

    private VBox mainContainer;
    private Board board;

    private PlayerQueue playerQueue;
    private Player player1, player2;

    private List<Label> nicknameLabels;

    public Game(String firstPlayerNick, String secondPlayerNick) {
        mainContainer = new VBox();
        nicknameLabels = new ArrayList<>();
        board = new Board(ROWS, COLS);

        player1 = new Player(firstPlayerNick, new CrossCellState());
        player2 = new Player(secondPlayerNick, new CircleCellState());

        playerQueue = new PlayerQueue(new Player[] {player1, player2});

        mainContainer.getStylesheets().add(getClass().getResource("/style.css").toExternalForm());
        mainContainer.getChildren().add(createHeader());
        mainContainer.getChildren().add(board);

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Результати гри");
        alert.setHeaderText("");

        board.setOnMouseClicked(event -> {
            EventTarget clickTarget = event.getTarget();

            if(clickTarget instanceof Cell) {
                Cell clickedCell = (Cell) clickTarget;
                Player currentPlayer = playerQueue.getCurrentPlayer();
                clickedCell.selectCell(currentPlayer.getPlayerMarkStyleId());

                if(hasWinner(clickedCell)) {
                    alert.setContentText(currentPlayer.toString() + " переміг!");
                    alert.showAndWait();

                    ((Stage) board.getScene().getWindow()).setScene(SceneManager.getSceneById(SceneManager.TITLE_SCENE_ID));
                    return;
                }

                if(isDraw()) {
                    alert.setContentText("Нічия!");
                    alert.showAndWait();

                    ((Stage) board.getScene().getWindow()).setScene(SceneManager.getSceneById(SceneManager.TITLE_SCENE_ID));
                    return;
                }

                playerQueue.changePlayer();
                markActivePlayerNameLabel();
            }
        });

        markActivePlayerNameLabel();
    }

    private void markActivePlayerNameLabel() {
        for(Label label: nicknameLabels) {
            label.getStyleClass().remove("active-player");
        }

        nicknameLabels.get(playerQueue.getCurrentPlayerIndex()).getStyleClass().add("active-player");
    }

    private Parent createHeader() {
        BorderPane header = new BorderPane();

        Label firstPlayerNicknameLabel = new Label(player1.toString());
        Label secondPlayerNicknameLabel = new Label(player2.toString());

        header.setLeft(firstPlayerNicknameLabel);
        header.setRight(secondPlayerNicknameLabel);

        nicknameLabels.add(firstPlayerNicknameLabel);
        nicknameLabels.add(secondPlayerNicknameLabel);

        return header;
    }

    private boolean checkDirection(int directionToCheck, Cell clickedCell) {
        switch(directionToCheck) {
            case Constants.HORIZONTAL_POSITION:
                return checkHorizontal(clickedCell);
            case Constants.VERTICAL_POSITION:
                return checkVertical(clickedCell);
            case Constants.PRIMARY_DIAGONAL_POSITION:
                return checkPrimaryDiagonal(clickedCell);
            case Constants.SECONDARY_DIAGONAL_POSITION:
                return checkSecondaryDiagonal(clickedCell);
        }

        return false;
    }

    private boolean checkHorizontal(Cell clickedCell) {
        boolean resultInHorizontal = true;
        CellPosition clickedCellPosition = clickedCell.getPositionOnBoard();
        int cellPositionInRow = clickedCellPosition.getRowPosition();
        int clickedCellStateId = clickedCell.getCurrentCellState().getCellStateId();

        for(int colCount = 0; colCount < COLS; colCount++) {
            Cell currentCellInHorizontal = board.getCellAtPosition(cellPositionInRow, colCount);

            resultInHorizontal =
                       resultInHorizontal
                    && clickedCellStateId
                    == currentCellInHorizontal.getCurrentCellState().getCellStateId();
        }

        return resultInHorizontal;
    }

    private boolean checkVertical(Cell clickedCell) {
        boolean resultInVertical = true;
        CellPosition clickedCellPosition = clickedCell.getPositionOnBoard();
        int cellPositionInColumn = clickedCellPosition.getColPosition();
        int clickedCellStateId = clickedCell.getCurrentCellState().getCellStateId();

        for(int rowCount = 0; rowCount < ROWS; rowCount++) {
            Cell currentCellInVertical = board.getCellAtPosition(rowCount, cellPositionInColumn);

            resultInVertical =
                       resultInVertical
                    && clickedCellStateId
                    == currentCellInVertical.getCurrentCellState().getCellStateId();
        }

        return resultInVertical;
    }

    private boolean checkPrimaryDiagonal(Cell clickedCell) {
        boolean resultInDiagonal = true;
        int clickedCellStateId = clickedCell.getCurrentCellState().getCellStateId();
        int cellIndex = 0;

        while(cellIndex < ROWS) {
            Cell currentCellInDiagonal = board.getCellAtPosition(cellIndex, cellIndex);

            resultInDiagonal =
                       resultInDiagonal
                    && clickedCellStateId
                    == currentCellInDiagonal.getCurrentCellState().getCellStateId();

            cellIndex++;
        }

        return resultInDiagonal;
    }

    private boolean checkSecondaryDiagonal(Cell clickedCell) {
        boolean resultInDiagonal = true;
        int clickedCellStateId = clickedCell.getCurrentCellState().getCellStateId();
        int columnIndex = COLS - 1;

        while(columnIndex >= 0) {
            int rowIndex = COLS - (columnIndex + 1);
            Cell currentCellInDiagonal = board.getCellAtPosition(rowIndex, columnIndex);

            resultInDiagonal =
                    resultInDiagonal
                            && clickedCellStateId
                            == currentCellInDiagonal.getCurrentCellState().getCellStateId();

            columnIndex--;
        }

        return resultInDiagonal;
    }

    private boolean hasWinner(Cell clickedCell) {
        CellPosition cellPositionOnBoard = clickedCell.getPositionOnBoard();

        int rowPosition = cellPositionOnBoard.getRowPosition();
        int colPosition = cellPositionOnBoard.getColPosition();

        boolean isOnPrimaryDiagonal = (rowPosition == colPosition);
        boolean isOnSecondaryDiagonal = (rowPosition == COLS - (colPosition + 1));

        // Завжди перевіряємо вертикаль і горизонталь
        boolean hasWinner =
                   checkDirection(Constants.HORIZONTAL_POSITION, clickedCell)
                || checkDirection(Constants.VERTICAL_POSITION, clickedCell);

        if(isOnPrimaryDiagonal) {
            hasWinner =
                       hasWinner
                    || checkDirection(Constants.PRIMARY_DIAGONAL_POSITION, clickedCell);
        }

        if(isOnSecondaryDiagonal) {
            hasWinner =
                       hasWinner
                    || checkDirection(Constants.SECONDARY_DIAGONAL_POSITION, clickedCell);
        }

        return hasWinner;
    }

    private boolean isDraw() {
        return board.allCellsAreFilled();
    }

    public Parent getRootElement() {
        return mainContainer;
    }
}
