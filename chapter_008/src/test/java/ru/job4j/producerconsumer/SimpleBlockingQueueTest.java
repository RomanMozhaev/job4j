package ru.job4j.producerconsumer;

import org.junit.Test;

import java.util.Arrays;
import java.util.concurrent.CopyOnWriteArrayList;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class SimpleBlockingQueueTest {
    @Test
    public void whenFetchAllThenGetIt() throws InterruptedException {
        final CopyOnWriteArrayList<Integer> buffer = new CopyOnWriteArrayList<>();
        final SimpleBlockingQueue<Integer> queue = new SimpleBlockingQueue<>(2);
        Thread producer = new Thread(
                () -> {
                    for (int i = 0; i < 5; i++) {
                        while (queue.isWriteBlockFactor()) {
                            try {
                                synchronized (queue) {
                                    queue.wait();
                                }
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                        if (!queue.offer(i)) {
                            i--;
                        }
                    }
                }
        );

        producer.start();
        Thread consumer = new Thread(
                () -> {
                    while (!(queue.isReadBlockFactor() && Thread.currentThread().isInterrupted())) {
                        try {
                            while (queue.isReadBlockFactor()) {
                                synchronized (queue) {
                                    queue.wait();
                                }
                            }
                            Integer el = queue.poll();
                            if (el != null) {
                                buffer.add(el);
                            }
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                            Thread.currentThread().interrupt();
                        }
                    }
                }
        );
        consumer.start();
        producer.join();
        consumer.interrupt();
        consumer.join();
        assertThat(buffer, is(Arrays.asList(0, 1, 2, 3, 4)));
    }
}
