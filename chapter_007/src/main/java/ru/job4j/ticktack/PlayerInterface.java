package ru.job4j.ticktack;

/**
 * the interface for players.
 */
public interface PlayerInterface {
    /**
     * the method for player's move.
     * @param board - the playing board.
     * @return - the array where 0 is vertical number and 1 is horizontal number.
     */
    int[] move(BoardInterface board);

    /**
     * returns the player's name.
     * @return the string with the player's name.
     */
    String getName();

    /**
     * returns the symbol which is assigned to the player.
     * @return the symbol.
     */
    char getSymbol();
}
