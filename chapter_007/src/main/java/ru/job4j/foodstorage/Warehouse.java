package ru.job4j.foodstorage;

import java.util.ArrayList;
import java.util.List;

/**
 * the class for the warehouse
 */
public class Warehouse implements Store {

    private List<Food> list = new ArrayList<>();
    @Override
    public List<Food> getList() {
        return list;
    }

    @Override
    public boolean accept(Food food) {
        boolean result = false;
        long now = System.currentTimeMillis();
        long quoter = (food.getExpireDate() - food.getCreateDate()) / 4 + food.getCreateDate();
        if (quoter >= now) {
            result = true;
        }
        return result;

    }

    @Override
    public void add(Food food) {
        this.list.add(food);
    }
}
