package org.example.game;

import org.example.game.enums.SignEnum;
import org.example.game.exception.InvalidPositionException;
import org.example.game.exception.UsedPositionException;
import org.example.user.User;

import java.util.*;

public class Game {
    private final Player[] players;
    private final Board board;
    private final List<Map.Entry<Player, Move>> moves;
    private int curUserIndex;

    public Game(Player player1, Player player2, int size, int startPlayerIndex) {
       players = new Player[2];
       players[0] = player1;
       players[1] = player2;
       board = new Board(size);
       moves = new ArrayList<>();
       curUserIndex = startPlayerIndex;
    }

    public void makeMove(Move move) {
        try {
            board.put(getCurPlayer().sign(), move);
        } catch (InvalidPositionException | UsedPositionException ex) {
            System.out.println(STR."Error: \{ex.getMessage()}");
            System.out.println("Try Again");
            return;
        }
        moves.add(new AbstractMap.SimpleImmutableEntry<>(getCurPlayer(), move));
        changePlayer();
    }


    public boolean isFinished() {
        return board.isDraw() || board.getWinner().isPresent();
    }

    public Optional<Player> getWinner() {
        return board.getWinner().map(s -> s.equals(players[0].sign()) ? players[0] : players[1]);
    }

    public void printBoard() {
        System.out.println(board);
    }
    public Player getPlayer1() {
        return players[0];
    }

    public Player getPlayer2() {
        return players[1];
    }

    public Player getCurPlayer() {
        return players[curUserIndex];
    }

    private void changePlayer() {
        curUserIndex = curUserIndex == 1 ? 0 : 1;
    }

    public List<Map.Entry<Player, Move>> getMoves() {
        return moves.stream().toList();
    }
}
