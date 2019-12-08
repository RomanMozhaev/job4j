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
    private Queue<T> queue;
    /**
     * the maximal size of the queue.
     */
    private final int fixSize;
    /**
     * the factor is true if the queue is full.
     */
    private boolean writeBlockFactor = false;
    /**
     * the factor is true if the queue is empty.
     */
    private boolean readBlockFactor = true;

    /**
     * the main constructor.
     *
     * @param fixLength - the max queue size.
     */
    public SimpleBlockingQueue(int fixLength) {
        this.fixSize = fixLength;
        this.queue = new LinkedList<>();
    }

    /**
     * getter for write factor.
     *
     * @return true if writing should be blocked.
     */
    public boolean isWriteBlockFactor() {
        return this.writeBlockFactor;
    }

    /**
     * getter for reading factor.
     *
     * @return true if reading should be blocked.
     */
    public boolean isReadBlockFactor() {
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
}
