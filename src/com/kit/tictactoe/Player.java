package com.kit.tictactoe;

import com.kit.tictactoe.cells.CellState;

public class Player {

    private String nickname;
    private CellState playerMarkStyle;

    public Player(String nickname, CellState playerMarkStyleId) {
        this.nickname = nickname;
        this.playerMarkStyle = playerMarkStyleId;
    }

    public String getNickname() {
        return nickname;
    }

    public CellState getPlayerMarkStyleId() {
        return playerMarkStyle;
    }
}
