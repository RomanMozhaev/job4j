package ru.job4j.menustructure;

import java.util.List;

/**
 * the interface for items with children.
 */
public interface IntChildren {
    /**
     * returns the children list.
     * @return the list with children.
     */
    List<IntName> getChildren();
}
