package ru.job4j.condition;

import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

/**
 * Test for MultiMax
 * @version 1.0 Aprl 20, 2019
 * @author RomanM
 */
public class MultiMaxTest {
    /**
     * Test. the first is the largest.
     */
    @Test
    public void whenFirstThenFirst() {
        MultiMax multiMax = new MultiMax();
        int result = multiMax.max(77, 30, 20);
        assertThat(result, is(77));

    }

    /**
     * Test. The second is the largest.
     */
    @Test
    public void whenSecondThenSecond() {
        MultiMax multiMax = new MultiMax();
        int result = multiMax.max(71, 72, 0);
        assertThat(result, is(72));
    }

    /**
     * Test. The third is the largest.
     */
    @Test
    public void whenThirdThenThird() {
        MultiMax multiMax = new MultiMax();
        int result = multiMax.max(-1, 30, 50);
        assertThat(result, is(50));
    }

    /**
     * Test. All numbers are equal.
     */
    @Test
    public void whenAllSameThenThird() {
        MultiMax multiMax = new MultiMax();
        int result = multiMax.max(77, 77, 77);
        assertThat(result, is(77));
    }
}
