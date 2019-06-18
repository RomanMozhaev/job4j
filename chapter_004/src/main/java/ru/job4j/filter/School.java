package ru.job4j.filter;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class School {

    public List<Student> collect(List<Student> students, Predicate<Student> predict) {
        List<Student> list = students.stream().filter(
                student -> predict.test(student)
        ).collect(Collectors.toList());
        return list;
    }
}
