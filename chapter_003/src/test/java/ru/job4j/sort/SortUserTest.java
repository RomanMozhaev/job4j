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
        List<User> list = new ArrayList<>(Arrays.asList(
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
        Set<User> result = sortUsers.sort(list);
        Set<User> expect = new TreeSet<>(Arrays.asList(
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

}