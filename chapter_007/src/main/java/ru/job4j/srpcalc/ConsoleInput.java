package ru.job4j.srpcalc;

import java.util.Scanner;

/**
 * the class for reading the users' answers
 */
public class ConsoleInput implements Input {

    Scanner scanner = new Scanner(System.in);

    /**
     * the method prints the question in console and return the user's enter
     * @param string - the question
     * @return - the answer
     */
    public String ask(String string) {
        System.out.println(string);
        return this.scanner.nextLine();
    }
}
