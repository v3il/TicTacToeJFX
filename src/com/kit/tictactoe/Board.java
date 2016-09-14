package com.kit.tictactoe;

import javafx.scene.layout.GridPane;

public class Board extends GridPane {

    public Board(int rows, int cols) {
        for(int rowCount = 0; rowCount < rows; rowCount++) {
            for(int colCount = 0; colCount < cols; colCount++) {
                Cell cell = new Cell(new CellPosition(rowCount, colCount));
                add(cell, colCount, rowCount);
            }
        }
    }
}
