package ru.job4j.condition;

import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

/**
 * the Test for Max.
 */
public class MaxTest {
    /**
     * Test for max method.
     * first number is greater than second number.
     */
    @Test
    public void whenFirstThenFirst() {
        Max max = new Max();
        int result = max.max(6, 5);
        assertThat(result, is(6));
    }
    /**
     * Test for max method.
     * second number is greater than first number.
     */
    @Test
    public void whenSecondThenSecond() {
        Max max = new Max();
        int result = max.max(1, 5);
        assertThat(result, is(5));
    }    /**
     * Test for max method.
     * first number and second number are same.
     */
    @Test
    public void whenFirstEqualSecondThenSecond() {
        Max max = new Max();
        int result = max.max(6, 6);
        assertThat(result, is(6));
    }
}
