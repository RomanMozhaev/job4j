package ru.job4j.array;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
/**
* Test for ArrayChar
*/
public class ArrayCharTest {
	/**
	* Test. if the prefix is the first part of the word, then true
	*/
    @Test
    public void whenStartWithPrefixThenTrue() {
        ArrayChar word = new ArrayChar("Hello");
        boolean result = word.startWith("He");
        assertThat(result, is(true));
    }
	/**
	* Test. if the prefix is not the first part of the word, then true
	*/
    @Test
    public void whenNotStartWithPrefixThenFalse() {
        ArrayChar word = new ArrayChar("Hello");
        boolean result = word.startWith("Hi");
        assertThat(result, is(false));
    }
	/**
	* Test. if the prefix is longer thenthe word, then false
	*/
	@Test
    public void whenPrefixIsLongerThenFalse() {
        ArrayChar word = new ArrayChar("Hello");
        boolean result = word.startWith("Hellohen");
        assertThat(result, is(false));
    }
	/**
	* Test. if the prefix is equal the word, then true
	*/
	@Test
    public void whenPrefixAndWordAreEqualThenTrue() {
        ArrayChar word = new ArrayChar("Hello");
        boolean result = word.startWith("Hello");
        assertThat(result, is(true));
    }
}