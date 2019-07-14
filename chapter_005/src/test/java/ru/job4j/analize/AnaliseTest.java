package ru.job4j.analize;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class AnaliseTest {

    @Test
    public void whenAnaliseThenSomeChanges() {

        List<User> previous = Arrays.asList(
                new User(113, "First"),
                new User(114, "Second"),
                new User(115, "third"),
                new User(116, "forth"),
                new User(117, "fifth")
                );
        List<User> current = Arrays.asList(
                new User(113, "First Modified"),
                new User(115, "third"),
                new User(116, "forth Modified"),
                new User(117, "fifth"),
                new User(118, "sixth")
                );
        Analise analise = new Analise();
        Info info = analise.diff(previous, current);
        Info expect = new Info(1, 2, 1);
        assertThat(info, is(expect));

    }
    @Test
    public void whenAnaliseThenAllDeleted() {

        List<User> previous = Arrays.asList(
                new User(113, "First"),
                new User(114, "Second"),
                new User(115, "third"),
                new User(116, "forth"),
                new User(117, "fifth")
        );
        List<User> current = new ArrayList<>();
        Analise analise = new Analise();
        Info info = analise.diff(previous, current);
        Info expect = new Info(0, 0, 5);
        assertThat(info, is(expect));
    }
    @Test
    public void whenAnaliseThenAllAdded() {

        List<User> current = Arrays.asList(
                new User(113, "First"),
                new User(114, "Second"),
                new User(115, "third"),
                new User(116, "forth"),
                new User(117, "fifth")
        );
        List<User> previous = new ArrayList<>();
        Analise analise = new Analise();
        Info info = analise.diff(previous, current);
        Info expect = new Info(5, 0, 0);
        assertThat(info, is(expect));
    }
    @Test
    public void whenAnaliseThenDuplicates() {

        List<User> previous = Arrays.asList(
                new User(113, "First"),
                new User(114, "Second"),
                new User(115, "third"),
                new User(116, "forth"),
                new User(117, "fifth")
        );
        List<User> current = Arrays.asList(
                new User(113, "First Modified"),
                new User(115, "third"),
                new User(116, "forth"),
                new User(116, "forth Modified"),
                new User(117, "fifth"),
                new User(118, "sixth")
        );
        Analise analise = new Analise();
        Info info = analise.diff(previous, current);
        Info expect = new Info(2, 1, 1);
        assertThat(info, is(expect));

    }
}