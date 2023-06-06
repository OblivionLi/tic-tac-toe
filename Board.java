package tictactoe;

public class Board {
    private final Character[][] board;

    Board() {
        this.board = new Character[3][3];
        this.initializeEmptyBoard();
    }

    public Character isGameOver() {
        if (checkHorizontalWin('X')
                || checkVerticalWin('X')
                || checkDiagonalWin('X')
        ) {
            return 'X';
        }

        if (checkHorizontalWin('O')
                || checkVerticalWin('O')
                || checkDiagonalWin('O')
        ) {
            return 'O';
        }

        if (!isGameFinished()) {
            return null;
        }

        return 'D';
    }

    private boolean isGameFinished() {
        for (int row = 0; row < this.board.length; row++) {
            for (int column = 0; column < this.board[row].length; column++) {
                if (this.board[row][column] == ' ') {
                    return false;
                }
            }
        }

        return true;
    }

    public boolean checkHorizontalWin(char playerSymbol) {
        for (int row = 0; row < 3; row++) {
            if (this.board[row][0] == playerSymbol
                    && this.board[row][0] == this.board[row][1]
                    && this.board[row][0] == this.board[row][2]
            ) {
                return true;
            }
        }

        return false;
    }

    public boolean checkVerticalWin(char playerSymbol) {
        for (int col = 0; col < 3; col++) {
            if (this.board[0][col] == playerSymbol
                    && this.board[0][col] == this.board[1][col]
                    && this.board[0][col] == this.board[2][col]
            ) {
                return true;
            }
        }
        return false;
    }

    public boolean checkDiagonalWin(char playerSymbol) {
        if (this.board[0][0] == playerSymbol
                && this.board[0][0] == this.board[1][1]
                && this.board[0][0] == this.board[2][2]
        ) {
            return true;
        }

        return this.board[0][2] == playerSymbol
                && this.board[0][2] == this.board[1][1]
                && this.board[0][2] == this.board[2][0]
        ;
    }

    public void initializeEmptyBoard() {
        for (int row = 0; row < this.board.length; row++) {
            for (int column = 0; column < this.board[row].length; column++) {
                this.board[row][column] = ' ';
            }
        }
    }

    public boolean fillSpace(int[] coordinates, Character symbolToFill) {
        if (this.board[coordinates[0]][coordinates[1]] != ' ') {
            return false;
        }

        this.board[coordinates[0]][coordinates[1]] = symbolToFill;
        return true;
    }

    public Character[][] getBoard() {
        return this.board;
    }

    @Override
    public String toString() {
        StringBuilder output = new StringBuilder();
        output.append("---------\n");

        for (int row = 0; row < this.board.length; row++) {
            output.append("|");
            for (int column = 0; column < this.board[row].length; column++) {
                output.append(" ").append(this.board[row][column]);
            }
            output.append(" |\n");
        }
        output.append("---------");
        return output.toString();
    }
}
