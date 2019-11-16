package ru.job4j.connections;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * the class for starting the program.
 */
public class Starter {

    public static void main(String[] args) throws IOException {
        String fileName = "testFiles/lng.csv";
        String splitter = ";";
        int limitNumber = 1;
        String finalFile = "testFiles/result.csv";
        Starter starter = new Starter();
        starter.start(fileName, splitter, limitNumber, finalFile);
    }

    private void start(String fileName, String splitter, int limitNumber, String finalFile) throws IOException {
        CSVReader reader = new CSVReader(splitter);
        List<List<SimpleElement>> list = reader.readCSV(fileName);
        Sorter sorter = new Sorter();
        Map<Integer, Set<Integer>> foundRepeats = sorter.findRepeats(list);
        List<Set<Integer>> groupedGroups = sorter.groupGroups(foundRepeats);
        Map<Integer, List<Map<Integer, Set<Integer>>>> sortedGroups = sorter.sortGroups(groupedGroups);
        int bigGroups = sorter.calcBigGroups(groupedGroups, limitNumber);
        FilePrinter printer = new FilePrinter(splitter, limitNumber);
        printer.print(list, sortedGroups, bigGroups, finalFile);
    }
}
