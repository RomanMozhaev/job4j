package ru.job4j.producerconsumer;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.LinkedList;
import java.util.Queue;

/**
 * the thread safe queue.
 *
 * @param <T>
 */
@ThreadSafe
public class SimpleBlockingQueue<T> {
    /**
     * the queue.
     */
    @GuardedBy("this")
    private final Queue<T> queue;
    /**
     * the maximal size of the queue.
     */
    @GuardedBy("this")
    private final int fixSize;
    /**
     * the factor is true if the queue is full.
     */
    @GuardedBy("this")
    private boolean writeBlockFactor = false;
    /**
     * the factor is true if the queue is empty.
     */
    @GuardedBy("this")
    private boolean readBlockFactor = true;

    /**
     * the main constructor.
     *
     * @param fixLength - the max queue size.
     */
    public SimpleBlockingQueue(final int fixLength) {
        this.fixSize = fixLength;
        this.queue = new LinkedList<>();
    }

    /**
     * getter for reading factor.
     *
     * @return true if reading should be blocked.
     */
    public synchronized boolean isReadBlockFactor() {
        return readBlockFactor;
    }

    /**
     * adds value to the queue if queue size is less than fixSize.
     *
     * @param value the value for adding.
     * @return true if value was added, false if not.
     */
    public synchronized boolean offer(T value) {
        boolean result = false;
        if (this.queue.size() < fixSize) {
            this.queue.offer(value);
            result = true;
        } else {
            this.writeBlockFactor = true;
        }
        this.readBlockFactor = false;
        notify();
        return result;
    }

    /**
     * retrieves and removes the head of the queue
     *
     * @return the value of the head or null if the queue is empty.
     */
    public synchronized T poll() {
        this.writeBlockFactor = false;
        T result = this.queue.poll();
        if (result == null) {
            this.readBlockFactor = true;
        }
        notify();
        return result;
    }

    /**
     * makes the writer waited.
     */
    public synchronized void writerWaiting() {
        while (this.writeBlockFactor) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * makes the reader waited.
     * @throws InterruptedException
     */
    public synchronized void readerWaiting() throws InterruptedException {
        while (this.readBlockFactor) {
            this.wait();
        }
    }

    /**
     * wakes all threads.
     */
    public synchronized void notifyAllThreads() {
        this.notifyAll();
    }
}
