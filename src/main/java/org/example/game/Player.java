package org.example.game;

import org.example.game.enums.SignEnum;
import org.example.user.User;

public record Player(User user, SignEnum sign) {

}
