package ru.job4j.pool;

import ru.job4j.producerconsumer.SimpleBlockingQueue;

/**
 * the class of the Thread sample which is pool consumer.
 */
public class ThreadSample implements Runnable {
    /**
     * the thread.
     */
    protected final Thread t;
    /**
     * the thread pool, the mutex.
     */
    private final SimpleBlockingQueue<Runnable> queue;

    /**
     * the main constructor.
     *
     * @param queue - the blocking queue.
     * @param name  - the name/ number of the thread.
     */
    public ThreadSample(final SimpleBlockingQueue<Runnable> queue, final String name) {
        this.t = new Thread(this, name);
        this.queue = queue;
    }

    /**
     * the run method. It finishes its work when interrupt was called.
     * it waits while queue is blocked for reading.
     */
    @Override
    public void run() {
        try {
            while (!(this.queue.isReadBlockFactor() && this.t.isInterrupted())) {
                while (this.queue.isReadBlockFactor()) {
                    synchronized (this.queue) {
                        this.queue.wait();
                    }
                }
                doSomeWork();
            }
        } catch (InterruptedException e) {
            this.t.interrupt();
            e.printStackTrace();
        }
    }

    /**
     * the method for imitation of the incoming thread work.
     */
    private void doSomeWork() {
        Runnable work = this.queue.poll();
        if (work != null) {
            System.out.println(Thread.currentThread().getName() +work.toString());
        }
    }
}
