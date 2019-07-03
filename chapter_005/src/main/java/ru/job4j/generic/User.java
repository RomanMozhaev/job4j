package ru.job4j.generic;

public class User extends Base {

    protected User(String id) {
        super(id);
    }
    @Override
    public String toString() {
        return String.format("User{id = %s}", getId());
    }
}
