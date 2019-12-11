package ru.job4j.pool;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;
import ru.job4j.producerconsumer.SimpleBlockingQueue;

import java.util.LinkedList;
import java.util.List;

/**
 * the simple thread pool.
 */
@ThreadSafe
public class ThreadPool {
    /**
     * the thread list.
     */
    @GuardedBy("this")
    private final List<Thread> threads;
    /**
     * the task list.
     */
    @GuardedBy("this")
    private final SimpleBlockingQueue<Runnable> tasks;
    /**
     * The quantity of the available CPU
     */
    @GuardedBy("this")
    private final int size;

    /**
     * the main constructor.
     * @param size - the size of the task list and quantity of the CPU.
     */
    public ThreadPool(int size) {
        this.size = size;
        this.tasks = new SimpleBlockingQueue<>(this.size);
        this.threads = new LinkedList<>();
    }

    /**
     * returns the task or null.
     * @return  Runnable object or null.
     */
    public Runnable getTask() {
        synchronized (this) {
            return this.tasks.poll();
        }

    }

    /**
     * adds executing thread to the list.
     * @param thread - the Thread.
     */
    public void addThread(Thread thread) {
        synchronized (this) {
            this.threads.add(thread);
        }
    }

    /**
     * adds task to the list and wakes all waiting threads if the task were added.
     * @param job - the task.
     * @return true if the task were added, false if not.
     */
    public boolean work(Runnable job) {
        synchronized (this) {
            boolean result = this.tasks.offer(job);
            if (result) {
                this.notifyAll();
            }
            return result;
        }
    }

    /**
     * calls interrupt methods for all thread.
     */
    public void shutdown() {
        synchronized (this) {
            for (Thread thread : this.threads) {
                thread.interrupt();
            }
            this.notifyAll();
        }
    }
}