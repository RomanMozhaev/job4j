package ru.job4j.nonblock;

/**
 * the class of exception which appears when the version of model is not matched.
 */
public class OptimisticException extends RuntimeException {

    public OptimisticException(String message) {
        super(message);
    }
}
