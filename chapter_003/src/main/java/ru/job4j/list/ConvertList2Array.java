package ru.job4j.list;

import java.util.List;

public class ConvertList2Array {
    public int[][] toArray(List<Integer> list, int rows) {
        int cells = list.size();
        int columns = cells / rows;
        if (cells % rows != 0) {
            columns++;
        }
        int column = 0;
        int row = 0;
        int[][] array = new int[rows][columns];
        for (int elmnt : list) {
            array[row][column] = elmnt;
            column++;
            if (column == columns) {
                column = 0;
                row++;
            }
        }
        return array;
    }
}
