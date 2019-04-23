package ru.job4j.array;

/**
* BubbleSort sorts numbers in array. 
* @author RomanM
* @version 1.0 April 23, 2019
*/
public class BubbleSort {
	/**
	* This method implements the sorting of array.
	* @param array - the array for sorting.
	*/
	public int[] sort(int[] array) {
			for (int j = 1; j < array.length; j++) {
				for (int i = 0; i < array.length - j; i++) {
					if (array[i] > array[i + 1]) {
						int temp = array[i];
						array[i] = array [i + 1];
						array[i + 1] = temp;
					}
				}
			}
			return array;
	}
}