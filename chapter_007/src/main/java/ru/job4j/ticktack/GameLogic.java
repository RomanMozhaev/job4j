package ru.job4j.ticktack;

/**
 * the class for the game logic.
 */
public class GameLogic {

    /**
     * board - the playing board.
     * player - the switch between two players.
     * player1 - the first player.
     * player2 - the second player.
     * drawn - the symbol for the draw game.
     */
    private BoardInterface board;
    private boolean player;
    private PlayerInterface player1;
    private PlayerInterface player2;
    private char drawn = 'D';

    /**
     * the constructor for the class.
     *
     * @param board   - the playing board.
     * @param player1 - the first player.
     * @param player2 - the second player.
     */
    public GameLogic(BoardInterface board, PlayerInterface player1, PlayerInterface player2) {
        this.board = board;
        this.player = true;
        this.player1 = player1;
        this.player2 = player2;
    }

    /**
     * the method which leads the game.
     */
    public void game() {
        this.board.initNewBoard();
        char ch;
        do {
            System.out.println(this.board.printBoard());
            if (player) {
                player = false;
                playerOneMove();
                ch = this.player1.getSymbol();
            } else {
                player = true;
                playerTwoMove();
                ch = this.player2.getSymbol();
            }
            if (this.board.checkDrawn()) {
                ch = drawn;
                break;
            }
        } while (!this.board.win(ch));
        System.out.println(this.board.printBoard());
        if (ch == this.player1.getSymbol()) {
            System.out.println(this.player1.getName() + " won!");
        } else if (ch == this.player2.getSymbol()) {
            System.out.println(this.player2.getName() + " won!");
        } else if (ch == drawn) {
            System.out.println("Drawn!");
        }
    }

    /**
     * the method which is responsible for the move of the first player.
     */
    private void playerOneMove() {
        int[] move = this.player1.move(this.board);
        this.board.setCell(move[0], move[1], this.player1.getSymbol());

    }

    /**
     * the method which is responsible for the move of the second player.
     */
    private void playerTwoMove() {
        int[] move = this.player2.move(this.board);
        this.board.setCell(move[0], move[1], this.player2.getSymbol());
    }


}
