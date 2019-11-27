package ru.job4j.connections;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.*;
import java.util.*;


/**
 * the class reads data from a file and after sorting writes sorted data to a file.
 */
public class FileManager {

    /**
     * the splitter splits rows into values.
     */
    private final String splitter;
    /**
     * the counted groups must have more elements then the limitNumber.
     */
    private int limitNumber;
    /**
     * the line separator.
     */
    private final static String LN = System.lineSeparator();
    /**
     * the logger constant.
     */
    private static final Logger LOG = LogManager.getLogger(FileManager.class.getName());

    /**
     * the main constructor.
     * @param splitter - splitter.
     */
    public FileManager(String splitter, int limitNumber) {
        this.splitter = splitter;
        this.limitNumber = limitNumber;
    }

    /**
     * the method reads data from the file and writes values row by row in to the list.
     * @param fileName - the file which contains data
     * @return - the list of the list of the simpleElements.
     */
    public List<List<SimpleElement>> readCSV(String fileName) {
        List<List<SimpleElement>> list = new LinkedList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line = br.readLine();
            int rowNumber = 0;
            while (line != null) {
                String[] numbers = line.split(this.splitter);
                List<SimpleElement> row = new ArrayList<>();
                for (String s : numbers) {
                    row.add(new SimpleElement(s, rowNumber));
                }
                list.add(row);
                line = br.readLine();
                rowNumber++;
            }
        } catch (IOException e) {
            LOG.error(e.getMessage(), e);
        }
        return list;
    }

    /**
     * the method writes reorganized data back to the file.
     *
     * @param list - the original list.
     * @param sortedGroups - the collection of the groups which rows numbers which has same values.
     * @param bigGroupCount - the quantity of the groups which have more elements than the limit number is.
     * @param fileName - the name of the file which is written in.
     */
    public void print(List<List<SimpleElement>> list, Map<Integer, List<Map<Integer, Set<Integer>>>> sortedGroups, int bigGroupCount, String fileName) {
        TreeSet<Integer> del = new TreeSet<>();
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(fileName))) {
            int groupId = 0;
            bw.write(String.format("The quantity of the groups which have more than %d line(s) is %d", this.limitNumber, bigGroupCount));
            bw.write(LN);
            for (Map.Entry<Integer, List<Map<Integer, Set<Integer>>>> entry : sortedGroups.entrySet()) {
                for (Map<Integer, Set<Integer>> groupMap : entry.getValue()) {
                    groupId++;
                    bw.write(String.format("Group # %d", groupId));
                    bw.write(LN);
                    for (Map.Entry<Integer, Set<Integer>> group : groupMap.entrySet()) {
                        del.addAll(group.getValue());
                        for (Integer rowNum : group.getValue()) {
                            writeRow(bw, list.get(rowNum));
                        }
                    }
                }
            }
            Iterator<Integer> it = del.iterator();
            int d = 0;
            if (it.hasNext()) {
                d = it.next();
            }
            int j = -1;
            for (List<SimpleElement> row : list) {
                j++;
                if (j != d) {
                    groupId++;
                    bw.write(String.format("Group # %d", groupId));
                    bw.write(LN);
                    writeRow(bw, row);
                } else {
                    if (it.hasNext()) {
                        d = it.next();
                    }
                }
            }
        } catch (IOException e) {
            LOG.error(e.getMessage(), e);
        }
    }

    private void writeRow(BufferedWriter bw, List<SimpleElement> row) throws IOException {
        String theSplitter = this.splitter;
        int j = 0;
        int rowSize = row.size();
        for (SimpleElement element : row) {
            j++;
            if (j == rowSize) {
                theSplitter = "";
            }
            bw.write(String.format("%s%s", element.getValue(), theSplitter));
        }
        bw.write(LN);
    }
}
