package ru.job4j.bomberman;

/**
 * the class of the cell on the board.
 * it contains coordinates.
 */
public class Cell {

    private final int row;
    private final int column;

    public Cell(int row, int column) {
        this.row = row;
        this.column = column;
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }
}
