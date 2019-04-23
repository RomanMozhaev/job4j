package ru.job4j.array;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Test for MatrixCheck
 * @author RomanM
 * @version 1.0 April 23, 2019
 */
public class MatrixCheckTest {
    /**
     * Test. both diagonals are true
     */
    @Test
    public void whenDiagonalsAreMonoThenTrue() {
        MatrixCheck matrixCheck = new MatrixCheck();
        boolean[][] data = {
                {true, false, true},
                {true, true, false},
                {true, true, true},
        };
        boolean result = matrixCheck.mono(data);
        boolean expect = true;
        assertThat(result, is(expect));
    }
    /**
     * Test. one is true, second is false
     * result is true
     */
    @Test
    public void whenDiagonalMonoMatrix4ThenTrue() {
        MatrixCheck matrixCheck = new MatrixCheck();
        boolean[][] data = {
                {true, false, true, false},
                {true, true, false, false},
                {true, false, true, false},
                {false, true, true, true},
        };
        boolean result = matrixCheck.mono(data);
        boolean expect = true;
        assertThat(result, is(expect));
    }

    /**
     * one is true, second is not mono
     * result is false
     */
    @Test
    public void whenDiagonalNotMonoThenFalse() {
        MatrixCheck matrixCheck = new MatrixCheck();
        boolean[][] data = {
                {false, false, true},
                {true, true, false},
                {true, true, true},
        };
        boolean result = matrixCheck.mono(data);
        boolean expect = false;
        assertThat(result, is(expect));
    }

    /**
     * the array is not a matrix. width and height are different
     * result is false
     */
    @Test
    public void whenArrayIsNotMatrixThenFalse() {
        MatrixCheck matrixCheck = new MatrixCheck();
        boolean[][] data = {
                {true, true, true},
                {true, true, true},
                {true, true, true},
                {true, true, true},
        };
        boolean result = matrixCheck.mono(data);
        boolean expect = false;
        assertThat(result, is(expect));
    }
}
