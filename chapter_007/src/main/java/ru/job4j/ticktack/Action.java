package ru.job4j.ticktack;

/**
 * the interface for classes which is responsible for selected action.
 */
public interface Action {
    /**
     * implements the act.
     */
    void act();

    /**
     * returns the title of the act.
     *
     * @return the string with the title of the act.
     */
    String getTitle();
}
