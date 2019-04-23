package ru.job4j.array;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Test for Matrix
 * @author RomanM
 */
public class MatrixTest {
    /**
     * Test. the Matrix size is 5
     */
    @Test
    public void whenMatrixSize5() {
        Matrix matrix = new Matrix();
        int size = 5;
        int[][] result = matrix.multiple(size);
        int[][] expect = {
                {1, 2, 3, 4, 5},
                {2, 4, 6, 8, 10},
                {3, 6, 9, 12, 15},
                {4, 8, 12, 16, 20},
                {5, 10, 15, 20, 25},

        };
        assertThat(result, is(expect));
    }
}
