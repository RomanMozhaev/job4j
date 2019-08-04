package ru.job4j.masksearch;

import com.google.common.primitives.Chars;
import ru.job4j.io.Search;

import java.io.*;
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
            if (treeSearch(maskCh, fileCh)) {
                result.add(file);
                System.out.println("true");
            }
        }
        return result;
    }

        /**
         * the method finds the next letter for dividing.
         * @param maskCh - the characters' list of the mask (part of the mask)
         * @return - The Letter instance with the number of the letter for dividing and the character or null
         */
    private Letter findNextLetter(List<Character> maskCh) {
        int maskSize = maskCh.size();
        Letter result = null;
        Character ch;
        Integer divPoint;
        for (int i = 0; i < maskSize; i++) {
            ch = maskCh.get(i);
            if (!ch.equals('*') && !ch.equals('?')) {
                divPoint = i;
                ch = maskCh.get(i);
                result = new Letter(divPoint, ch);
                break;
            }
        }
        return result;
    }

        /**
         * the method divides the mask and file name and add the parts to the stack. Then it takes one from the stack
         * The letter which is used for dividing does not includes to the parts.
         * If file name has more than one letter for dividing, the method adds to stack all available options.
         * Then it check branch by branch.
         * If one branch returns true than the method returns true.
         * @param maskCh - the characters' list of the mask
         * @param fileCh - the characters' list of the file name.
         * @return true if the file name fits to the mask.
         */
    private boolean treeSearch(List<Character> maskCh, List<Character> fileCh) {
        Stack<TreeElement> stack = new Stack<>();
        stack.push(new TreeElement(maskCh, fileCh, 0, true));
        boolean result = false;
        Letter nextLetter;
        Character ch;
        Integer divPoint;
        int i = 0;
        while (!stack.isEmpty()) {
            TreeElement element = stack.pop();
            List<Character> mask = element.getMaskCh();
            List<Character> file = element.getFileCh();
            nextLetter = findNextLetter(mask);
            if (nextLetter != null) {
                int maskSize = mask.size();
                int fileSize = file.size();
                ch = nextLetter.getCh();
                divPoint = nextLetter.getDivPoint();
                List<Integer> positions = findStartPositions(file, ch);
                if (positions.isEmpty()) {
                    removeBranch(stack);
                } else {
                    i++;
                    for (int pos : positions) {
                        stack.push(new TreeElement(mask.subList(0, divPoint), file.subList(0, pos), i, true));
                        stack.push(new TreeElement(mask.subList(divPoint + 1, maskSize), file.subList(pos + 1, fileSize), i, false));
                    }
                }
            } else {
                if (!check(mask, file)) {
                    removeBranch(stack);
                } else {
                    if ((element = stack.peek()) == null
                            || element.getDepth() == 1 && !element.isLeft() ) {
                        result = true;
                        break;
                    }
                }
            }
        }
        return result;
    }
        /**
         * the method removes elements from the stack while the stack is not empty and the next element is not right.
         * The method removes the branch of the tree in the case of one branch element returns false.
         * @param stack - the stack of the mask and the file name parts.
         */
    private void removeBranch(Stack<TreeElement> stack) {
        TreeElement element;
        while (!stack.isEmpty()) {
            element = stack.peek();
            if (!element.isLeft()) {
                break;
            } else {
                stack.pop();
            }
        }
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
        int astrix = 0;
        int fileSize = fileCh.size();
        for (Character ch : maskCh) {
            if (ch.equals('?')) {
                questions++;
            } else {
                astrix++;
            }
        }
        if (astrix > 0) {
            if (questions <= fileSize) {
                result = true;
            }
        } else {
            if (questions == fileSize) {
                result = true;
            }
        }
        return result;
    }

    /**
     * the method writes the Files List to the text file.
     * @param files - list of the matched files
     * @param resultFile - the name of the result file
     */
    private void writeFile(List<File> files, String resultFile) {
        String path = System.getProperty("java.io.tmpdir") + resultFile;
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
        try (FileReader reader = new FileReader(System.getProperty("java.io.tmpdir") + "log.txt")) {
            BufferedReader br = new BufferedReader(reader);
            br.lines().forEach(string -> System.out.println(string));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
