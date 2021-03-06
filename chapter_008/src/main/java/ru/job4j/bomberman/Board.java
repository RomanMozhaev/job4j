package ru.job4j.bomberman;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * the class describes the board for the play.
 */
public class Board {

    /**
     * the matrix of the board. The cell is ReentrantLock for locking the cell by game heroes.
     */
    private final ReentrantLock[][] board;
    /**
     * the vertical size of the board.
     */
    private final int rows;
    /**
     * the horizontal size of the board.
     */
    private final int columns;

    private volatile int moving;

    /**
     * the main constructor.
     *
     * @param rows    - Y - size
     * @param columns - X - size
     */
    public Board(int rows, int columns) {
        this.board = new ReentrantLock[rows][columns];
        this.columns = columns;
        this.rows = rows;
    }

    public int getMoving() {
        return moving;
    }

    public void setMoving(int moving) {
        this.moving = moving;
    }

    /**
     * rows getter
     *
     * @return - rows.
     */
    public int getRows() {
        return rows;
    }

    /**
     * columns getter
     *
     * @return - columns
     */
    public int getColumns() {
        return columns;
    }

    /**
     * the board initialization.
     * //////////the frame is under construction.//////////////////
     */
    public void init() {
        for (int i = 0; i < this.rows; i++) {
            for (int j = 0; j < this.columns; j++) {
                this.board[i][j] = new ReentrantLock();
            }

        }

        JFrame frame = new JFrame();
        frame.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent keyEvent) {
                setMoving(keyEvent.getKeyCode());
            }

            @Override
            public void keyPressed(KeyEvent keyEvent) {
            }
            @Override
            public void keyReleased(KeyEvent keyEvent) {

            }
        });
    }

    /**
     * the method for blocking the starting position.
     *
     * @param position - the starting position.
     * @return - true if the starting positing was blocked successfully; false otherwise.
     */
    public boolean blockStartPosition(Cell position) {
        return this.board[position.getRow()][position.getColumn()].tryLock();
    }

    /**
     * the method makes a move of the heroes.
     *
     * @param source - the current position of the hero.
     * @param dest   - the destination.
     * @return - true if the move was made successfully for 0,5 sec; false otherwise.
     */
    public boolean move(Cell source, Cell dest, long timeout) {
        boolean result = false;
        try {
            result = this.board[dest.getRow()][dest.getColumn()].tryLock(timeout, TimeUnit.MILLISECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if (result) {
            this.board[source.getRow()][source.getColumn()].unlock();
        }
        return result;
    }
}
