package ru.job4j.threadsafe;

public interface ThreadSafeList<E> extends Iterable<E> {

    int getLength();

    void add(E value);

    E get(int position);

    E removeLast();

    E remove(int position);
}
