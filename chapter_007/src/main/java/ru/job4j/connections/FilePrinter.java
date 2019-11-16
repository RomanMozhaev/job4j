package ru.job4j.connections;

import java.io.*;
import java.util.*;

/**
 * the class writes data back to the file.
 */
public class FilePrinter {
    /**
     * the splitter of the values in the row.
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
     * the main constructor.
     * @param splitter
     * @param limitNumber
     */
    public FilePrinter(String splitter, int limitNumber) {
        this.splitter = splitter;
        this.limitNumber = limitNumber;
    }

    /**
     * the method writes reorganized data back to the file.
     *
     * @param list - the original list.
     * @param sortedGroups - the collection of the groups which rows numbers which has same values.
     * @param bigGroupCount - the quantity of the groups which have more elements than the limit number is.
     * @param fileName - the name of the file which is written in.
     * @throws IOException
     */
    public void print(List<List<SimpleElement>> list, Map<Integer, List<Map<Integer, Set<Integer>>>> sortedGroups, int bigGroupCount, String fileName) throws IOException {
        Set<Integer> del = new TreeSet<>((t2, t1) -> (t1 - t2));
        BufferedWriter bw = new BufferedWriter(new FileWriter(fileName));
        int i = 0;
        bw.write(String.format("The quantity of the groups which have more than %d line(s) is %d", this.limitNumber, bigGroupCount));
        bw.write(LN);
        for (Map.Entry<Integer, List<Map<Integer, Set<Integer>>>> entry : sortedGroups.entrySet()) {
            for (Map<Integer, Set<Integer>> groupMap : entry.getValue()) {
                i++;
                bw.write(String.format("Group # %d", i));
                bw.write(LN);
                for (Map.Entry<Integer, Set<Integer>> group : groupMap.entrySet()) {
                    del.addAll(group.getValue());
                    for (Integer rowNum : group.getValue()) {
                        writeRow(bw, list.get(rowNum));
                    }
                }
            }
        }
        for (int rowToDel : del) {
            list.remove(rowToDel);
        }
        for (List<SimpleElement> row : list) {
            i++;
            bw.write(String.format("Group # %d", i));
            bw.write(LN);
            writeRow(bw, row);
        }
        bw.close();
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
