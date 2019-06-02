package ru.job4j.sort;

import java.util.List;
import java.util.Set;
import java.util.TreeSet;

/**
 * Sortuser sorts the List and return Set
 */
public class SortUser {
    /**
     * the method returns sorted Set.
     * @param list - arraylist for sorting
     * @return sorted Set.
     */
    public Set<User> sort(List<User> list) {
        TreeSet<User> sortedList = new TreeSet<>();
        sortedList.addAll(list);
        sortedList.comparator();
        return sortedList;
    }
}
