package ru.job4j.array;

/**
 * Square creates an array where each element contains its square index.
 * @author RomanM
 * @version 1.0 April 22, 2019
 */
public class Square {
    /**
     * This method creates the array described previously.
     * @param bound - the size of the array
     * @return - the array which contains square index of each element
     */
    public int[] calculate(int bound) {
        int[] result = new int[bound];
        for (int i = 0; i < bound; i++) {
            result[i] = (int) Math.pow(i + 1, 2);
        }
        return result;
    }
}
