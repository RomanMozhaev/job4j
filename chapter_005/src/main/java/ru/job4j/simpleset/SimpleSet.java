package ru.job4j.simpleset;

import ru.job4j.simplearray.SimpleArray;

import java.util.Iterator;
import java.util.Objects;

public class SimpleSet<E> {

    private SimpleArray<E> list;

    public SimpleSet(int length) {
        this.list = new SimpleArray<>(length);
    }

    public void add(E e) {
        if (isUnique(e)) {
            this.list.add(e);
        }
    }

    private boolean isUnique(E e) {
        boolean uniqueE = true;
        Iterator<E> it = iterator();
        while (it.hasNext()) {
            if (Objects.equals(it.next(), e)) {
                uniqueE = false;
                break;
            }
        }
        return uniqueE;
    }

    public Iterator<E> iterator() {
        return this.list.iterator();
    }

    @Override
    public String toString() {

        StringBuilder string = new StringBuilder();
        string.append("Simple Set = {");
        Iterator<E> it = iterator();
        while (it.hasNext()) {
            string.append(it.next());
            if (it.hasNext()) {
                string.append(", ");
            }
        }
        string.append("}");
        return string.toString();
    }
}
