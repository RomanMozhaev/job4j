package ru.job4j.foodstorage;

import java.util.ArrayList;
import java.util.List;

/**
 * the class for the trash
 */
public class Trash implements Store {

    private List<Food> list = new ArrayList<>();

    @Override
    public List<Food> getList() {
        return list;
    }

    @Override
    public boolean accept(Food food) {
        boolean result = false;
        long now = System.currentTimeMillis();
        if (food.getExpireDate() <= now) {
                result = true;
            }
        return result;
    }

    @Override
    public void add(Food food) {
        this.list.add(food);
    }
}
