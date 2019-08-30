package ru.job4j.masksearch;

import com.google.common.base.Joiner;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * Class for arguments checking
 */
public class Args {

    private String dir;
    private String mask;
    private String criteria;
    private String resultFile;
    private List<String> args;
    private boolean exitMark = false;
    private final static String LS = System.lineSeparator();
    private final List<String> masks = Arrays.asList("-m", "-f");
    private final List<String> forbidden = Arrays.asList("/", "\\", "?", "<", ">", "*", "|");

    public Args(String[] args) {
        this.args = new ArrayList<>(Arrays.asList(args));
    }

    public String getCriteria() {
        return criteria;
    }

    public String getMask() {
        return mask;
    }

    public String getDir() {
        return dir;
    }

    public String getResultFile() {
        return resultFile;
    }

    /**
     * starting method.
     * @return true if args are okay
     */
    public boolean start() {
        boolean result = false;
        while (!this.exitMark) {
            if (run()) {
                result = true;
                break;
            } else {
                this.args = new ArrayList<>();
            }
        }
        return result;
    }

    /**
     * the method checks whether a user typed EXIT or not and summary of the arguments' checks
     * @return true if all checks passed
     */
    private boolean run() {
        boolean result = false;
        while (this.args.isEmpty()) {
            receiveNewArgs();
        }
        this.exitMark = this.args.get(0).toUpperCase().equals("EXIT");
        if (!exitMark) {
            if (readArgs() && checkArgs()) {
                result = true;
            }
        }
        return result;
    }

    /**
     * the method checks results of the checking methods checkDir and checkResultFileName
     * @return true if both methods return true
     */
    private boolean checkArgs() {
        boolean result = false;
        if (!checkDir() || !checkResultFileName()) {
            receiveNewArgs();
            readArgs();
        } else {
            result = true;
        }
        return result;
    }

    /**
     * check the name of the result file,which will contain founded files' name.
     * @return true if the name doesn't contain rejected symbols.
     */
    private boolean checkResultFileName() {
        boolean result = true;
        for (String s : this.forbidden) {
            if (this.resultFile.contains(s)) {
                System.out.println("Имя файла содержит недопустимые символы");
                result = false;
                break;
            }
        }
        return result;
    }

    /**
     * the method checks the directory where the user wants to search
     * @return true if the directory exists.
     */
    private boolean checkDir() {
        boolean result = false;
        try {
            File directory = new File(this.dir);
            if (directory.isDirectory()) {
                result = true;
            } else {
                System.out.println("Указанный путь не указывает на папку");
            }
        } catch (NullPointerException e) {
            System.out.println("Имя папки не введено");
        }

        return result;
    }

    /**
     * The method reads args from the list and check their positions.
     * @return true if all arguments were typed in correct order.
     */
    private boolean readArgs() {
        boolean accept = true;
        if (this.args.size() != 7
                || !this.args.get(0).equals("-d")
                || !this.args.get(2).equals("-n")
                || !this.masks.contains(this.args.get(4))
                || !this.args.get(5).equals("-o")
        ) {
            accept = false;
        } else {
            this.dir = this.args.get(1);
            this.mask = this.args.get(3);
            this.criteria = this.args.get(4);
            this.resultFile = this.args.get(6);
        }
        return accept;
    }

    /**
     * the method reads the line from the console and divides it by the space symbol.
     */
    private void receiveNewArgs() {
        System.out.println(Joiner.on(LS).join(
                "Пожалуйста, введите параметры поиска в соответсвии со следующими павилами: ",
                "-d c:/ -n *.txt -m -o log.txt",
                "-d - директория в которая начинать поиск.",
                "-n - имя файла или маска.",
                "-m - искать по маске, либо -f - полное совпадение имени.",
                "используйте * - для обозначения неопределенного количества букв",
                "используйте ? - для обозначения одной пропущенной буквы",
                "-o - результат записать в файл с указанным именем.",
                "Для выхода введите EXIT"
        ));
        Scanner scanner = new Scanner(System.in);
        String string = scanner.nextLine();
        String a = "";
        for (char ch : string.toCharArray()) {
            if (ch == ' ') {
                if (!a.isEmpty()) {
                    this.args.add(a);
                    a = "";
                }
            } else {
                a = a + ch;
            }
        }
        if (!a.equals("")) {
            this.args.add(a);
        }
    }
}
