package ru.job4j.loop;

/**
 * The Sorting and Aggregating aggregates two sorted arrays and leave them sorted.
 * @author RomanM
 * @version 1.0 April 30, 2019
 */
public class SortingAndAggregating {
    /**
     * This method aggregates two sorted method and sorts a new one.
     * @param arrayA - first sorted array for aggregation.
     * @param arrayB -second sorted array for aggregation.
     * @return - new sorted array, which includes two previous arrays.
     */
    public int[] sorting(int[] arrayA, int[] arrayB) {
        int lengthA = arrayA.length;
        int lengthB = arrayB.length;
        int[] arrayC = new int[lengthA+lengthB];
        int a = 0;
        int b = 0;
        for (int c = 0; c < arrayC.length; c++) {
            if (a == lengthA) {
                arrayC[c] = arrayB[b];
                b++;
            } else if (b == lengthB) {
                arrayC[c] = arrayA[a];
                a++;
            } else {
                if (arrayA[a] < arrayB[b]) {
                    arrayC[c] = arrayA[a];
                    a++;
                } else {
                    arrayC[c] = arrayB[b];
                    b++;
                }
            }
        }
        return arrayC;
    }
}
