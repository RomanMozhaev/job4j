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
     * ch - the symbol of the player who has made the move.
     */
    private BoardInterface board;
    private boolean player;
    private PlayerInterface player1;
    private PlayerInterface player2;
    private char drawn = 'D';
    private char ch;

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
        do {
            System.out.println(this.board.printBoard());
            if (player) {
                player = false;
                playerMove(this.player1);
            } else {
                player = true;
                playerMove(this.player2);
            }
            if (this.board.checkDrawn()) {
                this.ch = drawn;
                break;
            }
        } while (!this.board.win(this.ch));
        System.out.println(this.board.printBoard());
        if (this.ch == this.player1.getSymbol()) {
            System.out.println(this.player1.getName() + " won!");
        } else if (this.ch == this.player2.getSymbol()) {
            System.out.println(this.player2.getName() + " won!");
        } else if (this.ch == drawn) {
            System.out.println("Drawn!");
        }
    }

    /**
     * the method which is responsible for the move of the player.
     */
    private void playerMove(PlayerInterface player) {
        System.out.println(player.getName() + " is moving");
        int[] move = player.move(this.board);
        this.board.setCell(move[0], move[1], player.getSymbol());
        this.ch = player.getSymbol();

    }
}
