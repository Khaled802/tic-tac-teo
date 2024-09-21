package org.example.game.exception;

import static java.lang.StringTemplate.STR;

public class InvalidPositionException extends Exception {
    public InvalidPositionException(int x, int y) {
        super(STR."The position[\{x},\{y}] is wrong");
    }
}
