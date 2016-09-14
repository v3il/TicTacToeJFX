package com.kit.tictactoe;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class PlayerQueue {

    private Player currentPlayer;
    private List<Player> players;

    private Random random;

    public PlayerQueue() {
        players = new ArrayList<>();
        random = new Random();
    }

    public PlayerQueue(Player[] playersArray) {
        this();
        addPlayers(playersArray);
    }

    public void addPlayers(Player[] playersArray) {
        List<Player> playersToAdd = Arrays.asList(playersArray);

        players.addAll(playersToAdd);
    }

    public void addPlayer(Player player) {
        players.add(player);
    }

    public Player getCurrentPlayer() {
        if(currentPlayer == null) {
            changePlayer();
        }

        return currentPlayer;
    }

    /**
     * Метод определяет следующего в очереди игрока основываясь на предыдущем
     * и записывает его в поле currentPlayer.
     * Если предыдущего нет (первый ход [currentPlayer == null]),
     * игрок выбирается случайно из списка зарегистрированных игроков.
     * Иначе выбирается игрок, отличный от игрока, который ходил предыдущим.
     */
    public void changePlayer() {
        int nextPlayerIndex;

        if(currentPlayer == null) {
            currentPlayer = players.get(random.nextInt(players.size()));
        } else {
            nextPlayerIndex = currentPlayer == players.get(0) ? 1 : 0;
            currentPlayer = players.get(nextPlayerIndex);
        }
    }
}
