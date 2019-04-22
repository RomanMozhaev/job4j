package ru.job4j.array;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
/**
 * the test for Square
 * @author RomanM
 * @version 1.0, April 22, 2019
 */
import org.junit.Test;

public class SquareTest {
    /**
     * Test. the array has 3 elements.
     */
    @Test
    public void whenBound3Then149() {
        int bound = 3;
        Square square = new Square();
        int[] result = square.calculate(bound);
        int[] expect = {1, 4, 9};
        assertThat(result, is(expect));
    }
    /**
     * Test. the array has 5 elements.
     */
    @Test
    public void whenBound5Then1491625() {
        int bound = 5;
        Square square = new Square();
        int[] result = square.calculate(bound);
        int[] expect = {1, 4, 9, 16, 25};
        assertThat(result, is(expect));
    }
    /**
     * Test. the array has 1 element.
     */
    @Test
    public void whenBound1Then1() {
        int bound = 1;
        Square square = new Square();
        int[] result = square.calculate(bound);
        int[] expect = {1};
        assertThat(result, is(expect));
    }
    /**
     * Test. the array has no elements.
     */
    @Test
    public void whenBound0ThenNone() {
        int bound = 0;
        Square square = new Square();
        int[] result = square.calculate(bound);
        int[] expect = {};
        assertThat(result, is(expect));
    }
}
