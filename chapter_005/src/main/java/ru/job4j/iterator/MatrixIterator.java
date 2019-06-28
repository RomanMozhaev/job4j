package ru.job4j.iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class MatrixIterator implements Iterator<Integer> {

    private final int[][] matrix;
    int column;
    int row;

    public MatrixIterator(final int[][] matrix) {
        this.matrix = matrix;
        this.column = 0;
        this.row = 0;
    }

    @Override
    public boolean hasNext() {
        return row < matrix.length;
    }

    @Override
    public Integer next() {
        if (hasNext()) {
            int result = this.matrix[this.row][this.column];
            this.column++;
            if (this.column == this.matrix[this.row].length) {
                this.row++;
                this.column = 0;
            }
            return result;
        } else {
            throw (new NoSuchElementException());
        }
    }
}