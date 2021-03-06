package ru.job4j.pool;

import ru.job4j.producerconsumer.SimpleBlockingQueue;

/**
 * the class for work imitation.
 * it creates thread-tasks for execution and ThreadSamples for distributing the tasks.
 */
public class ThreadFactory {

    public static void main(String[] args) {
        int size = Runtime.getRuntime().availableProcessors();
        System.out.println("CPU quantity: " + size);
        SimpleBlockingQueue<Runnable> queue = new SimpleBlockingQueue<>(size);
        ThreadPool pool = new ThreadPool(queue);
        for (int i = 0; i < size; i++) {
            ThreadSample thread = new ThreadSample(queue, Integer.toString(i));
            pool.addThread(thread.t);
            thread.t.start();
        }
        for (int n = 0; n < 20; n++) {
            boolean added;
            do {
                added = pool.work(new Thread("00" + n));
            }
            while (!added);
        }
        pool.shutdown();
    }
}
