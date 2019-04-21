package ru.job4j.loop;

/**
 * the Board builds a chess board
 * @author RomanM
 * @version 1.0, April 21, 2019
 */
public class Board {
    /**
     * the method builds a board
     * @param wight - the wight of the board
     * @param height - the height of the board
     * @return  the board as String with toString method
     */
    public String paint(int wight, int height) {
        StringBuilder screen = new StringBuilder();
        String ln = System.lineSeparator();
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < wight; j++) {
                if ((i + j) % 2 == 0) {
                    screen.append("X");
                } else {
                    screen.append(" ");
                }
            }
            screen.append(ln);
        }
        return screen.toString();
    }
}
