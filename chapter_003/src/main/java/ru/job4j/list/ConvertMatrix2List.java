package ru.job4j.list;

import java.util.ArrayList;
import java.util.List;

/**
 * The class converts array to List
 * @author RomanM
 * @version June 1, 2019
 */

public class ConvertMatrix2List {
    /**
     * the method does a conversation array to  List
     * @param array - the array for conversation to List
     * @return - the list of elements from the array
     */
    public List<Integer> toList(int[][] array) {
        List<Integer> list = new ArrayList<>();
        for (int[] row : array) {
            for (int elmnt : row) {
                list.add(elmnt);
            }
        }
        return list;
    }
}