package ru.job4j.producerconsumer;

/**
 * the class for tread which consumes the values from the queue and prints them in console.
 */
public class Consumer implements Runnable {
    /**
     * the thread.
     */
    protected Thread t;
    /**
     * the queue, the monitor.
     */
    private final SimpleBlockingQueue<Integer> queue;

    /**
     * the main constructor.
     *
     * @param queue - the queue, the common object, the monitor.
     */
    public Consumer(SimpleBlockingQueue<Integer> queue) {
        this.t = new Thread(this, "Consumer");
        this.queue = queue;
    }

    /**
     * the run method has waiting cycle for case if the queue is empty.
     * the main thread must interrupt this thread to let it know that producer's work was finished.
     */
    @Override
    public void run() {
        try {
            while (!(this.queue.isReadBlockFactor() && this.t.isInterrupted())) {
                this.queue.readerWaiting();
                read();
            }
        } catch (InterruptedException e) {
            this.t.interrupt();
            e.printStackTrace();
        }
    }

    /**
     * the method
     */
    private void read() {
        Integer result = this.queue.poll();
        if (result != null) {
            System.out.println("Read from the queue " + result);
        }
    }
}