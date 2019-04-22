package ru.job4j.array;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Test for Turn
 * @author RomanM
 * @version 1.0 April 22, 2019
 */
public class TurnTest {
    /**
     * The test when the array has even number of elements
     */
    @Test
    public void whenEven() {
        Turn turn = new Turn();
        int[] array = {1, 2, 3, 4, 5, 6};
        int[] expect = {6, 5, 4, 3, 2, 1};
        int[] result = turn.back(array);
        assertThat(expect, is(result));
    }
    /**
     * The test when the array has odd number of elements
     */
    @Test
    public void whenOdd() {
        Turn turn = new Turn();
        int[] array = {1, 2, 3, 4, 5, 6, 7};
        int[] expect = {7, 6, 5, 4, 3, 2, 1};
        int[] result = turn.back(array);
        assertThat(expect, is(result));
    }
}
