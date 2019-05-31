package ru.job4j.search;

import org.junit.Test;

import java.util.Iterator;
import java.util.List;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * tests for PhoneDictionary
 */
public class PhoneDictionaryTest {
    /**
     * test
     */
    @Test
    public void whenFindByName() {
        PhoneDictionary phones = new PhoneDictionary();
        phones.add(new Person("Petr", "Arsentev", "534872", "Bryansk"));
        phones.add(new Person("Roman", "Mozhaev", "435465", "Moscow"));
        phones.add(new Person("Ruslan", "Ruslanov", "1234355", "Moscow"));
        phones.add(new Person("Elena", "Elenova", "543765", "Samara"));
        List<Person> persons = phones.find("oscow");
        Iterator<Person> iter = persons.iterator();
        String result = iter.next().getName() + iter.next().getName();
        assertThat(result, is("RomanRuslan"));
    }
}