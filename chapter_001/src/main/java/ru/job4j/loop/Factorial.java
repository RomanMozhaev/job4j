package ru.job4j.loop;

/**
 * This class calculates a factorial
 * @author RomanM
 * @version 1.0 April 21, 2019
 */
public class Factorial {
    /**
     * This method calculates the factorial if the number.
     * @param n - the number
     * @return if n<0, it returns -1, if n=0, it returns 0!=1,
     * if n>0, it returns n!
     */
    public int calc(int n) {
        int result = 1;
        if (n < 0) {
            return -1;
        }
        if (n == 0) {
            return 1;
        }
        for (int i = 1; i <= n; i++) {
            result *= i;
        }
        return result;
    }
}
