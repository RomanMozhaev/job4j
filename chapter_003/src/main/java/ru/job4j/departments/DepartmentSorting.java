package ru.job4j.departments;

import java.util.*;

/**
 * the class for departments sorting
 * @author RomanM
 * @version June 13, 2019
 */
public class DepartmentSorting {
    /**
     * The method for preparing the list of departments
     * @param departments - array of departments
     * @return ArrayList of departments
     */
    private List<String> departmentsSet(String[] departments) {
        Set<String> set = new TreeSet<>();
        for (String dep : departments) {
            String name = "";
            for (int i = 0; i < dep.length(); i++) {
                if (dep.charAt(i) == '/') {
                    set.add(name);
                }
                name += dep.charAt(i);
            }
            set.add(name);
        }
        return new ArrayList<>(set);
    }

    /**
     * The method sorts departments in ascending order
     * @param departments - array of departments
     * @return list in ascending order
     */
    public List<String> departmentsAscendingSorting(String[] departments) {
        return departmentsSet(departments);
    }
    /**
     * The method sorts departments in descending order
     * @param departments - array of departments
     * @return list in descending order
     */
    public List<String> departmentsDescendingSorting(String[] departments) {
        List<String> list = departmentsSet(departments);
        Collections.sort(list, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                int result = 0;
                switch (o1.compareTo(o2)) {
                    case -1 :
                        result = 1;
                        break;
                    case 1 :
                        result = -1;
                        break;
                    default:
                        result = 0;
                }
                return result;
            }
        });

        return list;
    }


}
