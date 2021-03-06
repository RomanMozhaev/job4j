package ru.job4j.deadlock;

import java.util.concurrent.CountDownLatch;

public class WaiterOne implements Runnable {

    final Thread t;
    private final CountDownLatch block1;
    private final CountDownLatch block2;


    public WaiterOne(CountDownLatch block1, CountDownLatch block2) {
        this.block1 = block1;
        this.block2 = block2;
        this.t = new Thread(this, "WriterOne");
    }

    @Override
    public void run() {
        System.out.println("WaiterOne did something");
        System.out.println("WaiterOne before waiting");
        try {
            this.block1.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("WaiterOne does work after WaiterTwo");
        this.block2.countDown();
    }
}
