package com.kit.tictactoe;

import com.sun.javafx.geom.Point2D;
import javafx.geometry.Pos;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.layout.Border;
import javafx.scene.text.TextAlignment;

public class Cell extends Label {

    private final double cellWidth = 100d;
    private final double cellHeight = 100d;

    private CellState currentCellState;
    private CellPosition positionOnBoard;

    public Cell(CellPosition positionOnBoard) {
        this.positionOnBoard = positionOnBoard;

        setCellState(CellStatesFactory.EMPTY_CELL_ID);

        setPrefSize(cellWidth, cellHeight);
        setMinSize(cellWidth, cellHeight);
        setMaxSize(cellWidth, cellHeight);

        setAlignment(Pos.CENTER);

        getStyleClass().add("cell");
    }

    public CellState getCurrentCellState() {
        return currentCellState;
    }

    public void setCurrentCellState(CellState currentCellState) {
        this.currentCellState = currentCellState;
    }

    public void selectCell(Integer currentCellStateId) {
        setCellState(currentCellStateId);
        getStyleClass().addAll("selected");
        setDisable(true);
    }

    private void setCellState(Integer currentCellStateId) {
        CellState selectedStyle = CellStatesFactory.getCellStateById(currentCellStateId);

        getStyleClass().addAll(selectedStyle.getLabelClass());
        setText(selectedStyle.getLabelText());
        currentCellState = selectedStyle;
    }

    public CellPosition getPositionOnBoard() {
        return positionOnBoard;
    }
}
