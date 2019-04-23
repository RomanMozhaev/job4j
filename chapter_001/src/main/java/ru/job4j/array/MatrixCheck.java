package ru.job4j.array;

/**
 * MatrixCheck checks diagonals are mono or not
 * @author RomanM
 * @version 1.0 April 23, 2019
 */
public class MatrixCheck {
    /**
     * this method implements a checking
     * @param data - the matrix for checking
     * @return true if diagonals are mono.
     */
    public boolean mono(boolean[][] data) {
        boolean result = true;
        int height = data.length;
        int width = data[0].length;
        if (height != width) {
            return false;
        }
        for (int i = 0; i < height - 1; i++) {
                if ((data[i][i] != data[i + 1][i + 1]) || (data[i][height - i - 1] != data[i + 1][height - i - 2])) {
                    return false;
            }
        }
        return result;
    }
}
