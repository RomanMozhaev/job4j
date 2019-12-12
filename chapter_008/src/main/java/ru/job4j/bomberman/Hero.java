package ru.job4j.bomberman;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * the thread for hero.
 */
public class Hero implements Runnable {

    /**
     * the Thread.
     */
    protected final Thread t;
    /**
     * the play board.
     */
    private final Board board;
    /**
     * the starting position of the hero.
     */
    private final Cell startPosition;

    /**
     * the block for simultaneous start.
     */
    private final CyclicBarrier block;

    /**
     * the main constructor.
     *
     * @param board         - the play board.
     * @param startPosition - the starting position.
     */
    public Hero(Board board, Cell startPosition, String name, CyclicBarrier block) {
        this.t = new Thread(this, name);
        this.board = board;
        this.startPosition = startPosition;
        this.block = block;

    }

    /**
     * the main run method of this thread.
     */
    @Override
    public void run() {
        Cell source = this.startPosition;
        this.board.blockStartPosition(source);
        try {
            block.await();
        } catch (InterruptedException | BrokenBarrierException e) {
            e.printStackTrace();
        }
        while (true) {
            Cell dist = getNewCell(source);
            if (this.board.move(source, dist, 500)) {
                source = dist;
            }
        }
    }

    /**
     * the method provides the cell of the new hero's position.
     *
     * @param source - the current hero's position.
     * @return cell.
     */
    private Cell getNewCell(Cell source) {
        int rowChange = 0;
        int columnChange = 0;
        int choose = (int) (Math.random() * 80) / 10;
        if (choose == 0) {
            rowChange = 1;
            columnChange = 1;
        }
        if (choose == 1) {
            rowChange = 1;
            columnChange = 0;
        }
        if (choose == 2) {
            rowChange = 1;
            columnChange = -1;
        }
        if (choose == 3) {
            rowChange = 0;
            columnChange = 1;
        }
        if (choose == 4) {
            rowChange = 0;
            columnChange = -1;
        }
        if (choose == 5) {
            rowChange = -1;
            columnChange = 1;
        }
        if (choose == 6) {
            rowChange = -1;
            columnChange = 0;
        }
        if (choose == 7) {
            rowChange = -1;
            columnChange = -1;
        }
        return checkBorder(source, rowChange, columnChange);
    }

    /**
     * the method check if the new position within the board. if it is not,
     * the method corrects the new position.
     *
     * @param source       - the current position
     * @param rowChange    - the position change which should be checked in row direction.
     * @param columnChange - the position change which should be checked in column direction.
     * @return the Cell for moving.
     */
    private Cell checkBorder(Cell source, int rowChange, int columnChange) {
        int newRow = source.getRow() + rowChange;
        int newColumn = source.getColumn() + columnChange;
        int chooseRow = -1;
        if (newRow < 0 || newRow > (this.board.getRows() - 1)) {
            chooseRow = (int) (Math.random() * 100) / 50;
            if (chooseRow == 0) {
                newRow = source.getRow();
            }
            if (chooseRow == 1) {
                newRow = source.getRow() - rowChange;
            }
        }
        if (newColumn < 0 || newColumn > (this.board.getColumns() - 1)) {
            int chooseColumn = (int) (Math.random() * 100) / 50;
            if (chooseRow != 0 && chooseColumn == 0) {
                newColumn = source.getColumn();
            }
            if (chooseRow == 0 || chooseColumn == 1) {
                newColumn = source.getColumn() - columnChange;
            }
        }
        return new Cell(newRow, newColumn);

    }
}
