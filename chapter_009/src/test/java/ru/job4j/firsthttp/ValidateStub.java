package ru.job4j.firsthttp;

import java.util.HashMap;
import java.util.Map;

public class ValidateStub implements Validate {
    private final Map<Integer, User> store = new HashMap<>();
    private int ids = 1;

    @Override
    public boolean add(User user) {
        int id = ids++;
        User newUser = new User(id, user.getName(),
                user.getEmail(), user.getPhotoId(),
                user.getPassword(), user.getRole());
        User result = this.store.put(id, newUser);
        return result == null;
    }

    @Override
    public boolean update(User user) {
        return this.store.put(user.getId(), user) != null;
    }

    @Override
    public boolean delete(User user) {
        return this.store.remove(user.getId()) != null;
    }

    @Override
    public Map<Integer, User> findAll() {
        return this.store;
    }

    @Override
    public User findById(int id) {
        return this.store.get(id);
    }

    @Override
    public int isCredential(String name, String password) {
        return 0;
    }
}
