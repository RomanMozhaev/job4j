package ru.job4j.comparator;

import java.util.Comparator;

/**
 * the class ListCompare has the method compare which compares two string lexicographically
 */
public class ListCompare implements Comparator<String> {
    /**
     * the method compares two string lexicographically
     * @param left - first string
     * @param right - second string
     * @return - result of comparing
     */
    @Override
    public int compare(String left, String right) {
        int result = 0;
        int i = 0;
        while (result == 0 & i < right.length() & i < left.length()) {
            result = Character.compare(left.charAt(i), right.charAt(i));
            i++;
        }
        return result != 0 ? result : Integer.compare(left.length(), right.length());
    }
}