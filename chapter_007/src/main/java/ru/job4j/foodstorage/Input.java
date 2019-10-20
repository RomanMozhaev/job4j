package ru.job4j.foodstorage;

/**
 * the interface for input classes
 */
public interface Input {
    /**
     * the method for receiving a question and returning an answers
     *
     * @param string - question
     * @return - answer
     */
    String ask(String string);
}
