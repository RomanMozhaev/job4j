package ru.job4j.foodstorage;

import java.util.List;

/**
 * ControlQuality the class which divide Food list into lists add them to different places
 * (shop, warehouse, trash).
 */
public class ControlQuality {
    /**
     * storage - the list of all stores
     */
    private List<Store> stores;

    public ControlQuality(List<Store> storeList) {
        this.stores = storeList;
    }

    /**
     * adds the food to corresponding store.
     *
     * @param food - the food
     */
    public void add(Food food) {
        for (Store store : this.stores) {
            if (store.accept(food)) {
                store.add(food);
                break;
            }
        }
    }

    /**
     * start method
     *
     * @param foodList - list of the food
     */
    public void start(List<Food> foodList) {
        for (Food food : foodList) {
            add(food);
        }
    }

}
