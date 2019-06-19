package ru.job4j.filter;

import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * the class for working with students profiles
 */
public class School {
    /**
     * the method sorts profiles based on students' score
     * @param students - list of students
     * @param predict - selection criterion
     * @return - students' list.
     */
    public List<Student> collect(List<Student> students, Predicate<Student> predict) {
        return students.stream().filter(
                student -> predict.test(student)
        ).collect(Collectors.toList());
    }

    public Map<String, Student> studentMapCreate(List<Student> students) {
        return students.stream().distinct().collect(Collectors.toMap(
                student -> student.getSurname(),
                student -> student
        ));
    }

}
