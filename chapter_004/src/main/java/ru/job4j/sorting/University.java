package ru.job4j.sorting;

import java.util.Comparator;
import java.util.List;;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Class for working with students' profiles
 */
public class University {
    /**
     * the method returns students with score more than bound
     * @param array - array with Students' profiles and null links
     * @param bound - minimum score
     * @return - list of students
     */
    public List<Student> arrayLevelOf(Object[] array, int bound) {
        List list = Stream.of(array)
                    .flatMap(Stream::ofNullable)
                    .collect(Collectors.toList());
        List<Student> returnedList = levelOf(list, bound);

        return returnedList;
    }
    /**
     * the method returns students with score more than bound
     * @param students - Students' profiles
     * @param bound - minimum score
     * @return - list of students
     */
    private List<Student> levelOf(List<Student> students, int bound) {
        return students.stream()
                .sorted(Comparator.comparing(Student::getScore))
                .dropWhile(s -> s.getScore() < bound)
                .collect(Collectors.toList());
    }
}
