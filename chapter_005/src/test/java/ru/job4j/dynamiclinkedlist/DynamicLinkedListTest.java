package ru.job4j.dynamiclinkedlist;

import org.junit.Test;
import org.junit.Before;

import java.util.Iterator;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class DynamicLinkedListTest {

    private DynamicLinkedList<Integer> list;

    @Before
    public void doBeforeTest() {
        this.list = new DynamicLinkedList<>();
        this.list.add(1);
        this.list.add(2);
        this.list.add(3);
        this.list.add(4);
        this.list.add(5);
        this.list.add(6);
    }

    @Test
    public void whenGetSecondThenTwo() {
        assertThat(this.list.get(4), is(5));
    }

    @Test
    public void whenIteratorThenSumIs21() {
        Iterator<Integer> it = this.list.iterator();
        int sum = 0;
        while (it.hasNext()) {
            sum += it.next();
        }
        assertThat(sum, is(21));
    }

}