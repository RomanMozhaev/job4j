package ru.job4j.masksearch;

import com.google.common.primitives.Chars;
import ru.job4j.io.Search;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

/**
 * the class of searching files considering to the mask in the specified folder.
 */

public class MaskSearch {

    /**
     * the starting method of searching
     * @param args for searching
     */
    public void search(String[] args) {
        List<File> result;
        Args a = new Args(args);
        if (a.start()) {
            List<File> files = new Search().files(a.getDir());
            if (a.getCriteria().equals("-f")) {
                result = exactNameSearch(a.getMask(), files);
            } else {
                result = maskSearch(a.getMask(), files);
            }
            writeFile(result, a.getResultFile());
        }
    }

    /**
     * the method for the exact match searching
     * @param mask - the file name
     * @param files  - list of the files in the destined folder
     * @return - list of the matched files
     */
    private List<File> exactNameSearch(String mask, List<File> files) {
        List<File> result = new ArrayList<>();
        if (!files.isEmpty()) {
            int i = 0;
            int size = files.size();
            while (i < size) {
                File f = files.get(i++);
                String fileName = f.getName();
                if (fileName.equals(mask)) {
                    result.add(f);
                }
            }
        }
        return result;
    }

    /**
     * the method for the mask searching
     * @param mask - the mask for searching
     * @param files - the list of the files in the destined folder
     * @return - list of the matched files
     */
    private List<File> maskSearch(String mask, List<File> files) {
        List<File> result = new ArrayList<>();
        List<Character> maskCh = new ArrayList<>(Chars.asList(mask.toCharArray()));
        for (File file : files) {
            List<Character> fileCh = new ArrayList<>(Chars.asList(file.getName().toCharArray()));
            if (deviationSearch(maskCh, fileCh)) {
                result.add(file);
            }
        }
        return result;
    }

    /**
     * the method divides the mask and the file name finding the same letter in them
     * @param maskCh - Characters' array of the mask
     * @param fileCh - Characters' array of the file name
     * @return - true if the file name is matched to the mask
     */
    private boolean deviationSearch(List<Character> maskCh, List<Character> fileCh) {
        boolean result = false;
        int maskSize = maskCh.size();
        int divPoint = -1;
        Character ch = null;
        for (int i = 0; i < maskSize; i++) {
            ch = maskCh.get(i);
            if (!ch.equals('*') && !ch.equals('?')) {
                divPoint = i;
                break;
            }
        }
        if (divPoint != -1) {
            List<Integer> positions = findStartPositions(fileCh, ch);
            for (int pos : positions) {
                boolean left = deviationSearch(maskCh.subList(0, divPoint), fileCh.subList(0, pos));
                boolean right = deviationSearch(maskCh.subList(divPoint + 1, maskSize), fileCh.subList(pos + 1, fileCh.size()));
                if (left && right) {
                    result = true;
                    break;
                }
            }
        } else {
            result = check(maskCh, fileCh);
        }
        return result;
    }

    /**
     * the method search the startChar in the file name to find the indexes of the letter in the file name.
     * @param fileCh - Characters' array of the mask
     * @param startChar - the character from the mask
     * @return - list of the indexes
     */
    private List<Integer> findStartPositions(List<Character> fileCh, Character startChar) {
        List<Integer> startPositions = new ArrayList<>();
        int i = 0;
        for (Character ch : fileCh) {
            if (ch.equals(startChar)) {
                startPositions.add(i);
            }
            i++;
        }
        return startPositions;
    }

    /**
     * this method gets the part of the mask without letters. It compares the mask's part and the part of the file's name
     * @param maskCh - the array of the mask's part
     * @param fileCh - the array of the file's name
     * @return true of the part of the file's name is matched
     */
    private boolean check(List<Character> maskCh, List<Character> fileCh) {
        boolean result = false;
        int questions = 0;
        for (Character ch : maskCh) {
            if (ch.equals('?')) {
                questions++;
            }
        }
        if (questions <= fileCh.size()) {
            result = true;
        }
        return result;
    }

    /**
     * the method writes the Files List to the text file.
     * @param files - list of the matched files
     * @param resultFile - the name of the result file
     */
    private void writeFile(List<File> files, String resultFile) {
        String path = System.getProperty("java.io.tmpdir") + "/" + resultFile;
        final String LS = System.lineSeparator();
        try (FileWriter writer = new FileWriter(path)) {
            for (File file : files) {
                writer.write(file.getName());
                writer.write(" : Full path: " + file.getPath());
                writer.write(LS);
            }
            System.out.println("Результат поиска находится в файле:");
            System.out.println(path);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        MaskSearch maskSearch = new MaskSearch();
        maskSearch.search(args);

    }
}
