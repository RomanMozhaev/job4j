package ru.job4j.bomberman;

/**
 * the method for staring hero's thread.
 */
public class Start {

    public static void main(String[] args) {
        Board board = new Board(10, 10);
        board.init();
        Cell startPosition = new Cell(0, 0);
        Hero hero = new Hero(board, startPosition);
        hero.t.start();
        try {
            hero.t.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
