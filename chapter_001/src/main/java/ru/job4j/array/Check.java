package ru.job4j.array;

/**
 * Check checks are all elements equal or not.
 * @author RomanM
 * @version 1.0, April 22, 2019
 */
public class Check {
    /**
     * this method implements the checking
     * @param data  - the array which we check
     * @return - if all elements are equal, it return true, else - false
     */
    public boolean mono(boolean[] data) {
        boolean result = false;
        for (int i = 0; i < data.length - 1; i++) {
            if (data[i] != data[i + 1]) {
                return false;
            } else {
                result = true;
            }
        }
        return result;
    }
}
