package ru.job4j.bomberman;

import java.awt.event.KeyEvent;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class UserHero implements Runnable {
    /**
     * the Thread.
     */
    protected final Thread t;
    /**
     * the play board.
     */
    private final Board board;
    /**
     * the starting position of the User's Hero.
     */
    private final Cell startPosition;

    /**
     * the block for simultaneous start.
     */
    private final CyclicBarrier block;
    /**
     * The constants for directions.
     */
    private final static int UP = KeyEvent.VK_W;
    private final static int DOWN = KeyEvent.VK_S;
    private final static int LEFT = KeyEvent.VK_A;
    private final static int RIGHT = KeyEvent.VK_D;
    private final static int UP_LEFT = KeyEvent.VK_Q;
    private final static int UP_RIGHT = KeyEvent.VK_E;
    private final static int DOWN_LEFT = KeyEvent.VK_Z;
    private final static int DOWN_RIGHT = KeyEvent.VK_X;

    /**
     * the main constructor.
     *
     * @param board         - the play board.
     * @param startPosition - the starting position.
     */
    public UserHero(Board board, Cell startPosition, String name, CyclicBarrier block) {
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

        int oldMoving = this.board.getMoving();
        Cell source = this.startPosition;
        this.board.blockStartPosition(source);
        try {
            block.await();
        } catch (InterruptedException | BrokenBarrierException e) {
            e.printStackTrace();
        }
        while (true) {
            int moving = this.board.getMoving();
            if (oldMoving != moving) {
                Cell dist = getChanges(source, moving);
                if (dist != null && this.board.move(source, dist, 0)) {
                    source = dist;
                }
                oldMoving = moving;

            }
        }
    }

    /**
     * the method check the correct key for UserHero moving was pushed or not. if it is not,
     * the method returns null.
     *
     * @param source    - the current position
     * @param direction - the code of the pushed key.
     * @return the Cell for moving or null.
     */
    private Cell getChanges(Cell source, int direction) {
        Cell newPosition = null;
        int rowChange = 0;
        int columnChange = 0;
        if (direction == UP) {
            rowChange = 1;
            columnChange = 0;
        }
        if (direction == DOWN) {
            rowChange = -1;
            columnChange = 0;
        }
        if (direction == LEFT) {
            rowChange = 0;
            columnChange = -1;
        }
        if (direction == RIGHT) {
            rowChange = 0;
            columnChange = 1;
        }
        if (direction == UP_LEFT) {
            rowChange = 1;
            columnChange = -1;
        }
        if (direction == UP_RIGHT) {
            rowChange = 1;
            columnChange = 1;
        }
        if (direction == DOWN_LEFT) {
            rowChange = -1;
            columnChange = -1;
        }
        if (direction == DOWN_RIGHT) {
            rowChange = -1;
            columnChange = 1;
        }
        if ((rowChange != 0 || columnChange != 0)) {
            int newRow = source.getRow() + rowChange;
            int newColumn = source.getColumn() + columnChange;
            if (checkBorders(newRow, newColumn)) {
                newPosition = new Cell(newRow, newColumn);
            }
        }
        return newPosition;
    }

    /**
     * the method checks that moving is inside the playing board.
     *
     * @param newRow    - new possible row position.
     * @param newColumn - new possible column position.
     * @return true if the new position is inside the board; otherwise false.
     */
    private boolean checkBorders(int newRow, int newColumn) {
        boolean result = true;
        if (newRow < 0
                || newRow > (this.board.getRows() - 1)
                || newColumn < 0
                || newColumn > (this.board.getColumns() - 1)) {
            result = false;
        }
        return result;
    }
}
