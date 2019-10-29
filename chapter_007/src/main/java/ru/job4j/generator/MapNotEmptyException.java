package ru.job4j.generator;

/**
 * the Exception is thrown when the map has one or more unused keys.
 */
public class MapNotEmptyException extends Exception {

    public MapNotEmptyException(String message) {
        super(message);
    }
}
