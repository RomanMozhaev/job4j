package ru.job4j.generator;

/**
 * the Exception is thrown when the map does not have a required key.
 */
public class NoKeyException extends Exception {

    public NoKeyException(String message) {
        super(message);
    }
}
