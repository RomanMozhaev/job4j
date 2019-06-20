package ru.job4j.sorting;

import org.junit.Test;

import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class UniversityTest {

    public Object[] students = {
            new Student(70, "Petrov"),
            new Student(65, "Ivanov"),
            null,
            new Student(95, "Petrov"),
            new Student(20, "Kotov"),
            new Student(90, "Nikitin"),
            new Student(40, "Laptev"),
            new Student(60, "Sokolov")
            };
    @Test
    public void whenNeed70PlusThenList() {
        University uni = new University();
        List<Student> result = uni.arrayLevelOf(students, 70);
        List<Student> expect = List.of(
                new Student(70, "Petrov"),
                new Student(90, "Nikitin"),
                new Student(95, "Petrov")
        );
        assertThat(result, is(expect));
    }

}