package ru.job4j.cash;

import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class StarterTest {

    private final ByteArrayOutputStream out = new ByteArrayOutputStream();

    @Before
    public void loadOutput() {
        System.setOut(new PrintStream(this.out));
    }


    @Test
    public void whenThen() {
        String path = "cashTest/";
        String[] list = {"fifth.txt", "fifth.txt", "exit"};
        Input input = new StubInput(list);
        Starter starter = new Starter(input);
        starter.start(path);
        String expect = "fifth text file here";
        String result = this.out.toString();
        String[] array = result.split("\\n");

        assertThat(array[6], is(expect));
        assertThat(array[16], is(expect));
    }

}