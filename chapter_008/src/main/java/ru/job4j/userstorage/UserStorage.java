package ru.job4j.userstorage;

import net.jcip.annotations.ThreadSafe;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

@ThreadSafe
public class UserStorage {

    private ConcurrentMap<Integer, User> userMap;

    public UserStorage() {
        this.userMap = new ConcurrentHashMap<>();
    }

    public boolean add (User user) {
        return this.userMap.putIfAbsent(user.getId(), user) == null;
    }

    public boolean update(User user) {
        return this.userMap.replace(user.getId(), user) != null;
    }

    public boolean delete(User user) {
        return this.userMap.remove(user.getId()) != null;
    }

    public boolean transfer(int fromId, int toId, int amount) {
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
