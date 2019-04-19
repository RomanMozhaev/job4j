package ru.job4j.condition;

/**
 * This class looks for the greatest number.
 * @author RomanM
 * @version 1.0 April 19, 2019.
 */

public class Max {
    /**
     * The method compares two numbers and returns the greatest one.
     * @param first - first number.
     * @param second - second number.
     * @return - return the greatest number.
     */
    public int max(int first, int second) {
        int result = first > second ? first : second;
        return result;
    }

}
