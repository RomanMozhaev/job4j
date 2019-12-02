package ru.job4j.threadissues;

public class ThreadThree implements Runnable {

    protected Thread t;
    private Increment inc;

    public ThreadThree(Increment inc) {
        this.t = new Thread(this, "Thread Three");
        this.t.start();
        this.inc = inc;
    }

    @Override
    public void run() {
        int i = 0;
        while (i < 100) {
            i++;
            System.out.println(this.inc.plus());
            try {
                Thread.sleep(50);
            } catch (InterruptedException ie) {
                ie.printStackTrace();
            }
        }
    }
}
