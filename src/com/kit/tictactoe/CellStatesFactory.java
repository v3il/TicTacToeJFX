package com.kit.tictactoe;

import java.util.HashMap;
import java.util.Map;

public class CellStatesFactory {

    public final static int EMPTY_CELL_ID  = 1;
    public final static int CROSS_CELL_ID  = 2;
    public final static int CIRCLE_CELL_ID = 3;

    private static Map<Integer, CellState> cellStatesMap;

    static {
        cellStatesMap = new HashMap<>();

        cellStatesMap.put(EMPTY_CELL_ID, new CellState("", "empty"));
        cellStatesMap.put(CROSS_CELL_ID, new CellState("x", "cross"));
        cellStatesMap.put(CIRCLE_CELL_ID, new CellState("o", "circle"));
    }

    public static CellState getCellStateById(Integer cellStateId) {
        return cellStatesMap.get(cellStateId);
    }

}
