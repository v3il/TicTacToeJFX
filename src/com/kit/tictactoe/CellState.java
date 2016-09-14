package com.kit.tictactoe;

public class CellState {

    private String labelText;
    private String labelClass;

    public CellState(String labelText, String labelClass) {
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
}
