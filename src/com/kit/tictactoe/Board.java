package com.kit.tictactoe;

import com.kit.tictactoe.cells.Cell;
import com.kit.tictactoe.cells.CellPosition;
import javafx.scene.layout.GridPane;

public class Board extends GridPane {

    private Cell[][] cells;
    private int rows;
    private int cols;

    public Board(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;

        cells = new Cell[rows][cols];

        for(int rowCount = 0; rowCount < rows; rowCount++) {
            for(int colCount = 0; colCount < cols; colCount++) {
                Cell cell = new Cell(new CellPosition(rowCount, colCount));
                add(cell, colCount, rowCount);
                cells[rowCount][colCount] = cell;
            }
        }
    }

    public Cell getCellAtPosition(int rowPosition, int colPosition) {
        return cells[rowPosition][colPosition];
    }

    public boolean allCellsAreFilled() {
        for(int rowCount = 0; rowCount < rows; rowCount++) {
            for(int colCount = 0; colCount < cols; colCount++) {
                Cell cell = cells[rowCount][colCount];

                if(cell.getCurrentCellState().getCellStateId() == Constants.EMPTY_CELL_ID) {
                    return false;
                }
            }
        }

        return true;
    }

    public int getRows() {
        return rows;
    }

    public int getCols() {
        return cols;
    }
}
