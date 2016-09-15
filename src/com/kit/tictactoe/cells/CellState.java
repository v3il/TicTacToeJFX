package com.kit.tictactoe.cells;

public class CellState {

    private Integer cellStateId;
    private String labelText;
    private String labelClass;

    public CellState(Integer cellStateId, String labelText, String labelClass) {
        this.cellStateId = cellStateId;
        this.labelText = labelText;
        this.labelClass = labelClass;
    }

    public String getLabelText() {
        return labelText;
    }

    public void setLabelText(String labelText) {
        this.labelText = labelText;
    }

    public String getLabelClass() {
        return labelClass;
    }

    public void setLabelClass(String labelClass) {
        this.labelClass = labelClass;
    }

    public int getCellStateId() {
        return cellStateId;
    }

    public void setCellStateId(int cellStateId) {
        this.cellStateId = cellStateId;
    }
}
