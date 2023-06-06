package tictactoe;

import java.util.*;

public class UserInterface {
    private final Scanner scanner;
    private final Board board;

    UserInterface(Scanner scanner) {
        this.scanner = scanner;
        this.board = new Board();
    }

    public void startGame() {
        while (true) {
            String[] inputCommand = this.getInputCommand();
            if (inputCommand != null && inputCommand[0].equals("exit")) {
                break;
            }

            if (inputCommand == null) {
                System.out.println("Bad parameters!");
                continue;
            }

            List<ComputerPlayer> AIPlayers = this.getAIPlayers(inputCommand);

            System.out.println(this.board);

            if (AIPlayers.size() == 2) {
                this.playAgainstAI(AIPlayers.get(0), AIPlayers.get(1));
                this.board.initializeEmptyBoard();
                continue;
            }

            if (inputCommand[1].equals("user") && inputCommand[1].equals(inputCommand[2])) {
                playAgainstPlayer('X', 'O');
            } else {
                char playerSymbol = inputCommand[1].equals("user") ? 'X' : 'O';
                playAgainstAIPlayer(playerSymbol, AIPlayers.get(0));
            }
            this.scanner.nextLine();
            this.board.initializeEmptyBoard();
        }
    }

    private String[] getInputCommand(){
        System.out.print("Input command: ");
        String input = this.scanner.nextLine().toLowerCase();
        String[] inputParts = input.split(" ");

        if (inputParts.length == 1 && inputParts[0].equals("exit")) {
            return inputParts;
        }

        if (inputParts.length != 3) {
            return null;
        }

        if (!inputParts[0].equals("start")) {
            return null;
        }

        for (int i = 1; i < inputParts.length; i++) {
            if (inputParts[i].equals("easy")
                    || inputParts[i].equals("user")
                    || inputParts[i].equals("medium")
                    || inputParts[i].equals("hard")
            ) {
                continue;
            }

            return null;
        }

        return inputParts;
    }

    private void AIMove(ComputerPlayer computerPlayer) {
        int[] computerCoordinates = computerPlayer.getCoordinates();

        if (computerCoordinates != null) {
            System.out.println("Making move level \"" + computerPlayer.getDifficulty() + "\"");
            this.board.fillSpace(computerCoordinates, computerPlayer.getSymbol());
        }
    }

    private void playerMove(Character symbolToFill) {
        while (true) {
            int[] userCoordinates = this.getPlayerInput();
            if (userCoordinates == null) {
                continue;
            }

            boolean isSpaceFilled = this.board.fillSpace(userCoordinates, symbolToFill);
            if (!isSpaceFilled) {
                System.out.println("This cell is occupied! Choose another one!");
                continue;
            }

            break;
        }
    }

    private int[] getPlayerInput() {
        System.out.print("Enter the coordinates: ");
        int[] coordinates = new int[2];

        try {
            coordinates[0] = this.scanner.nextInt() - 1;
            coordinates[1] = this.scanner.nextInt() - 1;

            return coordinates;
        } catch (InputMismatchException e) {
            this.scanner.nextLine();
            return null;
        }
    }

    private List<ComputerPlayer> getAIPlayers(String[] inputCommand) {
        List<ComputerPlayer> players = new ArrayList<>();

        if (inputCommand[1].equals("easy")) {
            players.add(new EasyComputerPlayer(this.board, "easy", 'X'));
        }

        if (inputCommand[1].equals("medium")) {
            players.add(new MediumComputerPlayer(this.board, "medium", 'X'));
        }

        if (inputCommand[1].equals("hard")) {
            players.add(new HardComputerPlayer(this.board, "hard", 'X'));
        }

        if (inputCommand[2].equals("easy")) {
            players.add(new EasyComputerPlayer(this.board, "easy", 'O'));
        }

        if (inputCommand[2].equals("medium")) {
            players.add(new MediumComputerPlayer(this.board, "medium", 'O'));
        }

        if (inputCommand[2].equals("hard")) {
            players.add(new HardComputerPlayer(this.board, "hard", 'O'));
        }

        return players;
    }

    private void playAgainstAI(ComputerPlayer computerPlayer1, ComputerPlayer computerPlayer2) {
        while (true) {
            this.AIMove(computerPlayer1);
            System.out.println(this.board);

            Character winner = this.board.isGameOver();
            if (winner != null) {
                printGameResult(winner);
                break;
            }

            this.AIMove(computerPlayer2);
            System.out.println(this.board);

            winner = this.board.isGameOver();
            if (winner != null) {
                printGameResult(winner);
                break;
            }
        }
    }

    private void playAgainstPlayer(char player1Symbol, char player2Symbol) {
        while (true) {
            this.playerMove(player1Symbol);
            System.out.println(this.board);

            Character winner = this.board.isGameOver();
            if (winner != null) {
                this.scanner.nextLine();
                printGameResult(winner);
                break;
            }

            this.playerMove(player2Symbol);
            System.out.println(this.board);

            winner = this.board.isGameOver();
            if (winner != null) {
                this.scanner.nextLine();
                printGameResult(winner);
                break;
            }
        }
    }

    private void playAgainstAIPlayer(char playerSymbol, ComputerPlayer computerPlayer) {
        while (true) {
            if (playerSymbol == 'X') {
                this.playerMove(playerSymbol);
                System.out.println(this.board);

                Character winner = this.board.isGameOver();
                if (winner != null) {
                    this.scanner.nextLine();
                    printGameResult(winner);
                    break;
                }

                this.AIMove(computerPlayer);
                System.out.println(this.board);

                winner = this.board.isGameOver();
                if (winner != null) {
                    printGameResult(winner);
                    break;
                }
            } else {
                this.AIMove(computerPlayer);
                System.out.println(this.board);

                Character winner = this.board.isGameOver();
                if (winner != null) {
                    printGameResult(winner);
                    break;
                }

                this.playerMove(playerSymbol);
                System.out.println(this.board);

                winner = this.board.isGameOver();
                if (winner != null) {
                    this.scanner.nextLine();
                    printGameResult(winner);
                    break;
                }
            }
        }
    }

    private void printGameResult(Character winner) {
        if (winner == 'X') {
            System.out.println("X wins");
        } else if (winner == 'O') {
            System.out.println("O wins");
        } else {
            System.out.println("Draw");
        }
    }
}
