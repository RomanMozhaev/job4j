package ru.job4j.deadlock;

public class ThreadOne implements Runnable {

    Thread t;

    public ThreadOne() {
        this.t = new Thread(this, "One");
    }

    @Override
    public void run() {
        System.out.println("Before block");
        while (!Thread.currentThread().isInterrupted()) {
            synchronized (this) {
                try {
                    this.wait();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    e.printStackTrace();
                }
            }
        }
        System.out.println("After block");
    }

}
