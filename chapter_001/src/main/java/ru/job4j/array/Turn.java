package ru.job4j.array;

/**
 * Turn reverses the array
 * @author RomanM
 * @version 1.9 April 22, 2019
 */

public class Turn {
    /**
     * This method implements the reverse of the array
     * @param array - the array for reversing
     * @return - reversed array
     */
    public int[] back(int[] array) {
        int cycles = array.length / 2;
        for (int i = 0; i < cycles; i++) {
            int temp = array[i];
            array[i] = array[array.length - i - 1];
            array[array.length - i - 1] = temp;
        }
        return array;
    }
}
