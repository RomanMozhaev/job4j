package ru.job4j.tracker;

import java.util.Scanner;

public class ConsoleInput implements Input {

    Scanner scanner = new Scanner(System.in);

    public String ask(String string) {
        System.out.print(string);
        return scanner.nextLine();
    }
}
