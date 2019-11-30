package ru.job4j.threadissues;

public class ThreadIssueThree {

    public static void main(String[] args) {
        new ThreadIssueThree().start();
    }

    private void start() {
        Increment inc = new Increment();
        new ThreadThree(inc);
        new ThreadThree(inc);
    }
}
