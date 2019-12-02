package ru.job4j.threadissues;

public class ThreadOne implements Runnable {

    protected Thread t;
    private static int i;

    public ThreadOne() {
        this.t = new Thread(this, "Thread One");
        this.t.start();
    }

    @Override
    public void run() {
        int j =0;
        while (j < 100) {
            j++;
            System.out.println(i++);
            try {
                Thread.sleep(500);
            } catch (InterruptedException ie) {
                ie.printStackTrace();
            }
        }
    }
}
