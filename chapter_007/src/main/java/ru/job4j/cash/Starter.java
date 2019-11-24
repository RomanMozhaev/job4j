package ru.job4j.cash;

import java.util.List;

/**
 * the class for starting the program.
 */
public class Starter {

    private Input input;

    public Starter(Input input) {
        this.input = input;
    }

    /**
     * the main method
     * @param args - is not used.
     */
    public static void main(String[] args) {
        Input input = new ConsoleInput();
        Starter starter = new Starter(input);
        starter.start("chapter_007/cashTest/");
    }

    /**
     * the start method with cycle.
     * @param path - the path of the folder.
     */
    public void start(String path) {
        Cash cash = new Cash();
        FileScanner fs = new FileScanner(cash, path);
        boolean quit = false;
        do {
            List<String> list = fs.scanFiles();
            fs.printName(list);
            String answer = this.input.ask("Enter the file name from the list or exit: ");
            if (!answer.equals("exit")) {
                System.out.println(fs.getText(answer, list));
            } else {
                quit = true;
            }
        }
        while (!quit);
    }
}
