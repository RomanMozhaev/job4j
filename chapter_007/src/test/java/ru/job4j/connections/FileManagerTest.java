package ru.job4j.connections;

import org.junit.Test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class FileManagerTest {
    /**
     * the test for CSVReader.
     */
    @Test
    public void whenThen() {
        String fileName = "testFiles/test.csv";
        String splitter = ";";
        int limitNumber = 1;
        FileManager reader = new FileManager(splitter, limitNumber);
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

    /**
     * test for file writer.
     */
    @Test
    public void whenPrintThenPrinted() {
        String fileName = "testFiles/resultTest.csv";
        StringBuilder expect = new StringBuilder();
        expect.append("The quantity of the groups which have more than 1 line(s) is 2")
                .append("Group # 1")
                .append("\"1\";\"2\";\"3\"")
                .append("\"2\";\"67\";\"9\"")
                .append("\"67\"")
                .append("Group # 2")
                .append("\"4\";\"5\";\"6\"")
                .append("\"4\";\"10\";\"11\"")
                .append("Group # 3")
                .append("\"12\";\"13\";\"14\"");

        List<List<SimpleElement>> source = new ArrayList<>();
        SimpleElement el1 = new SimpleElement("\"1\"", 0);
        SimpleElement el2 = new SimpleElement("\"2\"", 0);
        SimpleElement el3 = new SimpleElement("\"3\"", 0);
        SimpleElement el4 = new SimpleElement("\"4\"", 1);
        SimpleElement el5 = new SimpleElement("\"5\"", 1);
        SimpleElement el6 = new SimpleElement("\"6\"", 1);
        SimpleElement el7 = new SimpleElement("\"2\"", 2);
        SimpleElement el8 = new SimpleElement("\"67\"", 2);
        SimpleElement el9 = new SimpleElement("\"9\"", 2);
        SimpleElement el10 = new SimpleElement("\"4\"", 3);
        SimpleElement el11 = new SimpleElement("\"10\"", 3);
        SimpleElement el12 = new SimpleElement("\"11\"", 3);
        SimpleElement el13 = new SimpleElement("\"12\"", 4);
        SimpleElement el14 = new SimpleElement("\"13\"", 4);
        SimpleElement el15 = new SimpleElement("\"14\"", 4);
        SimpleElement el16 = new SimpleElement("\"67\"", 5);
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
        List<SimpleElement> line4 = new ArrayList<>();
        line4.add(el10);
        line4.add(el11);
        line4.add(el12);
        List<SimpleElement> line5 = new ArrayList<>();
        line5.add(el13);
        line5.add(el14);
        line5.add(el15);
        List<SimpleElement> line6 = new ArrayList<>();
        line6.add(el16);
        source.add(line1);
        source.add(line2);
        source.add(line3);
        source.add(line4);
        source.add(line5);
        source.add(line6);
        Map<Integer, List<Map<Integer, Set<Integer>>>> sortedGroupsExpect = new TreeMap<>((t2, t1) -> (t1 - t2));
        Set<Integer> groupedSet1 = new HashSet<>();
        groupedSet1.add(0);
        groupedSet1.add(2);
        groupedSet1.add(5);
        Set<Integer> groupedSet2 = new HashSet<>();
        groupedSet2.add(1);
        groupedSet2.add(3);
        Map<Integer, Set<Integer>> newMap1 = new HashMap<>();
        newMap1.put(3, groupedSet1);
        Map<Integer, Set<Integer>> newMap2 = new HashMap<>();
        newMap2.put(2, groupedSet2);
        sortedGroupsExpect.put(3, List.of(newMap1));
        sortedGroupsExpect.put(2, List.of(newMap2));
        String splitter = ";";
        int limitNumber = 1;
        FileManager manager = new FileManager(splitter, limitNumber);
        manager.print(source, sortedGroupsExpect, 2, fileName);
        StringBuilder result = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            br.lines().forEach(result::append);
        } catch (IOException e) {
            e.fillInStackTrace();
        }
        assertThat(result.toString(), is(expect.toString()));
    }
}