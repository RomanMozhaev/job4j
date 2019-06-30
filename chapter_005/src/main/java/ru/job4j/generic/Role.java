package ru.job4j.generic;

public class Role extends Base {

    protected Role(String id) {
        super(id);
    }

    @Override
    public String toString() {
        return String.format("Role{id = %s}", getId());
    }


}
