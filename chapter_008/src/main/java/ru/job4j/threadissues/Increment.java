package ru.job4j.threadissues;

public class Increment {

    private int i;

    public synchronized int plus() {
        return i++;
    }

}
