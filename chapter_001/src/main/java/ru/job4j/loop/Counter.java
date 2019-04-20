package ru.job4j.loop;

/**
 * The Counter calculates the  sum of all evens in the range.
 * @author RomanM
 * @version 1.0 April 20, 2019
 */

public class Counter {
    /**
     * add calculates the sum
     * @param start - the less number of the range
     * @param finish - the larger number of the range.
     * @return the sum if all evens in the range.
     */
    public int add(int start, int finish) {
        int sum = 0;
        for (int i = start; i <= finish; i++) {
            if (i % 2 == 0) {
                sum += i;
            }

        }
        return sum;
    }
}
