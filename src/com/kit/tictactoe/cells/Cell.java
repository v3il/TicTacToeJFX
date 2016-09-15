package com.kit.tictactoe.cells;

import javafx.geometry.Pos;
import javafx.scene.control.Label;

import static com.kit.tictactoe.Constants.CELL_HEIGHT;
import static com.kit.tictactoe.Constants.CELL_WIDTH;

public class Cell extends Label {

    private CellState currentCellState;
    private CellPosition positionOnBoard;

    public Cell(CellPosition positionOnBoard) {
        this.positionOnBoard = positionOnBoard;

        setCellState(new EmptyCellState());

        setPrefSize(CELL_WIDTH, CELL_HEIGHT);
        setMinSize(CELL_WIDTH, CELL_HEIGHT);
        setMaxSize(CELL_WIDTH, CELL_HEIGHT);

        setAlignment(Pos.CENTER);

        getStyleClass().add("cell");
    }

    public CellState getCurrentCellState() {
        return currentCellState;
    }

    public void setCurrentCellState(CellState currentCellState) {
        this.currentCellState = currentCellState;
    }

    public void selectCell(CellState cellState) {
        setCellState(cellState);
        getStyleClass().addAll("selected");
        setDisable(true);
    }

    private void setCellState(CellState cellState) {
        getStyleClass().addAll(cellState.getLabelClass());
        setText(cellState.getLabelText());
        currentCellState = cellState;
    }

    public CellPosition getPositionOnBoard() {
        return positionOnBoard;
    }
}
