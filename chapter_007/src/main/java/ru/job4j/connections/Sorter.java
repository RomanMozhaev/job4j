package ru.job4j.connections;

import java.util.*;

/**
 * the class for value sorting by the group.
 * If lines of values has one or more equal values, they are contained in one group.
 */
public class Sorter {

    /**
     * the method finds repeats of the values and contains their lines into the map.
     * the map has key as the first met line number and the value as a set of rows/lines.
     * @param list - the list of the read values from the file.
     * @return - the map of the repeats.
     */
    public Map<Integer, Set<Integer>> findRepeats(List<List<SimpleElement>> list) {
        Map<SimpleElement, Integer> map = new HashMap<>();
        Map<Integer, Set<Integer>> repeat = new HashMap<>();
        SimpleElement empty = new SimpleElement("\"\"", -1);
        for (List<SimpleElement> row : list) {
            for (SimpleElement el : row) {
                if (!el.equals(empty)) {
                    if (map.containsKey(el)) {
                        int mapElementRow = map.get(el);
                        if (repeat.containsKey(mapElementRow)) {
                            repeat.get(mapElementRow).add(el.getRowNumber());
                        } else {
                            Set<Integer> set = new HashSet<>();
                            set.add(mapElementRow);
                            set.add(el.getRowNumber());
                            repeat.put(mapElementRow, set);
                        }
                    } else {
                        map.put(el, el.getRowNumber());
                    }
                }
            }
        }
        return repeat;
    }

    /**
     * the method groups repeated string into the new groups where line number
     * may contains only in one group.
     * @param repeat - the map of the sets
     * @return - the list of the sets.
     */
    public List<Set<Integer>> groupGroups(Map<Integer, Set<Integer>> repeat) {
        List<Set<Integer>> groupedRepeat
                = new ArrayList<>();
        for (Map.Entry<Integer, Set<Integer>> entry : repeat.entrySet()) {
            groupedRepeat.add(entry.getValue());
        }
        for (int i = 0; i < groupedRepeat.size(); i++) {
            List<Integer> del = new ArrayList<>();
            for (int j = i + 1; j < groupedRepeat.size(); j++) {
                for (int el : groupedRepeat.get(j)) {
                    if (groupedRepeat.get(i).contains(el)) {
                        groupedRepeat.get(i).addAll(groupedRepeat.get(j));
                        del.add(j);
                        break;
                    }
                }
            }
            for (int r = del.size() - 1; r >= 0; r--) {
                int d = del.get(r);
                groupedRepeat.remove(d);
            }

        }
    return groupedRepeat;
    }

    /**
     * the method sorts the groups basing on their size.
     * @param groupedRepeat - the groups of the rows which have equal values.
     * @return - the map where key means the size of the groups which are contained in the values.
     */
    public Map<Integer, List<Map<Integer, Set<Integer>>>> sortGroups(List<Set<Integer>> groupedRepeat) {
        Map<Integer, List<Map<Integer, Set<Integer>>>> result = new TreeMap<>((t1, t2) -> (t2 - t1));
        for (Set<Integer> set : groupedRepeat) {
            int size = set.size();
            Map<Integer, Set<Integer>> newMap = new HashMap<>();
            newMap.put(size, set);
            if (result.containsKey(size)) {
                result.get(size).add(newMap);
            } else {
                ArrayList<Map<Integer, Set<Integer>>> newList = new ArrayList<>();
                newList.add(newMap);
                result.put(size, newList);
            }
        }
        return result;
    }

    /**
     * the method counts the quantity of the groups which have more lines than limitNumber.
     * @param groupedRepeat - the groups if the lines
     * @param limitNumber - the limit number
     * @return - the quantity if the big groups.
     */
    public int calcBigGroups(List<Set<Integer>> groupedRepeat, int limitNumber) {
        int count = 0;
        for (Set<Integer> set : groupedRepeat) {
            if (set.size() > limitNumber) {
                count++;
            }
        }
        return count;
    }
}
