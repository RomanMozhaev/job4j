package ru.job4j.connections;

import java.util.Objects;

/**
 * the simple container of the value, includes the row number of the value.
 */
public class SimpleElement {

    /**
     * the value from the file
     */
    private String value;
    /**
     * the line where the value was in the file.
     */
    private int rowNumber;

    public SimpleElement(String value, int rowNumber) {
        this.value = value;
        this.rowNumber = rowNumber;
    }

    public String getValue() {
        return value;
    }

    public int getRowNumber() {
        return rowNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        SimpleElement element = (SimpleElement) o;
        return Objects.equals(value, element.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
