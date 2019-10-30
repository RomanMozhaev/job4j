package ru.job4j.ticktack;

/**
 * the class for user as a players.
 */
public class UserPlayer implements PlayerInterface {
    /**
     * input - the object for getting the user's enter.
     * name - the user name
     * symbol - the symbol which is assigned to the user.
     */
    private Input input = new ConsoleInput();
    private String name;
    private char symbol;

    public UserPlayer(String name, char symbol) {
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
        int size = board.getBoardSize();
        int[] result = new int[size];
        boolean cycle = true;
        while (cycle) {
            String answer = this.input.ask("Chose one cell, make a move. First vertical, second horizontal (12): ");
            if (answer.length() == 2) {
                try {
                    int v = Integer.parseInt(answer.substring(0, 1));
                    int h = Integer.parseInt(answer.substring(1, 2));
                    if (v > 0 && v <= size && h > 0 && h <= size) {
                        if (board.checkCell(v, h)) {
                            result[0] = v;
                            result[1] = h;
                            cycle = false;
                        }
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Try again.");
                }
            }
        }
        return result;
    }
}
