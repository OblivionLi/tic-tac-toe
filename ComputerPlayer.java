package tictactoe;

import java.util.*;

public class ComputerPlayer {
    protected final Board board;
    private final String difficulty;
    private final Character symbol;

    ComputerPlayer(Board board, String difficulty, Character symbol) {
        this.board = board;
        this.difficulty = difficulty;
        this.symbol = symbol;
    }

    public String getDifficulty() {
        return this.difficulty.toLowerCase();
    }

    public int[] getCoordinates() {
        Character[][] board = this.board.getBoard();

        List<int[]> freeCoordinates = new ArrayList<>();

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j] == ' ') {
                    freeCoordinates.add(new int[]{i, j});
                }
            }
        }

        if (freeCoordinates.isEmpty()) {
            return null;
        }

        Random random = new Random();
        int randomIndex = random.nextInt(freeCoordinates.size());
        int[] generatedCoordinates = freeCoordinates.get(randomIndex);

        return new int[]{generatedCoordinates[0], generatedCoordinates[1]};
    }

    public Character getSymbol() {
        return this.symbol;
    }
}
