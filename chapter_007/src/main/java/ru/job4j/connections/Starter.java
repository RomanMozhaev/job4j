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

    public void start(String fileName, String splitter, int limitNumber, String finalFile) {
        FileManager manager = new FileManager(splitter, limitNumber);
        List<List<SimpleElement>> list = manager.readCSV(fileName);
        Sorter sorter = new Sorter();
        Map<Integer, Set<Integer>> foundRepeats = sorter.findRepeats(list);
        List<Set<Integer>> groupedGroups = sorter.groupGroups(foundRepeats);
        Map<Integer, List<Map<Integer, Set<Integer>>>> sortedGroups = sorter.sortGroups(groupedGroups);
        int bigGroups = sorter.calcBigGroups(groupedGroups, limitNumber);
        manager.print(list, sortedGroups, bigGroups, finalFile);
    }
}
