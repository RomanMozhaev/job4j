package ru.job4j.pool;

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
    private final ThreadPool pool;

    /**
     * the main constructor.
     *
     * @param pool - the thread pool
     * @param name - the name/ number of the thread.
     */
    public ThreadSample(ThreadPool pool, String name) {
        this.t = new Thread(this, name);
        this.pool = pool;
        this.pool.addThread(this.t);
    }

    /**
     * the run method. It finishes its work when interrupt was called.
     */
    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            try {
                synchronized (this.pool) {
                    doSomeWork(this.pool.getTask());
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    /**
     * the method for imitation of the incoming thread work.
     * wait method is called if task is null.
     *
     * @param task - the incoming thread for execution
     * @throws InterruptedException
     */
    private void doSomeWork(Runnable task) throws InterruptedException {
        if (task != null) {
            System.out.println(Thread.currentThread().getName() + " " + task.toString());
        } else {
            this.pool.wait();
        }
    }
}
