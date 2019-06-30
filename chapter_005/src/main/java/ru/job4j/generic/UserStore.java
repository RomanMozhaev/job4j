package ru.job4j.generic;

import ru.job4j.simplearray.SimpleArray;

public class UserStore extends AbstractStore implements Store<User> {
    SimpleArray<User> array;
    int length;

    public UserStore(int length) {
        this.length = length;
        this.array = new SimpleArray<>(length);
    }

    @Override
    public void add(User model) {
        array.add(model);
    }

    @Override
    public boolean replace(String id, User model) {
        return replaceBase(id, model, this.array);
    }

    @Override
    public boolean delete(String id) {
        return deleteBase(id, this.array);
    }

    @Override
    public User findById(String id) {
        return (User) findByIdBase(id, this.array);
    }
}
