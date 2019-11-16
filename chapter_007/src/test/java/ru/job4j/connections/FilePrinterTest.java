package ru.job4j.connections;

import org.junit.Before;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class FilePrinterTest {

    private Map<Integer, List<List<String>>> resultSortGroups;
    private String fileName;
    private String splitter = ";";
    private StringBuilder expect;
    private static final String LN = System.lineSeparator();
    private List<List<SimpleElement>> source;
    private Map<Integer, List<Map<Integer, Set<Integer>>>> sortedGroupsExpect;

    @Before
    public void init() {
        this.fileName = "testFiles/resultTest.csv";
        this.expect = new StringBuilder();
        this.expect.append("The quantity of the groups which have more than 1 line(s) is 2")
                .append("Group # 1")
                .append("\"1\";\"2\";\"3\"")
                .append("\"2\";\"67\";\"9\"")
                .append("\"67\"")
                .append("Group # 2")
                .append("\"4\";\"5\";\"6\"")
                .append("\"4\";\"10\";\"11\"")
                .append("Group # 3")
                .append("\"12\";\"13\";\"14\"");
        this.source = new ArrayList<>();
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
        this.source.add(line1);
        this.source.add(line2);
        this.source.add(line3);
        this.source.add(line4);
        this.source.add(line5);
        this.source.add(line6);
        this.sortedGroupsExpect = new TreeMap<>((t2, t1) -> (t1 - t2));
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
        this.sortedGroupsExpect.put(3, List.of(newMap1));
        this.sortedGroupsExpect.put(2, List.of(newMap2));

    }

    @Test
    public void whenPrintThenPrinted() throws IOException {
        FilePrinter printer = new FilePrinter(this.splitter, 1);
        printer.print(this.source, this.sortedGroupsExpect, 2, this.fileName);
        BufferedReader br = new BufferedReader(new FileReader(this.fileName));
        StringBuilder result = new StringBuilder();
        br.lines().forEach(result::append);
        assertThat(result.toString(), is(this.expect.toString()));
    }

}