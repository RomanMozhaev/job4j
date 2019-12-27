package ru.job4j.firsthttp;

import java.util.concurrent.ConcurrentHashMap;

public class MemoryStore implements Store {

    private volatile int newId = 0;

    private static final MemoryStore INSTANCE = new MemoryStore();
    private final ConcurrentHashMap<Integer, User> map = new ConcurrentHashMap<>();

    private MemoryStore() {
    }

    public static Store getInstance() {
        return INSTANCE;
    }

    @Override
    public ConcurrentHashMap<Integer, User> getMap() {
        return this.map;
    }

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

    @Override
    public synchronized boolean update(User user) {
        boolean result = false;
        if (this.map.containsKey(user.getId())) {
            this.map.put(user.getId(), user);
            result = true;
        }
        return result;
    }


    @Override
    public synchronized boolean delete(User user) {
        return this.map.remove(user.getId()) != null;
    }

    private synchronized int generateNewId() {
        do {
            this.newId++;
        } while (this.map.containsKey(this.newId));
        return this.newId;
    }
}
