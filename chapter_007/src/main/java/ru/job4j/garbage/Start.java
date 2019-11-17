package ru.job4j.garbage;

public class Start {

    public static void main(String[] args) {
        Start start = new Start();
        start.start();
    }

    public void start() {
        info();
        for (int j = 0; j < 100; j++) {

            for (int i = 0; i < 100; i++) {
                new User("i");
                System.out.print("Number "+ j + " " + i + " : ");
                info();
            }
        }
    }

    public void info() {
        Runtime run = Runtime.getRuntime();
        System.out.println("Free   : " + run.freeMemory());
    }

}
