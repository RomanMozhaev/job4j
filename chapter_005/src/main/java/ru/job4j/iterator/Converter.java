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
                if (this.nextIt == null) {
                    if (it.hasNext()) {
                        this.nextIt = it.next();
                    }
                }
                while (it.hasNext() && !this.nextIt.hasNext()) {
                    this.nextIt = it.next();
                }
                if (this.nextIt.hasNext() || it.hasNext()) {
                    result = true;
                }
                return result;
            }

            @Override
            public Integer next() {
                int result;
                if (this.hasNext()) {
                    if (this.nextIt.hasNext()) {
                        result = this.nextIt.next();
                    } else {
                        if (it.hasNext()) {
                            this.nextIt = it.next();
                            result = this.nextIt.next();
                        } else {
                            throw new NoSuchElementException();
                        }
                    }
                } else {
                    throw (new NoSuchElementException());
                }
                return result;
            }
        };
    }
}