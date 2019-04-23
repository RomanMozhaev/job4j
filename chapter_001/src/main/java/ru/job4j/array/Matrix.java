package ru.job4j.array;

/**
 * Matrix creates a multiplication table
 * @author RomanM
 * @version 1.0, April 23, 2019
 */
public class Matrix {
    /**
     * this method creates a table
     * @param size - the size of table
     * @return - return the filled table
     */
    public int[][] multiple(int size) {
        int[][] matrix = new int[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                matrix[i][j] = (i + 1) * (j + 1);
            }
        }
        return matrix;
    }
}
