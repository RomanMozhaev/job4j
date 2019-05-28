package ru.job4j.tracker;

import java.util.List;
import java.util.Scanner;

public class ConsoleInput implements Input {
// Композиция. Создаем объект сканер и используем его методы.
    Scanner scanner = new Scanner(System.in);
//композиция объект стринг создается.
    public String ask(String string) {
        System.out.print(string);
        return scanner.nextLine();
    }
    public int ask(String question, List<Integer> range) {
//        использование статического класса обертки тоже композиция?
        int key = Integer.valueOf(this.ask(question));
        boolean exist = false;
        for (int value : range) {
            if (value == key) {
                exist = true;
                break;
            }
        }
        if (!exist) {
//  Композиция. Создаем объект класса исключения
            throw new MenuOutException("Out of menu range");
        }
        return key;
    }
}
