package ru.job4j.array;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Test for FindLoop
 */
public class FindLoopTest {
    /**
     * Test. Array is {3, 4, 5}
     * searched number is 4
     * return is 1
     */
    @Test
    public void when345and4Then1() {
        FindLoop findLoop = new FindLoop();
        int[] data = {3, 4, 5};
        int el = 4;
        int result = findLoop.indexOf(data, el);
        int expect = 1;
        assertThat(expect, is(result));
    }
    /**
     * Test. Array is {3, 4, 5}
     * searched number is 7
     * return is -1
     */
    @Test
    public void when345and7ThenMinus1() {
        FindLoop findLoop = new FindLoop();
        int[] data = {3, 4, 5};
        int el = 7;
        int result = findLoop.indexOf(data, el);
        int expect = -1;
        assertThat(expect, is(result));
    }
    /**
     * Test. Array is {3, 4, 5, 10, 41, 17}
     * searched number is 41
     * return is 4
     */
    @Test
    public void when345104117and41Then4() {
        FindLoop findLoop = new FindLoop();
        int[] data = {3, 4, 5, 10, 41, 17};
        int el = 41;
        int result = findLoop.indexOf(data, el);
        int expect = 4;
        assertThat(expect, is(result));
    }
}
