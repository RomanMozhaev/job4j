package ru.job4j.generic;

import ru.job4j.simplearray.SimpleArray;

public class RoleStore extends AbstractStore implements Store<Role> {
    SimpleArray<Role> array;
    int length;

    public RoleStore(int length) {
        this.length = length;
        this.array = new SimpleArray<>(length);
    }

    @Override
    public void add(Role model) {
        this.array.add(model);
    }

    @Override
    public boolean replace(String id, Role model) {
        return replaceBase(id, model, this.array);
    }

    @Override
    public boolean delete(String id) {
        return deleteBase(id, this.array);
    }

    @Override
    public Role findById(String id) {
        return (Role) findByIdBase(id, this.array);
    }
}
