package ru.job4j.dynamiccontainer;

import org.junit.Before;
import org.junit.Test;

import java.util.Iterator;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class DynamicContainerTest {
    DynamicContainer<Integer> container;

    @Before
    public void toDo() {
        container = new DynamicContainer<>(5);
        container.add(1);
        container.add(2);
        container.add(3);
        container.add(4);
        container.add(5);
        container.add(6);
    }

    @Test
    public void whenAddSixThenSize10() {
        assertThat(container.getLength(), is(10));
    }

    @Test
    public void whenGetIndexFourThenFive() {
        assertThat(container.get(4), is(5));
    }

    @Test
    public void whenIteratorThenAll() {
        Iterator<Integer> it = container.iterator();
        int sum = 0;
        while (it.hasNext()) {
            sum += it.next();
        }
        assertThat(sum, is(21));
    }

}