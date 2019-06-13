package ru.job4j.departments;

import org.junit.Test;

import java.util.*;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class DepartmentSortingTest {
    /**
     * test when the departments are sorted in ascending order
     */
    @Test
    public void whenAscendingSort() {
        String[] departments = {"K1/SK1", "K1/SK2", "K1/SK1/SSK1", "K1/SK1/SSK2", "K2", "K2/SK1/SSK1", "K2/SK1/SSK2"};
        DepartmentSorting sorting = new DepartmentSorting();
        List<String> result = sorting.departmentsAscendingSorting(departments);
        List<String> expect = new ArrayList<>(Arrays.asList("K1", "K1/SK1", "K1/SK1/SSK1", "K1/SK1/SSK2", "K1/SK2", "K2", "K2/SK1", "K2/SK1/SSK1", "K2/SK1/SSK2"));
        assertThat(result, is(expect));
    }
    /**
    * test when the departments are sorted in descending order
    */
    @Test
    public void whenDescendingSort() {
        String[] departments = {"K1/SK1", "K1/SK2", "K1/SK1/SSK1", "K1/SK1/SSK2", "K2", "K2/SK1/SSK1", "K2/SK1/SSK2"};
        DepartmentSorting sorting = new DepartmentSorting();
        List<String> result = sorting.departmentsDescendingSorting(departments);
        List<String> expect = new ArrayList<>(Arrays.asList("K2", "K2/SK1", "K2/SK1/SSK2", "K2/SK1/SSK1", "K1", "K1/SK2", "K1/SK1", "K1/SK1/SSK2", "K1/SK1/SSK1"));
        assertThat(result, is(expect));
    }

}