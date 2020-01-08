package ru.job4j.firsthttp;

import java.util.concurrent.ConcurrentHashMap;

/**
 * the class provides ability to work with map where users' data contains.
 */
public class MemoryStore implements Store {
    /**
     * the number for a next adding user.
     */
    private volatile int newId = 0;

    /**
     * the instance for singleton.
     */
    private static final MemoryStore INSTANCE = new MemoryStore();
    /**
     * the map with users.
     */
    private final ConcurrentHashMap<Integer, User> map = new ConcurrentHashMap<>();

    /**
     * the main constructor.
     */
    private MemoryStore() {
    }

    /**
     * returns the instance of the class.
     *
     * @return the instance of singleton.
     */
    public static Store getInstance() {
        return INSTANCE;
    }

    @Override
    public ConcurrentHashMap<Integer, User> getMap() {
        return this.map;
    }

    /**
     * adds the new user if the id is not mapped in the map.
     *
     * @param user the new user.
     * @return - true if the user was added; otherwise false.
     */
    @Override
    public synchronized boolean add(User user) {
        boolean result = false;
        int id = user.getId();
        long date = user.getCreateDate();
        if (date == -1) {
            date = System.currentTimeMillis();
            if (id == -1 || !this.map.containsKey(id)) {
                if (id == -1) {
                    id = generateNewId();
                }
                User newUser = new User(id, user.getName(), user.getEmail(), date);
                this.map.put(newUser.getId(), newUser);
                result = true;
            }
        }
        return result;
    }


    /**
     * updates data for the mapped user.
     * The is used for user identification.
     *
     * @param user - the user with field for updating.
     * @return - true if the user was updated; otherwise false.
     */
    @Override
    public synchronized boolean update(User user) {
        boolean result = false;
        if (this.map.containsKey(user.getId())) {
            this.map.put(user.getId(), user);
            result = true;
        }
        return result;
    }

    /**
     * deletes the user from the map.
     *
     * @param user - the user for deleting. id field is used only.
     * @return true if deleted; otherwise false.
     */
    @Override
    public synchronized boolean delete(User user) {
        return this.map.remove(user.getId()) != null;
    }

    /**
     * the method for new order number generation.
     *
     * @return - the order number.
     */
    private synchronized int generateNewId() {
        do {
            this.newId++;
        } while (this.map.containsKey(this.newId));
        return this.newId;
    }
}