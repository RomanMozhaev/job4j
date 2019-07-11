package ru.job4j.hascycle;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class HasCycleTest {

    @Test
    public void whenCycleThenTrue() {
        Node<Integer> node1 = new Node<>(1, null);
        Node<Integer> node2 = new Node<>(2, node1);
        Node<Integer> node3 = new Node<>(3, node2);
        Node<Integer> node4 = new Node<>(4, node3);
        Node<Integer> node5 = new Node<>(5, node4);
        node1.next = node5;

        HasCycle<Integer> cycle = new HasCycle<>();
        assertThat(cycle.hasCycle(node1), is(true));

    }
    @Test
    public void whenCycleMiddleThenTrue() {
        Node<Integer> node1 = new Node<>(1, null);
        Node<Integer> node2 = new Node<>(2, node1);
        Node<Integer> node3 = new Node<>(3, null);
        Node<Integer> node4 = new Node<>(4, node3);
        Node<Integer> node5 = new Node<>(5, node4);
        node1.next = node5;
        node3.next = node5;

        HasCycle<Integer> cycle = new HasCycle<>();
        assertThat(cycle.hasCycle(node1), is(true));

    }
    @Test
    public void whenNoCycleThenFalse() {
        Node<Integer> node1 = new Node<>(1, null);
        Node<Integer> node2 = new Node<>(2, node1);
        Node<Integer> node3 = new Node<>(3, null);
        Node<Integer> node4 = new Node<>(4, node3);
        Node<Integer> node5 = new Node<>(5, node4);
        node1.next = node5;
        node3.next = null;

        HasCycle<Integer> cycle = new HasCycle<>();
        assertThat(cycle.hasCycle(node1), is(false));

    }

}