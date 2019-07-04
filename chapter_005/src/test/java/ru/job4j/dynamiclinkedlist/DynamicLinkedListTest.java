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
    @Test
    public void whenDeletedThenNext() {
        this.list.remove(4);
        assertThat(this.list.get(4), is(6));
    }
    @Test
    public void whenDeletedLastThenSizeFive() {
        this.list.remove(5);
        assertThat(this.list.getLength(), is(5));
    }

    @Test
    public void whenDeletedFirstThenSecondHasIndexZero() {
        this.list.remove(0);
        assertThat(this.list.get(0), is(2));
    }

    @Test
    public void whenDeletedAllInsteadOneThenSix() {
        this.list.remove(0);
        this.list.remove(0);
        this.list.remove(0);
        this.list.remove(0);
        this.list.remove(0);
        this.list.remove(0);

        assertThat(this.list.getLength(), is(0));
    }
    @Test
    public void whenDeletedAllInsteadOneThenFive() {
        this.list.remove(0);
        this.list.remove(0);
        this.list.remove(0);
        this.list.remove(0);
        this.list.remove(1);


        assertThat(this.list.get(0), is(5));
    }


}