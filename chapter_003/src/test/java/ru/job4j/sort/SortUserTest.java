package ru.job4j.sort;

import org.junit.Test;

import java.util.*;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Tests for SortUser
 */
public class SortUserTest {
    /**
     * test. The result is sorted and without second User Polina.
     */
    @Test
    public void whenListThenSortedSet() {
        List<User> list = List.of(
                new User("Anna", 23),
                new User("Yulia", 24),
                new User("Alex", 12),
                new User("Polina", 34),
                new User("Polina", 34),
                new User("Eric", 34),
                new User("Polina", 32),
                new User("Sergey", 18),
                new User("Alla", 56)
        );
        SortUser sortUsers = new SortUser();
        Set<User> result = sortUsers.sort(list);
        Set<User> expect = new TreeSet<>(Set.of(
                new User("Alex", 12),
                new User("Sergey", 18),
                new User("Anna", 23),
                new User("Yulia", 24),
                new User("Polina", 32),
                new User("Eric", 34),
                new User("Polina", 34),
                new User("Alla", 56)
        ));
        assertThat(result, is(expect));
    }

    /**
     * test of sorting by name length
     */
    @Test
    public void whenListThenSortedByNameLength() {
        List<User> list = new ArrayList<>(List.of(
                new User("Anna", 23),
                new User("Yulia", 24),
                new User("Alex", 12),
                new User("Polina", 34),
                new User("Polina", 34),
                new User("Eric", 34),
                new User("Polina", 32),
                new User("Sergey", 18),
                new User("Alla", 56)
        ));
        SortUser sortUsers = new SortUser();
        List<User> result = sortUsers.sortNameLength(list);
        List<User> expect = List.of(
                new User("Anna", 23),
                new User("Alex", 12),
                new User("Eric", 34),
                new User("Alla", 56),
                new User("Yulia", 24),
                new User("Polina", 34),
                new User("Polina", 34),
                new User("Polina", 32),
                new User("Sergey", 18)
        );
        assertThat(result, is(expect));
    }

    /**
     * test of sorting which uses all fields.
     */
    @Test
    public void whenListThenSortedByAllFields() {
        List<User> list = new ArrayList<>(List.of(
                new User("Anna", 23),
                new User("Yulia", 24),
                new User("Alex", 15),
                new User("Polina", 34),
                new User("Polina", 34),
                new User("Polina", 36),
                new User("Polina", 33),
                new User("Alex", 17)
        ));
        SortUser sortUsers = new SortUser();
        List<User> result = sortUsers.sortByAllFields(list);
        List<User> expect = new ArrayList<>(List.of(
                new User("Alex", 15),
                new User("Alex", 17),
                new User("Anna", 23),
                new User("Polina", 33),
                new User("Polina", 34),
                new User("Polina", 34),
                new User("Polina", 36),
                new User("Yulia", 24)
        ));
        assertThat(result, is(expect));
    }


}