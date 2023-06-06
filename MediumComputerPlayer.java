package tictactoe;

public class MediumComputerPlayer extends ComputerPlayer {
    MediumComputerPlayer(Board board, String difficulty, Character symbol) {
        super(board, difficulty, symbol);
    }

    @Override
    public int[] getCoordinates() {
        int[] coordinatesToHorizontalWin = this.getCoordinatesForHorizontalAlmostWin();
        if (coordinatesToHorizontalWin != null ) {
            return coordinatesToHorizontalWin;
        }

        int[] coordinatesToVerticalWin = this.getCoordinatesForVerticalAlmostWin();
        if (coordinatesToVerticalWin != null) {
            return coordinatesToVerticalWin;
        }

        int[] coordinatesToDiagonalWin = this.getCoordinatesForDiagonalAlmostWin();
        if (coordinatesToDiagonalWin != null) {
            return coordinatesToDiagonalWin;
        }

        return super.getCoordinates();
    }

    private int[] getCoordinatesForHorizontalAlmostWin() {
        Character[][] board = super.board.getBoard();

        for (int row = 0; row < 3; row++) {
            int emptyCount = 0;
            int emptyColumnIndex = -1;
            char symbol = ' ';

            for (int col = 0; col < 3; col++) {
                if (board[row][col] == ' ') {
                    emptyCount++;
                    emptyColumnIndex = col;
                } else if (symbol == ' ') {
                    symbol = board[row][col];
                } else if (board[row][col] != symbol) {
                    // Symbols don't match, break the loop
                    emptyCount = 0;
                    emptyColumnIndex = -1;
                    break;
                }
            }

            if (emptyCount == 1) {
                return new int[]{row, emptyColumnIndex};
            }
        }

        return null; // No almost win found
    }

    private int[] getCoordinatesForVerticalAlmostWin() {
        Character[][] board = super.board.getBoard();

        for (int col = 0; col < 3; col++) {
            int emptyCount = 0;
            int emptyRowIndex = -1;
            char symbol = ' ';

            for (int row = 0; row < 3; row++) {
                if (board[row][col] == ' ') {
                    emptyCount++;
                    emptyRowIndex = row;
                } else if (symbol == ' ') {
                    symbol = board[row][col];
                } else if (board[row][col] != symbol) {
                    // Symbols don't match, break the loop
                    emptyCount = 0;
                    emptyRowIndex = -1;
                    break;
                }
            }

            if (emptyCount == 1) {
                return new int[]{emptyRowIndex, col};
            }
        }

        return null; // No almost win found
    }

    private int[] getCoordinatesForDiagonalAlmostWin() {
        Character[][] board = super.board.getBoard();

        // Check the main diagonal
        int emptyCount = 0;
        int emptyIndex = -1;
        char symbol = ' ';

        for (int i = 0; i < 3; i++) {
            if (board[i][i] == ' ') {
                emptyCount++;
                emptyIndex = i;
            } else if (symbol == ' ') {
                symbol = board[i][i];
            } else if (board[i][i] != symbol) {
                // Symbols don't match, break the loop
                emptyCount = 0;
                emptyIndex = -1;
                break;
            }
        }

        if (emptyCount == 1) {
            return new int[]{emptyIndex, emptyIndex};
        }

        // Check the secondary diagonal
        emptyCount = 0;
        emptyIndex = -1;
        symbol = ' ';

        for (int i = 0; i < 3; i++) {
            if (board[i][2 - i] == ' ') {
                emptyCount++;
                emptyIndex = i;
            } else if (symbol == ' ') {
                symbol = board[i][2 - i];
            } else if (board[i][2 - i] != symbol) {
                // Symbols don't match, break the loop
                emptyCount = 0;
                emptyIndex = -1;
                break;
            }
        }

        if (emptyCount == 1) {
            return new int[]{emptyIndex, 2 - emptyIndex};
        }

        return null; // No almost win found
    }
}
