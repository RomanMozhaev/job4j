package ru.job4j.search;

import java.util.ArrayList;
import java.util.List;

/**
 * PhoneDictionary can add a new Person or find Persons by a key.
 * @author RomanM
 * @version May 31, 2019
 */
public class PhoneDictionary {
    private List<Person> persons = new ArrayList<Person>();

    public void add(Person person) {
        this.persons.add(person);
    }

    /**
     * Вернуть список всех пользователей, который содержат key в любых полях.
     * @param key Ключ поиска.
     * @return Список подощедщих пользователей.
     */
    public List<Person> find(String key) {
        List<Person> result = new ArrayList<>();
        for (Person p : this.persons) {
            if (p.getAddress().contains(key) || p.getName().contains(key) || p.getPhone().contains(key) || p.getSurname().contains(key)) {
                result.add(p);
            }
        }
        return result;
    }
}