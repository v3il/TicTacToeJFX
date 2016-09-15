package com.kit.tictactoe.cells;

import com.kit.tictactoe.Constants;

public class EmptyCellState extends CellState {

    public EmptyCellState() {
        super(Constants.EMPTY_CELL_ID, "", "empty");
    }
}
