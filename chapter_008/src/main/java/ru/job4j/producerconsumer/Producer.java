package ru.job4j.producerconsumer;

/**
 * the class for thread which writes elements to the queue.
 */
public class Producer implements Runnable {
    /**
     * the thread
     */
    protected Thread t;
    /**
     * the queue. the monitor.
     */
    private final SimpleBlockingQueue<Integer> queue;
    /**
     * the starting value of the element.
     */
    private Integer element = 0;

    /**
     * the main constructor.
     *
     * @param queue - the queue, common object for all threads, the monitor.
     */
    public Producer(SimpleBlockingQueue<Integer> queue) {
        this.t = new Thread(this, "Producer");
        this.queue = queue;
    }

    /**
     * the run method. it has a waiting cycle for case if the queue is full.
     */
    @Override
    public void run() {
        while (this.element < 10) {
            this.queue.writerWaiting();
            write();
        }
    }

    /**
     * adda the value into the queue.
     */
    private void write() {
        boolean result = this.queue.offer(this.element);
        if (result) {
            this.element++;
        }
    }
}
