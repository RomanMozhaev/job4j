package ru.job4j.ticktack;

/**
 * the class for playing board.
 */
public class Board implements BoardInterface {

    /**
     * board - the playing board.
     * size - board size.
     * LN - the line separator
     */
    private char[][] board;
    private int size;
    private static final String LN = System.lineSeparator();

    public Board(int size) {
        this.size = size;
    }

    @Override
    public int getBoardSize() {
        return this.size;
    }

    @Override
    public void initNewBoard() {
        this.board = new char[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                this.board[i][j] = '-';
            }
        }
    }

    @Override
    public void setCell(int vertical, int horizontal, char ch) {
        this.board[vertical - 1][horizontal - 1] = ch;
    }

    @Override
    public boolean checkCell(int vertical, int horizontal) {
        boolean result = false;
        if (this.board[vertical - 1][horizontal - 1] == '-') {
            result = true;
        }
        return result;
    }

    @Override
    public StringBuilder printBoard() {
        StringBuilder sb = new StringBuilder();
        sb.append(" ");
        for (int i = 1; i < this.size + 1; i++) {
            sb.append(i);
        }
        sb.append(LN);
        int j = 0;
        for (char[] line : board) {
            j++;
            sb.append(j);
            for (char ch : line) {
                sb.append(ch);
            }
            sb.append(LN);
        }
        return sb;
    }

    @Override
    public boolean win(char ch) {
        boolean result = false;
        int rightDiagonal = 0;
        int leftDiagonal = 0;
        for (int i = 0; i < this.size; i++) {
            int vertical = 0;
            int horizontal = 0;
            for (int j = 0; j < this.size; j++) {
                if (this.board[i][j] == ch) {
                    horizontal++;
                }
                if (this.board[j][i] == ch) {
                    vertical++;
                }
                if ((i == j) && this.board[i][j] == ch) {
                    rightDiagonal++;
                }
                if ((i == this.size - 1 - j) && this.board[i][j] == ch) {
                    leftDiagonal++;
                }
            }
            if (horizontal == this.size
                    || vertical == this.size
                    || rightDiagonal == this.size
                    || leftDiagonal == this.size) {
                result = true;
                break;
            }
        }
        return result;
    }

    @Override
    public boolean checkDrawn() {
        boolean result = true;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (this.board[i][j] == '-') {
                    result = false;
                }
            }
        }
        return result;
    }
}