package ru.job4j.simpleset;

import ru.job4j.simplearray.SimpleArray;

import java.util.Iterator;

public class SimpleSet<E> {

    private SimpleArray<E> list;

    public SimpleSet(int length) {
        this.list = new SimpleArray<>(length);
    }

    public void add(E e) {
        boolean isIn = false;
        Iterator<E> it = iterator();
        while (it.hasNext()) {
            if (it.next().equals(e)) {
                isIn = true;
                break;
            }
        }
        if (!isIn) {
            this.list.add(e);
        }
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
            if(it.hasNext()) {
                string.append(", ");
            }
        }
        string.append("}");
        return string.toString();
    }
}
