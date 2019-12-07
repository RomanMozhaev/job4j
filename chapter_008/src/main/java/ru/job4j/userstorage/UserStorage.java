package ru.job4j.userstorage;

import net.jcip.annotations.ThreadSafe;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * the class for storing user objects
 */
@ThreadSafe
public class UserStorage {
    /**
     * the thread-safe concurrent collection for storing users.
     */
    private final ConcurrentMap<Integer, User> userMap;

    /**
     * the main constructor.
     */
    public UserStorage() {
        this.userMap = new ConcurrentHashMap<>();
    }

    /**
     * the method for adding user into the map.
     *
     * @param user - user for adding.
     * @return true if it was added, false - if not.
     */
    public synchronized boolean add(User user) {
        return this.userMap.putIfAbsent(user.getId(), user) == null;
    }

    /**
     * the method for replacing data if the user with same id is mapped.
     *
     * @param user - the user for replacing
     * @return true if the user id was mapped and the user was replaced, false if not.
     */
    public synchronized boolean update(User user) {
        return this.userMap.replace(user.getId(), user) != null;
    }

    /**
     * the method for deleting user from the map if key and amount are equal.
     *
     * @param user the user which should be deleted.
     * @return true if the user was deleted or false if not.
     */
    public synchronized boolean delete(User user) {
        return this.userMap.remove(user.getId(), user);
    }

    /**
     * the method for transferring amount from one user to another.
     *
     * @param fromId - the supplier user id
     * @param toId   - the consumer user id
     * @param amount the value for transferring
     * @return true if users are mapped and supplier's amount more or equal to amount.
     */
    public synchronized boolean transfer(int fromId, int toId, int amount) {
        boolean result = false;
        User userFrom = this.userMap.get(fromId);
        User userTo = this.userMap.get(toId);
        if (userFrom != null && userTo != null && userFrom.getAmount() >= amount) {
            update(new User(userFrom.getId(), userFrom.getAmount() - amount));
            update(new User(userTo.getId(), userTo.getAmount() + amount));
            result = true;
        }
        return result;
    }
}
