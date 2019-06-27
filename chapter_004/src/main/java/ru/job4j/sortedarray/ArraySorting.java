package ru.job4j.sortedarray;

import java.util.Arrays;

/**
 * the class for working with arrays of numbers.
 * @author RomanM
 * @version June 27, 2019
 */
public class ArraySorting {
    /**
     * the method calculates the sum of squared even numbers in the array
     * @param numArray - array with numbers
     * @return the sum
     */
    public int evenNumSquareSum(int[] numArray) {
        return Arrays.stream(numArray).filter(e -> e % 2 == 0).map(e -> e * e).reduce(0, Integer::sum);

    }
}
