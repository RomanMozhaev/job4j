package ru.job4j.sort;

import java.util.*;

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
        return sortedList;
    }

    /**
     * the method sorts the List based on names' lengths.
     * @param list - the list for sorting
     * @return sorted list.
     */
    public List<User> sortNameLength(List<User> list) {
        List<User> sortedList = list;
        sortedList.sort(new Comparator<User>() {
            @Override
            public int compare(User o1, User o2) {
                Integer lengthFirst = o1.getName().length();
                Integer lengthSecond = o2.getName().length();
                return lengthFirst.compareTo(lengthSecond);
            }
        });
        return sortedList;
    }

    /**
     * the method sorts using all field. First it sorts based on names' lexicography, then based on ages.
     * @param list for sorting
     * @return sorted list
     */
    public List<User> sortByAllFields(List<User> list) {
        List<User> sortedList = list;
        sortedList.sort(new Comparator<User>() {
            @Override
            public int compare(User o1, User o2) {
                final int result = o1.getName().compareTo(o2.getName());
                return result != 0 ? result : Integer.compare(o1.getAge(), o2.getAge());
            }
        });
        return sortedList;
    }
}
