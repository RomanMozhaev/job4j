package ru.job4j.array;

/**
 * FindLoop looks for a number in the array
 * @author RomanM
 * @version 1.0 April 22, 2019
 */
public class FindLoop {
    /**
     * This method implements a searching
     * @param data - the array where we search
     * @param el - the number which we search
     * @return - if we find el, it will return the index of element.
     */
    public int indexOf(int[] data, int el) {
        int result = -1;
        for (int i = 0; i < data.length; i++) {
            if (data[i] == el) {
                result = i;
                break;
            }
        }
        return result;
    }
}
