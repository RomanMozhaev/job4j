package ru.job4j.simplequeue;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class SimpleQueueTest {

    SimpleQueue<Integer> queue = new SimpleQueue<>();

    @Before
    public void toDo() {
        queue.push(1);
        queue.push(2);
        queue.push(3);
        queue.push(4);
        queue.push(5);
    }

    @Test
    public void whenThen() {
        queue.poll();
        queue.push(6);
        queue.poll();
        assertThat(queue.poll(), is(3));
    }

}