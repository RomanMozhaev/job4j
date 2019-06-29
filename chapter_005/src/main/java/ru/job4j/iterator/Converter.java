package ru.job4j.iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Converter {
    /**
     * the method returns the iterator which goes through all iterators
     * @param it Iterators' iterator
     * @return - iterator for all iterators in the iterators' iterator
     */
    public Iterator<Integer> convert(Iterator<Iterator<Integer>> it) {
        return new Iterator<Integer>() {
            private Iterator<Integer> nextIt = null;
            @Override
            public boolean hasNext() {

                boolean result = false;
                if (this.nextIt == null && it.hasNext()) {
                    this.nextIt = it.next();
                }
                while (it.hasNext() && !this.nextIt.hasNext()) {
                    this.nextIt = it.next();
                }
                if (this.nextIt.hasNext()) {
                    result = true;
                }
                return result;
            }

            @Override
            public Integer next() {
                if (!this.hasNext()) {
                    throw new NoSuchElementException();
                }
                return nextIt.next();
            }
        };
    }
}