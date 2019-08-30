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
    private List<String> answers;
    private int listAnswersSize = 0;

    public ConsoleChat(String filePath) {
        this.filePath = filePath;
    }

    private void run() {
        String say;
        boolean answering = true;
        this.answers = getAnswersList(new File(this.filePath));
        if (this.answers != null) {
            this.listAnswersSize = this.answers.size();
        }
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
                    System.out.println(getAnswer(say));
                }
            } while (!ConsoleChat.EXIT.equals(say.toUpperCase()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private List<String> getAnswersList(File file) {
        List<String> list = null;
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            list = br.lines().collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }

    private String getAnswer(String say) {
        String result = "";
        if (this.answers != null) {
            Random random = new Random(say.hashCode());
            int string = random.nextInt(this.listAnswersSize);
            result = this.answers.get(string);
        }
        return result;
    }

    public static void main(String[] args) {
        String path = System.getProperty("java.io.tmpdir") + "/answers.txt";
        ConsoleChat chat = new ConsoleChat(path);
        chat.run();
    }
}
