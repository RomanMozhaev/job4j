package ru.job4j.array;

import java.util.Arrays;
/**
 * ArrayDuplicate removes all duplication in String-array.
 * @author RomanM
 * @version 1.0 April 24, 2019
 */
public class ArrayDuplicate {
    /**
     * this method implements removing in the array.
     * @param array - the array where we remove duplications.
     * @return the array without duplications.
     */
    public String[] remove(String[] array) {
        int length = array.length;
        for (int i = 0; i < length - 1; i++) {
            for (int j = i + 1; j < length; j++) {
                if (array[i].equals(array[j])) {
                    i = 0;
                    length--;
                    for (int k = j; k < length; k++) {
                        String temp = array[k];
                        array[k] = array[k + 1];
                        array[k + 1] = temp;
                    }
                }
            }
        }
        return Arrays.copyOf(array, length);
    }
}
