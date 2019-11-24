package ru.job4j.cash;

import java.util.Scanner;

/**
 * the class for getting user's answer in console.
 */
public class ConsoleInput implements Input {

    private Scanner scanner = new Scanner(System.in);

    public String ask(String string) {
        System.out.print(string);
        return scanner.nextLine();
    }

}
