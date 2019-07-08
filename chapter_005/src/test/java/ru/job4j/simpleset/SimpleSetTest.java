package ru.job4j.simpleset;

import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class SimpleSetTest {

    @Test
    public void whenAddedDuplicateThenNoDuplicate() {
        SimpleSet<Integer> set = new SimpleSet<>(10);
        set.add(1);
        set.add(2);
        set.add(3);
        set.add(3);
        set.add(4);
        set.add(5);
        assertThat(set.toString(), is("Simple Set = {1, 2, 3, 4, 5}"));
    }

}