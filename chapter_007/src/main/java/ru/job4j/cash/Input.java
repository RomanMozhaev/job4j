package ru.job4j.cash;

/**
 * the interface for getting user's answer.
 */
public interface Input {
    /**
     * asks user
     * @param string the question.
     * @return - the answer.
     */
    String ask(String string);
}