package ru.job4j.generic;

import ru.job4j.simplearray.SimpleArray;

import java.util.Iterator;

public abstract class AbstractStore<T extends Base> {

    public int findIndex(String id, SimpleArray<T> array) {
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

    public boolean deleteBase(String id, SimpleArray<T> array) {
        int index = findIndex(id, array);
        return index != -1 && array.remove(index);
    }

    public boolean replaceBase(String id, T model, SimpleArray<T> array) {
        int index = findIndex(id, array);
        return index != -1 && array.set(index, model);
    }
    public T findByIdBase(String id, SimpleArray<T> array) {
        int index = findIndex(id, array);
        return index != -1 ? array.get(index) : null;
    }


}
