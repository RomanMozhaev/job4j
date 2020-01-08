package ru.job4j.firsthttp;

import java.util.concurrent.ConcurrentHashMap;

/**
 * the interface for working with memory class.
 */
public interface Store {

    /**
     * returns the map with users.
     *
     * @return
     */
    ConcurrentHashMap<Integer, User> getMap();

    /**
     * adds the new user if the id is not mapped in the map.
     *
     * @param user the new user.
     * @return - true if the user was added; otherwise false.
     */
    boolean add(User user);

    /**
     * deletes the user from the map.
     *
     * @param user - the user for deleting.
     * @return true if deleted; otherwise false.
     */
    boolean delete(User user);

    /**
     * updates data for the mapped user.
     *
     * @param user - the user with field for updating.
     * @return - true if the user was updated; otherwise false.
     */
    boolean update(User user);
}