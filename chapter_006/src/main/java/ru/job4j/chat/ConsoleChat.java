package ru.job4j.chat;

import java.io.*;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class ConsoleChat {
    private static final String EXIT = "ЗАКОНЧИТЬ";
    private static final String CONTINUING = "ПРОДОЛЖИТЬ";
    private static final String STOP = "СТОП";
    private final String filePath;

    public ConsoleChat(String filePath) {
        this.filePath = filePath;
    }

    private void run() {
        String say;
        boolean answering = true;
        File file = new File(this.filePath);
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("Здравствуйте, я - консольный чат! Давайте поговорим!");
            System.out.println("Я замолчу, если скажите СТОП, и начну опять отвечать после ПРОДОЛЖИТЬ");
            System.out.println("Введите ЗАКОНЧИТЬ, чтобы закрыть программу");
            do {
                say = br.readLine();
                if (ConsoleChat.STOP.equals(say.toUpperCase())) {
                    answering = false;
                }
                if (ConsoleChat.CONTINUING.equals(say.toUpperCase())) {
                    answering = true;
                }
                if (answering) {
                    System.out.println(getAnswer(say, file));
                }
            } while (!ConsoleChat.EXIT.equals(say.toUpperCase()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String getAnswer(String say, File file) {
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
