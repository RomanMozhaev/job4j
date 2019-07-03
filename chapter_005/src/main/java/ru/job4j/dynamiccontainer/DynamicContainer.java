package ru.job4j.dynamiccontainer;

import java.util.Arrays;
import java.util.ConcurrentModificationException;
import java.util.Iterator;

public class DynamicContainer<E> implements Iterable<E> {
    private Object[] container;
    private int modCount;
    private int length;
    private int factor;
    private int nextIndex = 0;

    public DynamicContainer(int length) {
        this.length = length;
        this.container = new Object[this.length];
        this.modCount = 0;
        this.factor = 2;
    }

    private int getModCount() {
        return this.modCount;
    }

    private int getNextIndex() {
        return this.nextIndex;
    }

    public int getLength() {
        return length;
    }

    private Object[] getContainer() {
        return Arrays.copyOf(this.container, nextIndex);
    }

    private void sizeCheck() {
        if (this.nextIndex >= length) {
            this.length *= this.factor;
            this.container = Arrays.copyOf(this.container, this.length);
        }
    }

    public void add(E value) {
        sizeCheck();
        this.container[this.nextIndex] = value;
        this.modCount++;
        this.nextIndex++;
    }

    public E get(int position) {
        if (position >= this.nextIndex && position < 0) {
            throw new NullPointerException();
        }
        return (E) this.container[position];
    }


    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {

            private Object[] container = getContainer();
            private int expectedModCount = getModCount();
            private int lastIndex = getNextIndex() - 1;
            private int index;

            @Override
            public boolean hasNext() {
                modCheck();
                boolean result = false;
                if (lastIndex >= index) {
                    result = true;
                }
                return result;
            }

            @Override
            public E next() {
                modCheck();
                return (E) container[index++];
            }

            private void modCheck() {
                if (expectedModCount != getModCount()) {
                    throw new ConcurrentModificationException();
                }
            }
        };
    }
}
