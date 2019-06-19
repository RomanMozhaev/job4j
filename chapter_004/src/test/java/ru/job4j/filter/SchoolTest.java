package ru.job4j.filter;

import org.junit.Test;

import java.util.List;
import java.util.Map;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class SchoolTest {
    /**
     * List of students
     */
    private List<Student> students = List.of(
                new Student(70, "Petrov"),
                new Student(65, "Ivanov"),
                new Student(95, "Petrov"),
                new Student(10, "Sidorov"),
                new Student(20, "Kotov"),
                new Student(90, "Nikitin"),
                new Student(40, "Laptev"),
                new Student(60, "Sokolov")
            );
    private List<Student> class10A = List.of(
                new Student(70, "Petrov"),
                new Student(95, "Petrov"),
                new Student(90, "Nikitin")
            );
    private List<Student> class10B = List.of(
                new Student(65, "Ivanov"),
                new Student(60, "Sokolov")
            );
    private List<Student> class10C = List.of(
                new Student(10, "Sidorov"),
                new Student(20, "Kotov"),
                new Student(40, "Laptev")
            );
    private Map<String, Student> class10cMap = Map.of(
            "Sidorov", new Student(10, "Sidorov"),
            "Kotov", new Student(20, "Kotov"),
            "Laptev", new Student(40, "Laptev")
            );
    /**
     * Test for 10A
     */
    @Test
    public void when70MoreThen10A() {
        School school = new School();
        List<Student> result = school.collect(this.students, student -> (student.getScore() >= 70 && student.getScore() <= 100));
        assertThat(result, is(this.class10A));
    }
    /**
     * Test for 10B
     */
    @Test
    public void when50to70Then10B() {
        School school = new School();
        List<Student> result = school.collect(this.students, student -> (student.getScore() < 70 && student.getScore() >= 50));
        assertThat(result, is(this.class10B));
    }
    /**
     * Test for 10C
     */
    @Test
    public void when50lessThen10C() {
        School school = new School();
        List<Student> result = school.collect(this.students, student -> (student.getScore() < 50));
        assertThat(result, is(this.class10C));
    }
    @Test
    public void whenMapCreated() {
        School school = new School();
        Map<String, Student> result = school.studentMapCreate(this.class10C);
        assertThat(result, is(class10cMap));
    }
}