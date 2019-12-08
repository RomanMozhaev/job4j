package ru.job4j.producerconsumer;

/**
 * the class for starting the Producer-Consumer program.
 */
public class ProducerConsumer {

    /**
     * the main method starts threads and has the cycle which interrupts consumer's thread to let it know
     * that producer's thread was terminated.
     *
     * @param args are not used.
     */
    public static void main(String[] args) {
        SimpleBlockingQueue<Integer> queue = new SimpleBlockingQueue<>(5);
        Producer p = new Producer(queue);
        Consumer c = new Consumer(queue);
        p.t.start();
        c.t.start();
        do {
            if (p.t.getState() == Thread.State.TERMINATED) {
                c.t.interrupt();
                queue.notifyAllThreads();
            }
        } while (c.t.getState() != Thread.State.TERMINATED);
    }
}
