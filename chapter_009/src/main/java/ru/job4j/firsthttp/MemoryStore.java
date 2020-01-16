package ru.job4j.firsthttp;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * the class provides ability to work with map where users' data contains.
 */
public class MemoryStore implements Store {
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
     * the value of the next serial ID for adding user.
     */
    private AtomicInteger serialID = new AtomicInteger();

    /**
     * returns the instance of the class.
     *
     * @return the instance of singleton.
     */
    public static Store getInstance() {
        return INSTANCE;
    }

    @Override
    public Map<Integer, User> findAll() {
        return this.map;
    }

    @Override
    public User findById(int id) {
        return this.map.get(id);
    }

    /**
     * adds the new user if the id is not mapped in the map.
     *
     * @param user the new user.
     * @return - true if the user was added; otherwise false.
     */
    @Override
    public boolean add(User user) {
        boolean result = false;
        int id = this.serialID.incrementAndGet();
        User newUser = new User(id, user.getName(), user.getEmail(),
                user.getCreateDate(), user.getPhotoId(),
                user.getPassword(), user.getRole());
        if (this.map.putIfAbsent(newUser.getId(), newUser) == null) {
            result = true;
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
    public boolean update(User user) {
        boolean result = false;
        if (this.map.replace(user.getId(), user) != null) {
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
    public boolean delete(User user) {
        return this.map.remove(user.getId()) != null;
    }
}