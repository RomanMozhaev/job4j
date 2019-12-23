package ru.job4j.deadlock;

import java.util.concurrent.CountDownLatch;

public class DeadLockStarter {

    public static void main(String[] args) {
        CountDownLatch block1 = new CountDownLatch(1);
        CountDownLatch block2 = new CountDownLatch(1);
        WaiterOne one = new WaiterOne(block1, block2);
        WaiterTwo two = new WaiterTwo(block1, block2);
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
