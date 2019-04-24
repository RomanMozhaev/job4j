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
        int arrayLength = array.length;
        for (int i = 0; i < arrayLength - 1; i++) {
            for (int j = i+1; j < arrayLength; j++) {
                if (array[i].equals(array[j])) {
                    // the main cycle must start from the very beginning to avoid a multiple duplication.
                    i = 0;
                    // decrement the array length, because we don't need to check a duplicate again
                    arrayLength--;
                    //move a duplicate to the end of the array
                    for (int k = j; k < arrayLength; k++) {
                        String temp = array[k];
                        array[k] = array[k + 1];
                        array[k + 1] = temp;
                    }
                }
            }
        }
        return Arrays.copyOf(array, arrayLength);
    }
}
