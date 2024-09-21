package org.example.game.enums;

public enum SignEnum {
    X('X'), O('O');

    private final char sign;

    SignEnum(char o) {
        sign = o;
    }

    public char getSign() {
        return sign;
    }
}
