package ru.job4j.firsthttp;

import java.util.concurrent.ConcurrentHashMap;

/**
 * the class for data validating.
 */
public class ValidateService implements Validate {

    /**
     * the instance of singleton
     */
    private static final ValidateService INSTANCE = new ValidateService();

    /**
     * the memory store.
     */
    private final Store memory = MemoryStore.getInstance();

    /**
     * the main constructor.
     */
    private ValidateService() {

    }

    /**
     * returns the instance of the singleton.
     *
     * @return - the instance.
     */
    public static ValidateService getInstance() {
        return INSTANCE;
    }

    /**
     * adds a new user to the memory.
     *
     * @param user - the user for adding
     * @return true if the user was added; otherwise false.
     */
    @Override
    public boolean add(User user) {
        boolean result = false;
        if (user.getName() != null && !user.getName().equals("") && findById(user.getId()) == null) {
            result = this.memory.add(user);
        }
        return result;
    }

    /**
     * updates the mapped user.
     *
     * @param user - the user instance with new field.
     * @return - true if updating was successful; otherwise false.
     */
    @Override
    public boolean update(User user) {
        boolean result = false;
        String name;
        String email;
        long date;
        int id;
        User listedUser = findById(user.getId());
        if (listedUser != null) {
            id = listedUser.getId();
            date = listedUser.getCreateDate();
            if (user.getName() != null && !user.getName().equals("")) {
                name = user.getName();
            } else {
                name = listedUser.getName();
            }
            if (user.getEmail() != null && !user.getEmail().equals("")) {
                email = user.getEmail();
            } else {
                email = listedUser.getEmail();
            }
            result = this.memory.update(new User(id, name, email, date));
        }
        return result;
    }

    /**
     * deletes the user for the map. id filed used only for identification.
     *
     * @param user - the user for deleting.
     * @return true if the entry was deleted; otherwise false.
     */
    @Override
    public boolean delete(User user) {
        return this.memory.delete(user);
    }

    /**
     * returns all mapped data.
     *
     * @return the string with data.
     */
    @Override
    public ConcurrentHashMap<Integer, User> findAll() {
        return this.memory.getMap();
    }

    /**
     * finds the mapped user by id.
     *
     * @param id - the id for searching
     * @return found user or null.
     */
    @Override
    public User findById(int id) {
        User result = null;
        if (id >= 0) {
            result = this.memory.getMap().get(id);
        }
        return result;
    }
}
