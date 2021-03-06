package ru.job4j.list;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class ConvertList2ArrayTest {
    @Test
    public void when7ElementsThen9() {
        ConvertList2Array list = new ConvertList2Array();
        int[][] result = list.toArray(
                List.of(1, 2, 3, 4, 5, 6, 7),
                3
        );
        int[][] expect = {
                {1, 2, 3},
                {4, 5, 6},
                {7, 0, 0}
        };
        assertThat(result, is(expect));
    }

    @Test
    public void when3ArraysThen1List() {
        ConvertList2Array list = new ConvertList2Array();
        int[] firstArray = {1, 2, 3, 4, 5};
        int[] secondArray = {11, 12, 13};
        int[] thirdArray = {21, 22, 23, 24, 25, 26};
        List<int[]> arrayList = List.of(firstArray, secondArray, thirdArray);
        List<Integer> result = list.convert(arrayList);
        List<Integer> expect = new ArrayList<>(List.of(1, 2, 3, 4, 5, 11, 12, 13, 21, 22, 23, 24, 25, 26));
        assertThat(result, is(expect));
    }

}