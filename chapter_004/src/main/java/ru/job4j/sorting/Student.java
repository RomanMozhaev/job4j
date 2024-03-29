package ru.job4j.sorting;

import java.util.Comparator;
import java.util.Objects;

public class Student implements Comparator<Student> {
    private int score;
    private String name;

    public Student(int score, String name) {
        this.score = score;
        this.name = name;
    }

    public int getScore() {
        return score;
    }

    @Override
    public String toString() {
        return "Student{"
                + "score=" + score
                + ", name='" + name
                + '\''
                + '}';
    }

    @Override
    public int compare(Student o1, Student o2) {
        return Integer.compare(o1.getScore(), o2.getScore());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Student student = (Student) o;
        return score == student.score
                && Objects.equals(name, student.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(score, name);
    }
}
