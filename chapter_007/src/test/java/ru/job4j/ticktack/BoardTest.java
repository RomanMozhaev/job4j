package ru.job4j.ticktack;

import org.junit.Test;

import static org.junit.Assert.*;

public class BoardTest {

    @Test
    public void whenFirstColumnThenTrue() {
        Board board = new Board(3);
        board.initNewBoard();
        board.setCell(1, 1, 'X');
        board.setCell(2, 1, 'X');
        board.setCell(3, 1, 'X');
        assertTrue(board.win('X'));
    }
    @Test
    public void whenSecondColumnThenTrue() {
        Board board = new Board(3);
        board.initNewBoard();
        board.setCell(1, 2, 'X');
        board.setCell(2, 2, 'X');
        board.setCell(3, 2, 'X');
        board.setCell(1, 1, '0');
        board.setCell(2, 1, '0');
        board.setCell(3, 3, '0');
        assertTrue(board.win('X'));
    }
    @Test
    public void whenThirdLineThenTrue() {
        Board board = new Board(3);
        board.initNewBoard();
        board.setCell(1, 2, 'X');
        board.setCell(2, 2, 'X');
        board.setCell(2, 3, 'X');
        board.setCell(3, 1, '0');
        board.setCell(3, 2, '0');
        board.setCell(3, 3, '0');
        assertTrue(board.win('0'));
    }
    @Test
    public void whenLeftDiagonalThenTrue() {
        Board board = new Board(3);
        board.initNewBoard();
        board.setCell(1, 3, 'X');
        board.setCell(2, 2, 'X');
        board.setCell(3, 1, 'X');
        board.setCell(1, 1, '0');
        board.setCell(1, 2, '0');
        board.setCell(3, 3, '0');
        assertTrue(board.win('X'));
    }
    @Test
    public void whenRightDiagonalThenTrue() {
        Board board = new Board(3);
        board.initNewBoard();
        board.setCell(1, 3, 'X');
        board.setCell(1, 2, 'X');
        board.setCell(3, 1, 'X');
        board.setCell(1, 1, '0');
        board.setCell(2, 2, '0');
        board.setCell(3, 3, '0');
        System.out.println(board.printBoard());
        assertTrue(board.win('0'));
    }

    @Test
    public void whenDrownThenTrue() {
        Board board = new Board(3);
        board.initNewBoard();
        board.setCell(1, 1, 'X');
        board.setCell(1, 2, '0');
        board.setCell(2, 1, '0');
        board.setCell(1, 3, 'X');
        board.setCell(3, 1, '0');
        board.setCell(2, 2, 'X');
        board.setCell(2, 3, 'X');
        board.setCell(3, 2, 'X');
        board.setCell(3, 3, '0');

        System.out.println(board.printBoard());
        assertTrue(board.checkDrawn());
    }
}