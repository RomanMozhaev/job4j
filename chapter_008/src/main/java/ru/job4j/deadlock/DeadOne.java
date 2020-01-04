package ru.job4j.deadlock;

public class DeadOne implements Runnable {

    final Thread t;
    private final Object s;
    private final Object f;

    public DeadOne(Object f, Object s) {
        this.t = new Thread(this, "DeadOne");
        this.s = s;
        this.f = f;
    }

    @Override
    public void run() {
        while (true) {
            synchronized (s) {
                System.out.println("second is blocked by DeadTwo");
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (f) {
                    System.out.println("first is blocked by DeadTwo");
                }
            }

        }
    }
}
