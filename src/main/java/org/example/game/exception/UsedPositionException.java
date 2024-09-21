package org.example.game.exception;

public class UsedPositionException extends Exception {
    public UsedPositionException(int x, int y) {
        super(STR."The position[\{x},\{y}] is already used");
    }
}
