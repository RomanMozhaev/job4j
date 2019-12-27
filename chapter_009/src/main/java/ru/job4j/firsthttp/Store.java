package ru.job4j.firsthttp;

import java.util.concurrent.ConcurrentHashMap;

public interface Store {

    ConcurrentHashMap<Integer, User> getMap();

    boolean add(User user);

    boolean delete(User user);

    boolean update(User user);
}
