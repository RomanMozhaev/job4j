package ru.job4j.io;

import org.junit.Test;

import java.io.File;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class SearchTest {
    private final String dir = "/home/roman/Projects/job4j/temp";

    @Test
    public void whenThen() {
        Search search = new Search();
        List<String> exts = List.of("qwe", "rts");
        String parent = dir + "/ScannerTest";
//        String parent = System.getProperty("java.io.tmpdir") + "/ScannerTest";
        List<File> resultList = search.exists(parent, exts);
        StringBuilder result = new StringBuilder();
        for (File file : resultList) {
            result.append(file.getName());
            result.append(System.lineSeparator());
        }
        assertThat(result.toString(), is(new StringBuilder()
                                        .append("Untitled Document 2.rts")
                                        .append(System.lineSeparator())
                                        .append("Untitled Document 2.qwe")
                                        .append(System.lineSeparator())
                                        .append("Untitled Document 3.qwe")
                                        .append(System.lineSeparator())
                                        .toString()
                                        ));
    }

}