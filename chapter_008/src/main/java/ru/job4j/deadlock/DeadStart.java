package ru.job4j.deadlock;

public class DeadStart {
    public static void main(String[] args) {
        Object first = new Object();
        Object second = new Object();
        DeadOne one = new DeadOne(first, second);
        DeadTwo two = new DeadTwo(first, second);
        one.t.start();
        two.t.start();
        try {
            one.t.join();
            two.t.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
