package ru.job4j.nonblock;

import org.junit.Test;

import java.util.concurrent.atomic.AtomicReference;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

/**
 * Cash class tests.
 */
public class CashTest {
    /**
     * the test of exeption throwing
     *
     * @throws InterruptedException
     */
    @Test
    public void whenThrowException() throws InterruptedException {
        Cash cash = new Cash();
        Base model1 = new Base(1, 1);
        Base model2 = new Base(1, 1);
        AtomicReference<Exception> ex = new AtomicReference<>();
        Thread thread = new Thread(
                () -> {
                    try {
                        cash.add(model1);
                        model1.updateVersion();
                        model2.updateVersion();
                        cash.update(model2);
                    } catch (Exception e) {
                        ex.set(e);
                    }
                }
        );
        thread.start();
        thread.join();
        assertThat(ex.get().getMessage(), is("The model was modified by other thread."));
    }

}