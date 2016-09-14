package com.kit.tictactoe;

public class Player {

    private String nickname;
    private Integer playerMarkStyleId;

    public Player(String nickname, Integer playerMarkStyleId) {
        this.nickname = nickname;
        this.playerMarkStyleId = playerMarkStyleId;
    }

    public String getNickname() {
        return nickname;
    }

    public Integer getPlayerMarkStyleId() {
        return playerMarkStyleId;
    }
}
