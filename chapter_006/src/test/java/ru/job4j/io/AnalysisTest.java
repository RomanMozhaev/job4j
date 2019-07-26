package ru.job4j.io;

import org.junit.Test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Optional;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class AnalysisTest {

    @Test
    public void whenSourceThenTarget() {
        Analysis analysis = new Analysis();
        String source = "/home/roman/Projects/job4j/chapter_006/src/main/java/ru/job4j/io/unavailable.csv";
        String target = "/home/roman/Projects/job4j/chapter_006/src/main/java/ru/job4j/io/time.txt";
        analysis.unavailable(source, target);
        Optional<String> string = Optional.empty();
        try (BufferedReader reader = new BufferedReader(new FileReader(target))) {
            string = reader.lines().reduce((string1, string2) -> string1 + string2);
        } catch (IOException e) {
            System.out.println("IO Exception");
        }
        assertThat(string.get(), is(new StringBuilder()
                .append("10:58:01;10:59:01;")
                .append("11:01:02;11:02:02;")
                .toString()));
    }
    @Test
    public void whenSource2ThenTarget2() {
        Analysis analysis = new Analysis();
        String source = "/home/roman/Projects/job4j/chapter_006/src/main/java/ru/job4j/io/unavailable2.csv";
        String target = "/home/roman/Projects/job4j/chapter_006/src/main/java/ru/job4j/io/time2.txt";
        analysis.unavailable(source, target);
        Optional<String> string = Optional.empty();
        try (BufferedReader reader = new BufferedReader(new FileReader(target))) {
            string = reader.lines().reduce((string1, string2) -> string1 + string2);
        } catch (IOException e) {
            System.out.println("IO Exception");
        }
        assertThat(string.get(), is(new StringBuilder()
                .append("10:58:31;11:02:02;")
                .append("12:02:02;")
                .toString()));
    }

}