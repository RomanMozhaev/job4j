package ru.job4j.loop;

/**
 * Paint builds a pyramid.
 * @author RomanM
 * @version 1.0, April 21, 2019
 */

public class Paint {
    /**
     * this method builds the triangle with right side hypothenuse.
     * @param height - the height of the triangle
     * @return - triangle is printed by toString.
     */
    public String rightTrl(int height) {
        StringBuilder screen = new StringBuilder();
        int wight = height;
        for (int row = 0; row != height; row++) {
            for (int column = 0; column != wight; column++) {
                if (column <= row) {
                    screen.append("^");
                } else {
                    screen.append(" ");
                }
            }
            screen.append(System.lineSeparator());
        }
        return screen.toString();
    }
    /**
     * this method builds the triangle with left side hypothenuse.
     * @param height - the height of the triangle
     * @return - triangle is printed by toString.
     */
    public String leftTrl(int height) {
        StringBuilder screen = new StringBuilder();
        int wight = height;
        for (int row = 0; row != height; row++) {
            for (int column = 0; column != wight; column++) {
                if (wight - row - 1 <= column) {
                    screen.append("^");
                } else {
                    screen.append(" ");
                }
            }
            screen.append(System.lineSeparator());
        }
        return screen.toString();
    }
    /**
     * this method builds the pyramid.
     * @param height - the height of the pyramid.
     * @return - pyramid is printed by toString.
     */
    public String pyramid(int height) {
        StringBuilder screen = new StringBuilder();
        int wight = height * 2 - 1;
        for (int row = 0; row != height; row++) {
            for (int column = 0; column != wight; column++) {
                if (row >= height - column - 1 && row + height - 1 >= column) {
                    screen.append("^");
                } else {
                    screen.append(" ");
                }
            }
            screen.append(System.lineSeparator());
        }
        return screen.toString();
    }
}
