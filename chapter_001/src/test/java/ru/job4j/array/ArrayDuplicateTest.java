package ru.job4j.array;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Test for ArrayDuplicate
 * @author RomanM
 * @version 1.0 April 24, 2019
 */

public class ArrayDuplicateTest {
    /**
     * Test Hi, lol, Hello, Cat
     */
    @Test
    public void whenRemoveAllDuplicates() {
        ArrayDuplicate arrayDuplicate = new ArrayDuplicate();
        String[] array = {"Hi", "lol", "Hello", "lol", "lol", "Hi", "Cat", "Hello", "Hello"};
        String[] expect = {"Hi", "lol", "Hello", "Cat"};
        String[] result = arrayDuplicate.remove(array);
        assertThat(result, is(expect));
    }
}