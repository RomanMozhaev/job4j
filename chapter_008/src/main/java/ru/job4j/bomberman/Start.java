package ru.job4j.bomberman;

import java.util.concurrent.CyclicBarrier;

/**
 * the method for staring hero's thread.
 */
public class Start {

    public static void main(String[] args) {
        Start start = new Start();
        start.start();
    }

    private void start() {
        Board board = new Board(10, 10);
        board.init();
        blockUnavailableCells(board);
        int monsterQuantity = 2;
        CyclicBarrier block = new CyclicBarrier(monsterQuantity + 1);
        Cell startPosition1 = new Cell(9, 9);
        Cell startPosition2 = new Cell(9, 0);
        Cell startPosition3 = new Cell(0, 0);
        Hero monster1 = new Hero(board, startPosition1, "Monster1", block);
        Hero monster2 = new Hero(board, startPosition2, "Monster2", block);
        UserHero bomber = new UserHero(board, startPosition3, "Bomberman", block);
        monster1.t.start();
        monster2.t.start();
        bomber.t.start();

        try {
            monster1.t.join();
            monster2.t.join();
            bomber.t.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * The method for blocking cells which should not be available for heroes.
     */
    private void blockUnavailableCells(Board board) {
        Cell cell1 = new Cell(5, 5);
        board.blockStartPosition(cell1);

    }
}
