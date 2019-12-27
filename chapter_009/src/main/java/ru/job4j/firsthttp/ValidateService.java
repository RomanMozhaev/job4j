package ru.job4j.firsthttp;

import java.util.concurrent.ConcurrentHashMap;

public class ValidateService implements Validate {

    private static final ValidateService INSTANCE = new ValidateService();

    private final Store memory = MemoryStore.getInstance();
    private final String LN = System.lineSeparator();

    private ValidateService() {

    }

    public static ValidateService getInstance() {
        return INSTANCE;
    }

    @Override
    public boolean add(User user) {
        boolean result = false;
        if (user.getName() !=null && !user.getName().equals("") && findById(user.getId()) == null) {
            result = this.memory.add(user);
        }
        return result;
    }
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
    @Override
    public boolean delete(User user) {
        return this.memory.delete(user);
    }
    @Override
    public String findAll() {
        StringBuilder builder = new StringBuilder();
        ConcurrentHashMap<Integer, User> map = this.memory.getMap();
        map.forEach((i, user) -> {
            builder.append(user.getId());
            builder.append(LN);
            builder.append(user.getName());
            builder.append(LN);
            builder.append(user.getEmail());
            builder.append(LN);
            builder.append(user.getCreateDate());
            builder.append(LN);
            builder.append(LN);
        });
        return builder.toString();
    }
    @Override
    public User findById(int id) {
        User result = null;
        if (id >= 0) {
            result = this.memory.getMap().get(id);
        }
        return result;
    }
}
