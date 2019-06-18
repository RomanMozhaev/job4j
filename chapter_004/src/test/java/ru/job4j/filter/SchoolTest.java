package ru.job4j.filter;

import org.junit.Test;

import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class SchoolTest {
    /**
     * List of students
     */
    private List<Student> students = List.of(
                new Student(70),
                new Student(65),
                new Student(95),
                new Student(10),
                new Student(20),
                new Student(90),
                new Student(40),
                new Student(60)
            );
    private List<Student> class10A = List.of(
                new Student(70),
                new Student(95),
                new Student(90)
            );
    private List<Student> class10B = List.of(
                new Student(65),
                new Student(60)
            );
    private List<Student> class10C = List.of(
                new Student(10),
                new Student(20),
                new Student(40)
            );
    /**
     * Test for 10A
     */
    @Test
    public void when70MoreThen10A() {
        School school = new School();
        List<Student> result = school.collect(this.students, student -> (student.getScore() >= 70 && student.getScore() <= 100));
        assertThat(result.toString(), is(class10A.toString()));
    }
    /**
     * Test for 10B
     */
    @Test
    public void when50to70Then10B() {
        School school = new School();
        List<Student> result = school.collect(this.students, student -> (student.getScore() < 70 && student.getScore() >= 50));
        assertThat(result.toString(), is(class10B.toString()));
    }
    /**
     * Test for 10C
     */
    @Test
    public void when50lessThen10C() {
        School school = new School();
        List<Student> result = school.collect(this.students, student -> (student.getScore() < 50));
        assertThat(result.toString(), is(class10C.toString()));
    }
}