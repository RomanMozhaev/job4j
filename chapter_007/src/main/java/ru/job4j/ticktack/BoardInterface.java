package ru.job4j.ticktack;

/**
 * the interface for board classes.
 */
public interface BoardInterface {
    /**
     * returns the size of the board.
     *
     * @return - the board size.
     */
    int getBoardSize();

    /**
     * creates a new clean board.
     */
    void initNewBoard();

    /**
     * sets the moves of players on the board.
     *
     * @param vertical   - the line number.
     * @param horizontal - the column number.
     * @param ch         - the symbol which is used for the corresponding player.
     */
    void setCell(int vertical, int horizontal, char ch);

    /**
     * checks if the cell is empty or not.
     *
     * @param vertical   - the line number.
     * @param horizontal - the column number.
     * @return true if the cell is empty.
     */
    boolean checkCell(int vertical, int horizontal);

    /**
     * prints the current board with all made moves.
     *
     * @return - the StringBuilder for printing
     */
    StringBuilder printBoard();

    /**
     * checks whether the player, which had made the last move, won.
     *
     * @param ch - the player symbol
     * @return - true if the player won.
     */
    boolean win(char ch);

    /**
     * checks whether the draw game happened.
     *
     * @return true if it happened.
     */
    boolean checkDrawn();
}
