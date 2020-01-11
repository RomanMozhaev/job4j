package ru.job4j.deadlock;

public class DeadlockCycle {

    public static void main(String[] args) throws InterruptedException {
        ThreadOne one = new ThreadOne();
        ThreadOne two = new ThreadOne();
        one.t.start();
        two.t.start();
        one.t.join();
        two.t.join();
        System.out.println("main is finished");
    }
}
