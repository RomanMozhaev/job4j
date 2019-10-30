package ru.job4j.ticktack;

/**
 * the class for the computer as a player.
 */
public class ComputerPlayer implements PlayerInterface {

    /**
     * name - the name of the player.
     * symbol - the assigned symbol to the player.
     */
    private String name;
    private char symbol;

    public ComputerPlayer(String name, char symbol) {
        this.name = name;
        this.symbol = symbol;
    }

    @Override
    public char getSymbol() {
        return this.symbol;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public int[] move(BoardInterface board) {
        int v;
        int h;
        int size = board.getBoardSize();
        do {
            v = ((int) Math.round(Math.random() * 3000) % size) + 1;
            h = ((int) Math.round(Math.random() * 7000) % size) + 1;
        } while (!board.checkCell(v, h));
        int[] result = {v, h};
        return result;

    }


}
