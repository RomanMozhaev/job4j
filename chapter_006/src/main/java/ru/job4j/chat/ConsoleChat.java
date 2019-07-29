package ru.job4j.chat;

import java.io.*;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class ConsoleChat {
    String exit = "ЗАКОНЧИТЬ";
    String continuing = "ПРОДОЛЖИТЬ";
    String stop = "СТОП";
    File file;

    public ConsoleChat(String path) {
        this.file = new File(path);
    }

    public void run() {
        String say;
        boolean answering = true;
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("Здравствуйте, я - консольный чат! Давайте поговорим!");
            System.out.println("Я замолчу, если скажите СТОП, и начну опять отвечать после ПРОДОЛЖИТЬ");
            System.out.println("Введите ЗАКОНЧИТЬ, чтобы закрыть программу");
            do {
                say = br.readLine();
                if (this.stop.equals(say.toUpperCase())) {
                    answering = false;
                }
                if (this.continuing.equals(say.toUpperCase())) {
                    answering = true;
                }
                if (answering) {
                    System.out.println(getAnswer(say));
                }
            } while (!this.exit.equals(say.toUpperCase()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String getAnswer(String say) {
        String result = "";
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            List<String> list = br.lines().collect(Collectors.toList());
            Random random = new Random(say.hashCode());
            int string = random.nextInt(list.size());
            result = list.get(string);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    public static void main(String[] args) {
        String path = System.getProperty("java.io.tmpdir") + "/answers.txt";
        ConsoleChat chat = new ConsoleChat(path);
        chat.run();
    }
}
