package ru.job4j.threadsafe;

import org.junit.Test;

import java.util.Iterator;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class ThreadSafeLinkedListTest {

    /**
     * Tests that ThreadSafeLinkedList creates fail-safe iterator.
     */
    @Test
    public void whenThen() {
        ThreadSafeList<String> list = new ThreadSafeLinkedList<>();
        list.add("1");
        list.add("2");
        list.add("3");
        list.add("4");
        Iterator<String> it1 = list.iterator();
        list.remove(1);
        StringBuilder sb1 = new StringBuilder();
        while (it1.hasNext()) {
            sb1.append(it1.next());
        }
        Iterator<String> it2 = list.iterator();
        StringBuilder sb2 = new StringBuilder();
        while (it2.hasNext()) {
            sb2.append(it2.next());
        }
        assertThat(sb1.toString(), is("1234"));
        assertThat(sb2.toString(), is("134"));
    }
}