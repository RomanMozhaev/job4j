package ru.job4j.cash;

import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class StarterTest {

    private static final String LN = System.lineSeparator();
    private final ByteArrayOutputStream out = new ByteArrayOutputStream();
//    private final Consumer<String> output = new Consumer<>() {
//        private final PrintStream stdout = new PrintStream(out);
//        @Override
//        public void accept(String s) {
//            stdout.printf(s);
//        }
//    };

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
        StringBuilder fileList = new StringBuilder();
        fileList.append("The files in the folder: ")
                .append(LN)
                .append("fifth.txt")
                .append(LN)
                .append("first.txt")
                .append(LN)
                .append("fourth.txt")
                .append(LN)
                .append("second.txt")
                .append(LN)
                .append("third.txt")
                .append(LN);
        StringBuilder text = new StringBuilder();
        text.append("fifth text file here")
                .append(LN)
                .append("fifth text file here")
                .append(LN)
                .append("fifth text file here")
                .append(LN)
                .append(LN);
        String fileString = fileList.toString();
        String textString = text.toString();
        String expect = fileString + textString + fileString + textString + fileString;
        assertThat(this.out.toString(), is(expect));
    }

}