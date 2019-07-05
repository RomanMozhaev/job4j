package ru.job4j.stack;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class SimpleStackTest {

    public SimpleStack<Integer> stack = new SimpleStack<>();

    @Before
    public void toDo() {
        stack.push(1);
        stack.push(2);
        stack.push(3);
        stack.push(4);
    }

    @Test
    public void whenPullTwiceThenThree() {
        stack.poll();
        assertThat(stack.poll(), is(3));
    }

    @Test
    public void whenPullThenTree() {
        stack.poll();
        stack.poll();
        stack.poll();
        stack.poll();
        assertThat(stack.isEmpty(), is(true));
    }



}