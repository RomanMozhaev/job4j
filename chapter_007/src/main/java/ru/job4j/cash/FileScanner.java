package ru.job4j.cash;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.*;
import java.lang.ref.SoftReference;
import java.util.ArrayList;
import java.util.List;

/**
 * the class for working with text files.
 */
public class FileScanner {

    /**
     * cashed text files are here.
     */
    private Cash cash;
    /**
     * the path to the folder with text files.
     */
    private String folderPath;
    /**
     * the line separator.
     */
    private static final String LN = System.lineSeparator();
    /**
     * the logger.
     */
    private static final Logger LOG = LogManager.getLogger(FileScanner.class.getName());

    /**
     * the main constructor.
     * @param cash - the cash instance.
     * @param folderPath - the folder path.
     */
    public FileScanner(Cash cash, String folderPath) {
        this.cash = cash;
        this.folderPath = folderPath;
    }

    /**
     * the method scans folder with text files.
     * @return - the list with files' names.
     */
    public List<String> scanFiles() {
        List<String> fileNames = new ArrayList<>();
        File dir = new File(this.folderPath);
        if (dir.isDirectory()) {
            File[] files = dir.listFiles();
            if (files != null) {
                for (File file : files) {
                    if (file.isFile()) {
                        fileNames.add(file.getName());
                    }
                }
            }
        }
        return fileNames;
    }

    /**
     * the method outputs files' names in console.
     * @param fileNames - the list with files' names.
     */
    public void printName(List<String> fileNames) {
        if (!fileNames.isEmpty()) {
            System.out.println("The files in the folder: ");
            for (String name : fileNames) {
                System.out.println(name);
            }
        } else {
            System.out.println("No file in the folder.");
        }
    }

    /**
     * the method which gets the text in the file with name.
     * If it is contained the files' list.
     * First it tries to get the text from the cash, and if the cash does not contain file's text,
     * the text is added to the cash and returns.
     * if name is not in the list, "no data" is printed.
     * If
     * @param name - file name
     * @param fileNames the list with files' names.
     * @return the string.
     */
    public String getText(String name, List<String> fileNames) {
        String result = "no data";
        if (fileNames.contains(name)) {
            String path = this.folderPath + name;
            SoftReference<String> cashData = cash.get(name);
            if (cashData != null) {
                result = cashData.get();
            } else {
                String text = readText(path);
                cash.add(name, text);
                result = text;
            }
        }
        return result;
    }

    /**
     * the method reads text from the file and returns it as String.
     * @param name - the file path
     * @return - the string.
     */
    private String readText(String name) {
        StringBuilder result = new StringBuilder();
        File file = new File(name);
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            br.lines().forEach(s -> {
                result.append(s);
                result.append(LN);
            });
        } catch (IOException e) {
            LOG.error(e.getMessage(), e);
        }
        return result.toString();
    }
}
