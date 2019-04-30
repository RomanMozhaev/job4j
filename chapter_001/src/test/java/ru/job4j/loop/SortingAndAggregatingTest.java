package ru.job4j.loop;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * the test for SortingAndAggregating
 * @author RomanM
 * @version 1.0 April 20, 2019
 */
public class SortingAndAggregatingTest {
    /**
     * first test.
     */
    @Test
    public void whenTwoSortedArraysMixed() {
        SortingAndAggregating sort = new SortingAndAggregating();
        int[] arrayA = {1, 4, 6, 8, 8, 9, 9};
        int[] arrayB = {1, 2, 3, 7, 8, 9};
        int[] expect = {1, 1, 2, 3, 4, 6, 7, 8, 8, 8, 9, 9, 9};
        int[] result = sort.sorting(arrayA, arrayB);
        assertThat(result, is(expect));
    }

    /**
     * the test from the task
     */
    @Test
    public void whenTwoTwo() {
        SortingAndAggregating sort = new SortingAndAggregating();
        int[] arrayA = {1, 3};
        int[] arrayB = {2, 4};
        int[] expect = {1, 2, 3, 4};
        int[] result = sort.sorting(arrayA, arrayB);
        assertThat(result, is(expect));
    }

    /**
     * the test. ArrayA is ArrayB from the first test. ArrayB is ArrayA for the first test.
     */
    @Test
    public void whenTwoSortedArrays() {
        SortingAndAggregating sort = new SortingAndAggregating();
        int[] arrayA = {1, 2, 3, 7, 8, 9};
        int[] arrayB = {1, 4, 6, 8, 8, 9, 9};
        int[] expect = {1, 1, 2, 3, 4, 6, 7, 8, 8, 8, 9, 9, 9};
        int[] result = sort.sorting(arrayA, arrayB);
        assertThat(result, is(expect));
    }

}