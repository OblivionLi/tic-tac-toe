package tictactoe;

import java.util.ArrayList;
import java.util.List;

public class HardComputerPlayer extends ComputerPlayer {
    HardComputerPlayer(Board board, String difficulty, Character symbol) {
        super(board, difficulty, symbol);
    }

    @Override
    public int[] getCoordinates() {
        Character[][] board = this.board.getBoard();

        int bestScore = Integer.MIN_VALUE;
        List<Integer> bestMove = new ArrayList<>();

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j] == ' ') {
                    board[i][j] = super.getSymbol();
                    int score = minimax(board, 0, false);
                    board[i][j] = ' ';
                    if (score > bestScore) {
                        bestScore = score;
                        bestMove.clear();
                        bestMove.add(i);
                        bestMove.add(j);
                    }
                }
            }
        }

        return new int[]{bestMove.get(0), bestMove.get(1)};
    }

    private int minimax(Character[][] board, int depth, boolean isMaximizingPlayer) {
        if (this.board.isGameOver() != null) {
            if (this.board.checkHorizontalWin(super.getSymbol())
                    || this.board.checkVerticalWin(super.getSymbol())
                    || this.board.checkDiagonalWin(super.getSymbol())) {
                return 1;
            } else if (this.board.checkHorizontalWin(getOpponentSymbol())
                    || this.board.checkVerticalWin(getOpponentSymbol())
                    || this.board.checkDiagonalWin(getOpponentSymbol())) {
                return -1;
            } else {
                return 0; // Draw
            }
        }

        int bestScore;
        if (isMaximizingPlayer) {
            bestScore = Integer.MIN_VALUE;
            for (int i = 0; i < board.length; i++) {
                for (int j = 0; j < board[i].length; j++) {
                    if (board[i][j] == ' ') {
                        board[i][j] = super.getSymbol();
                        int score = minimax(board, depth + 1, false);
                        board[i][j] = ' ';
                        bestScore = Math.max(score, bestScore);
                    }
                }
            }
        } else {
            bestScore = Integer.MAX_VALUE;
            for (int i = 0; i < board.length; i++) {
                for (int j = 0; j < board[i].length; j++) {
                    if (board[i][j] == ' ') {
                        board[i][j] = getOpponentSymbol();
                        int score = minimax(board, depth + 1, true);
                        board[i][j] = ' ';
                        bestScore = Math.min(score, bestScore);
                    }
                }
            }
        }

        return bestScore;
    }

    private char getOpponentSymbol() {
        return (super.getSymbol() == 'X') ? 'O' : 'X';
    }
}