package ru.job4j.iterator;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class MatrixIteratorTest {

    @Test
    public void whenTest() {
        int[][] matrix = {
            {1, 2, 3, 4},
            {5, 6, 7, 8, 9, 10},
            {11, 12},
            {13}
        };
        int[] expect = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13};
        int[] result = new int[13];
        MatrixIterator it = new MatrixIterator(matrix);
        for (int i = 0; i < 13; i++) {
            result[i] = it.next();
        }
        assertThat(result, is(expect));
    }

}