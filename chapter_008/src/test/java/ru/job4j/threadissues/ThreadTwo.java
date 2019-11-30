package ru.job4j.threadissues;

public class ThreadTwo implements Runnable {

    protected Thread t;
    private static volatile int j = 0;
    private String name;

    public ThreadTwo(String name) {
        this.name = name;
        this.t = new Thread(this, "Thread One");
        this.t.start();
    }

    @Override
    public void run() {
        for (int k = 0; k < 100; k++) {
            commonMethod();
        }
    }

     private synchronized void commonMethod() {
        System.out.println(name + " before " + j);
        j++;
        int q = 0;
        while (q < 10000) {
            q++;
        }
        j--;
        System.out.println(name + " after " + j);
    }

}
