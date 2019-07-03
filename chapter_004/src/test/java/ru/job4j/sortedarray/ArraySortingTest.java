package ru.job4j.sortedarray;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class ArraySortingTest {

    @Test
    public void whenTwoAndFourThenTwenty() {
        int[] numArray = {1, 2, 4, 5, 7, 9};
        ArraySorting sorting = new ArraySorting();
        int result = sorting.evenNumSquareSum(numArray);
        assertThat(result, is(20));
    }

}