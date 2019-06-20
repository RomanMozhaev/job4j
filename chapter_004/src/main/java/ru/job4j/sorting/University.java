package ru.job4j.sorting;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Class for working with students' profiles
 */
public class University {
    /**
     * the method returns students with score more than bound
     * @param students - students' list
     * @param bound - minimum score
     * @return - list of students
     */
    public List<Student> levelOf(List<Student> students, int bound) {
        return students.stream()
                .sorted(Comparator.comparing(Student::getScore))
//                .flatMap(Stream::ofNullable)
                .dropWhile(s -> s.getScore() < bound)
                .collect(Collectors.toList());
    }
}
