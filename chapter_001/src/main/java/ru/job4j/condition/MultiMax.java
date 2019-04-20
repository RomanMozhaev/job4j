package ru.job4j.condition;

/**
 * MultiMax finds the largest number between three numbers.
 * @author RomanM.
 * @version 1.0, April 20, 2019.
 */

public class MultiMax {
    /**
     * the method finds the maxi
     * @param first number.
     * @param second number.
     * @param third number.
     * @return - the largest number.
     */
    public int max(int first, int second, int third) {
       int result = first > second ? first : second;
       result = result > third ? result : third;
       return result;
    }
}
