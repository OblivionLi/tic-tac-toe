package tictactoe;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        initializeGame();
    }

    public static void initializeGame() {
        Scanner scanner = new Scanner(System.in);
        UserInterface ui = new UserInterface(scanner);
        ui.startGame();
    }
}
