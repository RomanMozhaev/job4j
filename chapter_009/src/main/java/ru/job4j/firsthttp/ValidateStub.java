package ru.job4j.firsthttp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ValidateStub implements Validate {
    private final Map<Integer, User> store = new HashMap<>();
    private int ids = 0;

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
        return false;
    }

    @Override
    public boolean delete(User user) {
        return false;
    }

    @Override
    public Map<Integer, User> findAll() {
        return null;
    }

    @Override
    public User findById(int id) {
        return null;
    }

    @Override
    public int isCredential(String name, String password) {
        return 0;
    }
}
