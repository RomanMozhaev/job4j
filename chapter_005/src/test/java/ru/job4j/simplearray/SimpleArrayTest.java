package ru.job4j.simplearray;

import org.junit.Before;
import org.junit.Test;

import java.util.Iterator;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class SimpleArrayTest {

    private SimpleArray<Integer> array;

    @Before
    public void doBeforeTest() {
        this.array = new SimpleArray<>(10);
        this.array.add(1);
        this.array.add(null);
        this.array.add(3);
        this.array.add(4);
        this.array.add(5);
        this.array.add(7);
    }

//    @Test
//    public void whenAddNullThenNoNull() {
//        assertThat(this.array.get(1), is(3));
//    }

    @Test
    public void whenSet10Then10() {
        this.array.set(1, 10);
        assertThat(this.array.get(1), is(10));
    }

    @Test
    public void whenRemove3Then4is3() {
        this.array.remove(3);
        assertThat(this.array.get(3), is(5));
    }

    @Test
    public void whenIteratorThenSumIs20() {
        Iterator<Integer> it = this.array.iterator();
        int sum = 0;
        while (it.hasNext()) {
            Integer element = it.next();
            if (element != null) {
                sum += element;
            }
        }
        assertThat(sum, is(20));
    }


}