package ru.job4j.loop;

import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

/**
 * Test for Factorial
 * @author RomanM
 * @version 1.0, April 21, 2019
 */
public class FactorialTest {
    /**
     * Test. The factorial of 5 is 120
     */
    @Test
    public void whenFiveThenOneHundredTwenty() {
        Factorial factorial = new Factorial();
        int result = factorial.calc(5);
        assertThat(result, is(120));
    }
    /**
     * Test. The factorial of -2 does not exist
     */
    @Test
    public void whenMinusTwoThenMinusOne() {
        Factorial factorial = new Factorial();
        int result = factorial.calc(-2);
        assertThat(result, is(-1));
    }
    /**
     * Test. The factorial of zero is one.
     */
    @Test
    public void whenZeroThenOne() {
        Factorial factorial = new Factorial();
        int result = factorial.calc(0);
        assertThat(result, is(1));
    }
    /**
     * Test. The factorial of 3 is 6
     */
    @Test
    public void whenThreeThenSix() {
        Factorial factorial = new Factorial();
        int result = factorial.calc(3);
        assertThat(result, is(6));
    }

}
