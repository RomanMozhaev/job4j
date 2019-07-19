package ru.job4j.simplehashmap;

import org.junit.Test;

import java.util.Iterator;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class SimpleHashMapTest {

    @Test
    public void whenInsertThenTrue() {
        SimpleHashMap<Integer, String> map = new SimpleHashMap<>();
        assertThat(map.insert(10, "First"), is(true));
    }

    @Test
    public void whenInsertSameThenFalse() {
        SimpleHashMap<Integer, String> map = new SimpleHashMap<>();
        map.insert(10, "First");
        assertThat(map.insert(10, "First"), is(true));
    }


    @Test
    public void whenInsertTwoLength4ThenLengthEight() {
        SimpleHashMap<Integer, String> map = new SimpleHashMap<>(4);
        map.insert(10, "First");
        map.insert(11, "Second");
        map.insert(12, "third");
        map.insert(12, "third");

        assertThat(map.getLength(), is(8));
    }


    @Test
    public void whenInsertThreeThenGetSecond() {
        SimpleHashMap<Integer, String> map = new SimpleHashMap<>(4);
        map.insert(10, "First");
        map.insert(11, "Second");
        map.insert(12, "third");
        map.insert(12, "third");

        assertThat(map.get(11), is("Second"));
    }

    @Test
    public void whenNoKeyThenNull() {
        SimpleHashMap<Integer, String> map = new SimpleHashMap<>(4);
        map.insert(10, "First");
        map.insert(11, "Second");
        map.insert(12, "third");
        map.insert(12, "third");

        assertNull(map.get(13));
    }

    @Test
    public void whenDeleteThenTrue() {
        SimpleHashMap<Integer, String> map = new SimpleHashMap<>(4);
        map.insert(10, "First");
        map.insert(11, "Second");
        map.insert(12, "third");
        map.insert(12, "third");

        assertTrue(map.delete(12));
        assertNull(map.get(12));
    }

    @Test
    public void whenIteratorThenSum() {
        SimpleHashMap<Integer, String> map = new SimpleHashMap<>(4);
        map.insert(10, "First");
        map.insert(11, "Second");
        map.insert(12, "third");
        map.insert(12, "third");
        Iterator it = map.iterator();
        it.next();
        it.next();
        it.next();
        assertFalse(it.hasNext());
    }


}