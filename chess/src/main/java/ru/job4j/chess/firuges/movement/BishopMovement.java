package ru.job4j.chess.firuges.movement;

import ru.job4j.chess.exception.ImpossibleMoveException;
import ru.job4j.chess.firuges.Cell;

/**
 * check the Bishop's way. If it is not a diagonal, it trows an exception.
 * it finds the cells which along the Bishop moves.
 */
public class BishopMovement {
    private Cell source;
    private Cell dest;

    public BishopMovement(Cell source, Cell dest) {
        this.source = source;
        this.dest = dest;
    }

    public Cell[] way() throws ImpossibleMoveException {

        if (!isDiagonal()) {
            throw new ImpossibleMoveException();
        }
        return findTheWay();
    }

    /**
     * check the movement is along diagonal or not.
     * @return true if along diagonal
     */
    private boolean isDiagonal() {
        boolean result = false;
        int deltaX = this.source.x - this.dest.x;
        int deltaY = this.source.y - this.dest.y;
        if (Math.abs(deltaX) == Math.abs(deltaY)) {
            result = true;
        }
        return result;
    }

    /**
     * find the cells which along the Bishop moves.
     * @return the array of way cells.
     */
    private Cell[] findTheWay() {

        int xIncrement = 1;
        int yIncrement = 1;

        int stepLength = Math.abs(this.source.x - this.dest.x);
        Cell[] result = new Cell[stepLength];
        Cell[] allCells = Cell.values();
        if (this.source.x > this.dest.x) {
            xIncrement = -1;
        }
        if (this.source.y > this.dest.y) {
            yIncrement = -1;
        }
        int xPos = this.source.x + xIncrement;
        int yPos = this.source.y + yIncrement;
        for (int i = 0; i < stepLength; i++) {
            for (Cell cell : allCells) {
                if (cell.x == xPos && cell.y == yPos) {
                    result[i] = cell;
                    break;
                }
            }
            xPos += xIncrement;
            yPos += yIncrement;
        }
        return result;
    }
}