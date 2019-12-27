package ru.job4j.firsthttp;


public interface Validate {

    boolean add(User user);

    boolean update(User user);

    boolean delete(User user);

    String findAll();

    User findById(int id);
}
