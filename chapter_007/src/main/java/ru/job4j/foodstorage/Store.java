package ru.job4j.foodstorage;

import java.util.List;

/**
 * the interface for all sores.
 */
interface Store {

    /**
     * checks if the product suits to the specific store
     * @param food - the food
     * @return true if suited.
     */
    boolean accept(Food food);

    /**
     * adds to the list
     */
    void add(Food food);

    /**
     * returns the foods list
     * @return the foods list
     */
    List<Food> getList();

    /**
     * the method removes all in the food list.
     */
    void clearList();
}
