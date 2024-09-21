package org.example.game;

import org.example.game.enums.SignEnum;
import org.example.game.exception.InvalidPositionException;
import org.example.game.exception.UsedPositionException;

import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Board {
    private final char[][] board;
    private final int size;
    private final String separator;
    private final String indexes;
    private SignEnum winner;
    private int moves;
    private final int[] rowSum;
    private final int[] colSum;
    private int cross;
    private int reversCross;

    public Board(int size) {
        this.size = size;
        board = new char[size][size];
        separator = IntStream.range(0, size*2 + 2).mapToObj(_ -> "-").reduce((cum, cur)-> cum + cur).orElse("");
        indexes = IntStream.range(0, size).mapToObj(String::valueOf).collect(Collectors.joining("|"));
        rowSum = new int[size];
        colSum = new int[size];
        moves = 0;
    }


    public void put(SignEnum sign, Move move) throws InvalidPositionException, UsedPositionException {
        if (!isValidPosition(move.x(), move.y()))
            throw new InvalidPositionException(move.x(), move.y());
        if (board[move.x()][move.y()] != 0)
            throw new UsedPositionException(move.x(), move.y());
        board[move.x()][move.y()] = sign.getSign();

        updateState(move, sign.equals(SignEnum.O) ? -1 : 1);
    }

    private void updateState(Move move, int moveEffect) {
        moves++;
        rowSum[move.x()]+=moveEffect;
        colSum[move.y()]+=moveEffect;
        if (move.x() == move.y()) cross+=moveEffect;
        if (move.x() == size - move.y() - 1) reversCross+=moveEffect;
        if (winner == null)
            winner = rowSum[move.x()] == size || colSum[move.y()] == size|| cross == size || reversCross == size ?
                    SignEnum.X : null;
        if (winner == null)
            winner = rowSum[move.x()] == -size || colSum[move.y()] == -size|| cross == -size || reversCross == -size ?
                    SignEnum.O : null;
    }
    public Optional<SignEnum> getWinner() {
        return Optional.ofNullable(winner);
    }

    public boolean isDraw() {
        return winner == null && moves == (size*2);
    }

    public boolean isValidPosition(int x, int y) {
        return (x >= 0 && x < size) && (y >= 0 && y < size);
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        result.append("I|");
        result.append(indexes);
        result.append("\n");
        for (int i = 0; i < size; i++) {
            result.append(separator);
            result.append("\n");
            result.append(i);
            result.append("|");
            for (int j = 0; j < size; j++) {
                result.append(board[i][j]);
                result.append("|");
            }
            result.append("\n");
        }
        return result.toString();
    }
}
