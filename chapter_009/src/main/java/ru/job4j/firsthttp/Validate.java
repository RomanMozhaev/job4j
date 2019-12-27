package ru.job4j.firsthttp;

/**
 * the interface for validating classes.
 */
public interface Validate {

    /**
     * adds a new user to the memory.
     */
    boolean add(User user);

    /**
     * updates the mapped user.
     */
    boolean update(User user);

    /**
     * deletes the user for the map.
     */
    boolean delete(User user);

    /**
     * returns all mapped data.
     */
    String findAll();

    /**
     * finds the mapped user by id.
     */
    User findById(int id);
}
