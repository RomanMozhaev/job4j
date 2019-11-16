package ru.job4j.connections;

import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class CSVReaderTest {
    /**
     * the test for CSVReader class.
     * @throws IOException
     */
    @Test
    public void whenThen() throws IOException {
        String fileName = "testFiles/test.csv";
        String splitter = ";";
        CSVReader reader = new CSVReader(splitter);
        List<List<SimpleElement>> list = reader.readCSV(fileName);
        List<List<SimpleElement>> result = new ArrayList<>();
        SimpleElement el1 = new SimpleElement("\"1\"", 0);
        SimpleElement el2 = new SimpleElement("\"2\"", 0);
        SimpleElement el3 = new SimpleElement("\"3\"", 0);
        SimpleElement el4 = new SimpleElement("\"4\"", 1);
        SimpleElement el5 = new SimpleElement("\"5\"", 1);
        SimpleElement el6 = new SimpleElement("\"6\"", 1);
        SimpleElement el7 = new SimpleElement("\"7\"", 2);
        SimpleElement el8 = new SimpleElement("\"8\"", 2);
        SimpleElement el9 = new SimpleElement("\"9\"", 2);
        List<SimpleElement> line1 = new ArrayList<>();
        line1.add(el1);
        line1.add(el2);
        line1.add(el3);
        List<SimpleElement> line2 = new ArrayList<>();
        line2.add(el4);
        line2.add(el5);
        line2.add(el6);
        List<SimpleElement> line3 = new ArrayList<>();
        line3.add(el7);
        line3.add(el8);
        line3.add(el9);
        result.add(line1);
        result.add(line2);
        result.add(line3);
        assertThat(list, is(result));
    }
}