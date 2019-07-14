package ru.job4j.tree;
import org.junit.Test;

import java.util.Iterator;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;
public class TreeTest {
    @Test
    public void when6ElFindLastThen6() {
        Tree<Integer> tree = new Tree<>(1);
        tree.add(1, 2);
        tree.add(1, 3);
        tree.add(1, 4);
        tree.add(4, 5);
        tree.add(5, 6);
        assertThat(
                tree.findBy(6).isPresent(),
                is(true)
        );
    }
    @Test
    public void when6ElFindNotExitThenOptionEmpty() {
        Tree<Integer> tree = new Tree<>(1);
        tree.add(1, 2);
        assertThat(
                tree.findBy(7).isPresent(),
                is(false)
        );
    }

    @Test
    public void whenIteratorThenSum54() {
        Tree<Integer> tree = new Tree<>(1);
        tree.add(1, 2);
        tree.add(1, 3);
        tree.add(1, 4);
        tree.add(4, 5);
        tree.add(5, 7);
        tree.add(5, 8);
        tree.add(8, 10);
        tree.add(10, 14);
        Iterator<Integer> it = tree.iterator();
        int sum = 0;
        while (it.hasNext()) {
            sum += it.next();
        }
        assertThat(sum, is(54));

    }

    @Test
    public void whenIsBinaryThenTrue() {
        Tree<Integer> tree = new Tree<>(1);
        tree.add(1, 2);
        tree.add(1, 3);
        tree.add(2, 4);
        tree.add(2, 5);
        tree.add(5, 7);
        tree.add(5, 8);
        tree.add(8, 10);
        tree.add(10, 14);

        assertTrue(tree.isBinary());

    }
    @Test
    public void whenIsNotBinaryThenFalse() {
        Tree<Integer> tree = new Tree<>(1);
        tree.add(1, 2);
        tree.add(1, 3);
        tree.add(2, 4);
        tree.add(2, 5);
        tree.add(5, 7);
        tree.add(5, 8);
        tree.add(5, 10);
        tree.add(10, 14);

        assertFalse(tree.isBinary());

    }
}
