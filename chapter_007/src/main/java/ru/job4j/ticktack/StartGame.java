package ru.job4j.ticktack;

import java.util.HashMap;
import java.util.Map;

/**
 * the class for adjusting the game.
 */
public class StartGame {

    /**
     * LN - the line separator.
     * input - the object for getting the user enter.
     * boardSize - the playing board size.
     * actionMap - the map with available options.
     * exit - the number in the selection menu for exit.
     * userName - the name of the real user.
     */
    private static final String LN = System.lineSeparator();
    private Input input;
    private int boardSize;
    private Map<Integer, Action> actionMap;
    private int exit;

    /**
     * the constructor for the class.
     *
     * @param input     - the object for getting user's enter.
     * @param boardSize - the size of the board.
     */
    public StartGame(Input input, int boardSize) {
        this.input = input;
        this.boardSize = boardSize;
        this.actionMap = new HashMap<>();

    }

    /**
     * returns the size of the playing board.
     *
     * @return the board size.
     */
    public int getBoardSize() {
        return boardSize;
    }

    /**
     * the method for the launching of the game.
     *
     * @param args is not used.
     */
    public static void main(String[] args) {
        StartGame startGame = new StartGame(new ConsoleInput(), 3);
        startGame.start();
    }

    /**
     * the method which starts the game.
     */
    public void start() {
        init();
        startMenu();
    }

    /**
     * the method for the menu initialization.
     */
    private void init() {
        this.actionMap.put(1, new ComputerStart("Computer starts the game."));
        this.actionMap.put(2, new UserStart("User starts the game."));
        this.actionMap.put(3, new twoUsers("Two Users' game."));
        this.exit = this.actionMap.size() + 1;
    }

    /**
     * the method for menu printing and implementing the selected option.
     */
    private void startMenu() {
        int answer = 0;
        while (answer != this.exit) {
            StringBuilder sb = new StringBuilder();
            sb.append("Welcome to Tick Tack Toe game!")
                    .append(LN);
            for (int i = 1; i < this.exit; i++) {
                sb.append(i)
                        .append(". ")
                        .append(this.actionMap.get(i).getTitle())
                        .append(LN);
            }
            sb.append(this.exit)
                    .append(". Exit")
                    .append(LN);
            System.out.println(sb);
            answer = askUserInput("Select: ", this.exit);
//            this.userName = this.input.ask("Enter your name:");
            this.actionMap.get(answer).act();
        }
    }

    /**
     * the method for getting the input from the user.
     *
     * @param question - the question to the user.
     * @param max      - the last number in the menu (exit).
     * @return - the user selection.
     */
    private int askUserInput(String question, int max) {
        int answer = 0;
        boolean cycle = true;
        do {
            System.out.println(String.format("Enter the digit more than 0 and less than %s.", max + 1));
            String enter = this.input.ask("Select: ");
            try {
                answer = Integer.parseInt(enter);
                if (answer > 0 && answer <= max) {
                    cycle = false;
                }
            } catch (NumberFormatException e) {
                System.out.println("You have not entered correctly.");
            }
        } while (cycle);
        return answer;
    }

    /**
     * the class for the option when the computer starts the game.
     */
    private class ComputerStart implements Action {
        /**
         * the title of the option.
         */
        private String title;

        public ComputerStart(String title) {
            this.title = title;
        }

        @Override
        public void act() {
            BoardInterface board = new Board(getBoardSize());
            GameLogic logic = new GameLogic(board, new ComputerPlayer("Computer", 'X'), new UserPlayer(input.ask("Enter your name:"), '0'));
            logic.game();
        }

        @Override
        public String getTitle() {
            return this.title;
        }
    }

    /**
     * the class for the option when the user starts the game.
     */
    private class UserStart implements Action {
        /**
         * the title of the option.
         */
        private String title;

        public UserStart(String title) {
            this.title = title;
        }

        @Override
        public void act() {
            BoardInterface board = new Board(getBoardSize());
            GameLogic logic = new GameLogic(board, new UserPlayer(input.ask("Enter your name:"), 'X'), new ComputerPlayer("Computer", '0'));
            logic.game();
        }

        @Override
        public String getTitle() {
            return this.title;
        }
    }
    /**
     * the class for the option when two users play the game.
     */
    private class twoUsers implements Action {
        /**
         * the title of the option.
         */
        private String title;

        public twoUsers(String title) {
            this.title = title;
        }

        @Override
        public void act() {
            BoardInterface board = new Board(getBoardSize());
            GameLogic logic = new GameLogic(board, new UserPlayer(input.ask("Enter the name of the first player:"), 'X'),
                    new UserPlayer(input.ask("Enter the name of the second player:"), '0'));
            logic.game();
        }

        @Override
        public String getTitle() {
            return this.title;
        }
    }


}
