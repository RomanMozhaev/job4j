package ru.job4j.deadlock;

import java.util.concurrent.CountDownLatch;

public class WaiterTwo implements Runnable {

    final Thread t;
    private final CountDownLatch block1;
    private final CountDownLatch block2;

    public WaiterTwo(CountDownLatch block1, CountDownLatch block2) {
        this.block1 = block1;
        this.block2 = block2;
        this.t = new Thread(this, "WaiterTwo");
    }

    @Override
    public void run() {
        System.out.println("WaiterTwo did something");
        System.out.println("WaiterTwo before waiting");
        try {
            this.block2.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("WaiterTwo does some work after WaiterOne");
        this.block1.countDown();
    }
}
