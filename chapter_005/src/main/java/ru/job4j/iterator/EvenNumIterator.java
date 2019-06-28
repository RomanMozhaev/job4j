package ru.job4j.iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * the Iterator returns even number only
 * @author RomanM
 * @version June 28, 2019
 */
public class EvenNumIterator implements Iterator<Integer> {

    private final int[] array;
    int index;

    public EvenNumIterator(final int[] array) {
        this.array = array;
        this.index = 0;
    }
    /**
     * the method checks whether next even number exists in the array.
     * @return true if next even number exists.
     */
    @Override
    public boolean hasNext() {
        boolean result = false;
        while (this.index < this.array.length) {
            if (this.array[this.index] % 2 != 0) {
                this.index++;
            } else {
                result = true;
                break;
            }
        }
        return result;
    }
    /**
     * the method returns the next even number or throw NoSuchElement exception
     * @return the next even number
     */
    @Override
    public Integer next() {
        if (hasNext()) {
            return array[this.index++];
        } else {
            throw (new NoSuchElementException());
        }
    }
}
