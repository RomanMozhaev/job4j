package ru.job4j.loop;

import java.util.function.BiPredicate;

/**
 * Paint builds a pyramid.
 * @author RomanM
 * @version 2.0, April 21, 2019
 * refactoring April 21, 2019
 */

public class Paint {
    /**
     * this method builds the triangle with right side hypothenuse.
     * @param height - the height of the triangle
     * @return - triangle is printed by toString.
     */
    public String rightTrl(int height) {
        return this.loopBy(
                height,
                height,
                (row, column) -> column <= row
        );
    }
    /**
     * this method builds the triangle with left side hypothenuse.
     * @param height - the height of the triangle
     * @return - triangle is printed by toString.
     */
    public String leftTrl(int height) {
        return this.loopBy(
                height,
                height,
                (row, column) -> height - row - 1 <= column
        );
    }
    /**
     * this method builds the pyramid.
     * @param height - the height of the pyramid.
     * @return - pyramid is printed by toString.
     */
    public String pyramid(int height) {
        return this.loopBy(
                height,
                height * 2 - 1,
                (row, column) -> row >= height - column - 1 && row + height - 1 >= column

        );
    }

    /**
     * This is a common method with a basic logic.
     * @param height - the height of pyramid or triangle
     * @param wight - wight of pyramid of triangle
     * @param predicate - the predicate for building of geometric object
     * @return - the object is built by toString
     */
    private String loopBy(int height, int wight, BiPredicate<Integer, Integer> predicate) {
        StringBuilder screen = new StringBuilder();
        for (int row = 0; row != height; row++) {
            for (int column = 0; column != wight; column++) {
                if (predicate.test(row, column)) {
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
