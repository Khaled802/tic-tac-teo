package org.example;

import org.example.game.Game;
import org.example.game.Move;
import org.example.game.Player;
import org.example.game.enums.SignEnum;
import org.example.user.User;

import java.util.Random;
import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    private final static Scanner input = new Scanner(System.in);
    private final static Random random = new Random();

    public static void main(String[] args) {
        System.out.println("Enter First Player Name");
        String name1 = input.nextLine();
        System.out.println("Enter Second Player Name");
        User user1 = new User(name1);
        String name2 = input.nextLine();
        User user2 = new User(name2);
        SignEnum[] randomSigns = getRandomSigns();
        Player player1 = new Player(user1, randomSigns[0]);
        Player player2 = new Player(user2, randomSigns[1]);

        printPlayerInAGame(player1);
        printPlayerInAGame(player2);

        Game game = new Game(player1, player2, 3, random.nextInt(2));

        while (!game.isFinished()) {
            System.out.println("---------------------------");
            printPlayerInAGame(game.getCurPlayer());
            System.out.println("Make move\n");
            System.out.println("Enter cell position\n");
            game.printBoard();
            System.out.println("Enter x position:");
            int x = Integer.parseInt(input.nextLine());
            System.out.println("Enter y position:");
            int y = Integer.parseInt(input.nextLine());
            Move move = new Move(x, y);
            game.makeMove(move);
            game.printBoard();
            System.out.println("Press Enter to continue");
            input.nextLine();
        }
        game.getWinner().ifPresentOrElse(player -> {
            player.user().addScore(1);
            System.out.printf("Congrats %s Your score Now %d\n", player.user().getName(), player.user().getScore());
        }, () -> System.out.println("Draw!!!\n"));
    }


    public static SignEnum[] getRandomSigns() {
        int randomInt = random.nextInt(2);
        int randomInt2 = (randomInt + 1) % 2;
        SignEnum[] signs = new SignEnum[] {SignEnum.O, SignEnum.X};
        return new SignEnum[] {signs[randomInt], signs[randomInt2]};
    }

    public static void printPlayerInAGame(Player player) {
        System.out.printf("Player[%s] with sign[%c]\n", player.user().getName(), player.sign().getSign());
    }
}