package ru.job4j.pool;

import ru.job4j.producerconsumer.SimpleBlockingQueue;

import java.util.LinkedList;
import java.util.List;

/**
 * the simple thread pool.
 */
public class ThreadPool {
    /**
     * the thread list.
     */
    private final List<Thread> threads;
    /**
     * the task list.
     */
    private final SimpleBlockingQueue<Runnable> tasks;

    /**
     * the main constructor.
     *
     * @param queue - the blocking queue;
     */
    public ThreadPool(final SimpleBlockingQueue<Runnable> queue) {
        this.tasks = queue;
        this.threads = new LinkedList<>();
    }

    /**
     * adds executing thread to the list.
     *
     * @param thread - the Thread.
     */
    public void addThread(Thread thread) {
        this.threads.add(thread);
    }

    /**
     * adds task to the list and wakes all waiting threads if the task were added.
     *
     * @param job - the task.
     * @return true if the task were added; false otherwise.
     */
    public boolean work(Runnable job) {
        return this.tasks.offer(job);
    }

    /**
     * calls interrupt methods for all thread.
     */
    public void shutdown() {
        for (Thread thread : this.threads) {
            thread.interrupt();
        }
    }

}