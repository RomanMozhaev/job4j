package ru.job4j.array;


import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Test for Check
 */
public class CheckTest {
    /**
     * Test when all elements are true
     */
    @Test
    public void whenAllTrueThenTrue() {
        Check check = new Check();
        boolean[] data = {true, true, true};
        boolean result = check.mono(data);
        boolean expect = true;
        assertThat(result, is(expect));
    }
    /**
     * Test when all elements are false
     */
    @Test
    public void whenAllFalseThenTrue() {
        Check check = new Check();
        boolean[] data = {false, false, false};
        boolean result = check.mono(data);
        boolean expect = true;
        assertThat(result, is(expect));
    }
    /**
     * Test when there are different elements.
     */
    @Test
    public void whenAllNotEqualThenFalse() {
        Check check = new Check();
        boolean[] data = {false, true, false};
        boolean result = check.mono(data);
        boolean expect = false;
        assertThat(result, is(expect));
    }


}
