package ru.job4j.connections;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

/**
 * the class reads data from csv file and collects separated values to the list row by row.
 */
public class CSVReader {

    /**
     * the splitter splits rows into values.
     */
    private final String splitter;

    /**
     * the main constructor.
     * @param splitter - splitter.
     */
    public CSVReader(String splitter) {
        this.splitter = splitter;
    }

    /**
     * the method reads data from the file and writes values row by row in to the list.
     * @param fileName - the file which contains data
     * @return - the list of the list of the simpleElements.
     * @throws IOException
     */
    public List<List<SimpleElement>> readCSV(String fileName) throws IOException {
        List<List<SimpleElement>> list = new LinkedList<>();
        BufferedReader br = new BufferedReader(new FileReader(fileName));
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
        br.close();
        return list;
    }
}