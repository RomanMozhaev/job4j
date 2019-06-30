package ru.job4j.simplearray;

import java.util.Iterator;
import java.util.NoSuchElementException;
/**
 * Simple array is limited by length array
 * @param <T> the type of collection elements
 */
public class SimpleArray<T> implements Iterable<T> {

    private int length;
    private int position;
    private Object[] array;

    public SimpleArray(int length) {
        this.length = length;
        this.position = 0;
        this.array = new Object[length];
    }
    /**
     * the method adds element to SimpleArray if there is an empty cells.
     * @param model - the element for adding
     */
    public void add(T model) {
        if (this.position >= this.length) {
            throw new IndexOutOfBoundsException();
        }
        if (model != null) {
            array[position] = model;
            position++;
        }
    }
    /**
     * the method replaces the element with index with model if there is an element in the cell with index
     * @param index - index of the element for replacing
     * @param model - the new element for adding instead of the old element
     */
    public boolean set(int index, T model) {
        checkIndex(index);
        array[index] = model;
        return true;
    }
    /**
     * the method removes the element in the cell with index
     * @param index
     */
    public boolean remove(int index) {
        checkIndex(index);
        System.arraycopy(this.array, (index + 1), this.array, index, position - index);
        position--;
        return true;
    }
    /**
     * the method returns the element with index if it exists.
     * @param index of the element which return needed
     * @return T element
     */
    public T get(int index) {
        checkIndex(index);
        return (T) array[index];
    }
    /**
     * the method for checking the correction of the input index. if there is no index it throws an exception.
     * @param index index for checking
     */
    private void checkIndex(int index) {
        if (index < 0 || index > this.position) {
            throw new IndexOutOfBoundsException();
        }
    }
    /**
     * the method returns an iterator
     * @return iterator
     */
    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            int itLength = position;
            int selector = 0;
            @Override
            public boolean hasNext() {
                boolean result = false;
                if (selector < itLength) {
                    result = true;
                }
                return result;
            }
            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return (T) array[selector++];
            }
        };
    }
}
