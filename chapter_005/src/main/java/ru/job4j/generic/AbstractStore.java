package ru.job4j.generic;

import ru.job4j.simplearray.SimpleArray;

import java.util.Iterator;

public abstract class AbstractStore<T extends Base> implements Store<T> {

    protected SimpleArray<T> array;
    protected int length;

    public AbstractStore(int length) {
        this.length = length;
        this.array = new SimpleArray<>(length);
    }

    private int findIndex(String id, SimpleArray<T> array) {
        Iterator<T> it = array.iterator();
        int result = -1;
        int index = 0;
        while (it.hasNext()) {
            if (it.next().getId().equals(id)) {
                result = index;
                break;
            }
            index++;
        }
        return result;
    }

    @Override
    public void add(T model) {
        array.add(model);
    }

    @Override
    public boolean delete(String id) {
        int index = findIndex(id, array);
        return index != -1 && array.remove(index);
    }
    @Override
    public boolean replace(String id, T model) {
        int index = findIndex(id, array);
        return index != -1 && array.set(index, model);
    }
    @Override
    public T findById(String id) {
        int index = findIndex(id, array);
        return index != -1 ? array.get(index) : null;
    }


}
