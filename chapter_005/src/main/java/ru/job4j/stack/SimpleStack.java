package ru.job4j.stack;

import ru.job4j.dynamiclinkedlist.DynamicLinkedList;

import java.util.EmptyStackException;

public class SimpleStack<T> {

    private DynamicLinkedList<T> list = new DynamicLinkedList<>();


    public T poll() {
        if (isEmpty()) {
            throw new EmptyStackException();
        }
        return this.list.remove(this.list.getLength() - 1);
    }

    public void push(T value) {
        this.list.add(value);
    }

    public boolean isEmpty() {
        boolean result = false;
        if (this.list.getLength() == 0) {
           result = true;
        }
        return result;
    }

}
