package ru.job4j.array;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
/**
* Test forBubbleSort
* @author RomanM
* @version 1.0 April 23, 2019
*/
public class BubbleSortTest {
	/**
	* Test. input in array with ten int numbers
	*/
    @Test
    public void whenSortArrayWithTenElementsThenSortedArray() {
		BubbleSort bubbleSort = new BubbleSort();
		int[] array = {1, 5, 4, 2, 3, 1, 7, 8, 0, 5};
		int[] result = bubbleSort.sort(array);
		int[] expect = {0, 1, 1, 2, 3, 4, 5, 5, 7, 8};
		assertThat(result, is(expect));
    }
}