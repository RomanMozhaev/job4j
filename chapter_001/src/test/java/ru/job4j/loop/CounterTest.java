package ru.job4j.loop;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * The test for Counter.
 * @author RomanM
 * @version 1.0, April 20, 2019
 */
public class CounterTest {
    /**
     * Test. The range is 1 to 10
     * Result is 30
     */
    @Test
    public void whenOneToTenThenThirty() {
        Counter counter = new Counter();
        int result = counter.add(1, 10);
        assertThat(result, is(30));
    }

    /**
     * Test.The range is -10 to 10
     * Result is 0
     */
    @Test
    public void whenMinusTenToTenThenZero() {
        Counter counter = new Counter();
        int result = counter.add(-10, 10);
        assertThat(result, is(0));
    }
    /**
     * Test. There is no range.
     * Result is zero.
     */
    @Test
    public void whenTenToEightThenZero() {
        Counter counter = new Counter();
        int result = counter.add(10, 8);
        assertThat(result, is(0));
    }

    /**
     * Test.The range is 10 to 10
     * Result is 10
     */
    @Test
    public void whenTenToTenThenTen() {
        Counter counter = new Counter();
        int result = counter.add(10, 10);
        assertThat(result, is(10));
    }

}
