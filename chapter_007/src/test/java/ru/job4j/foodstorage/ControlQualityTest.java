package ru.job4j.foodstorage;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class ControlQualityTest {
    private Store trash;
    private Store shop;
    private Store warehouse;
    private Food bread;
    private Food butter;
    private Food cookies;
    private Food crackers;

    @Before
    public void init() {
        List<Store> storeList = new ArrayList<>();
        this.trash = new Trash();
        this.warehouse = new Warehouse();
        String[] discountCommands = {"10", "n"};
        this.shop = new Shop(new TestInput(discountCommands));
        storeList.addAll(List.of(this.trash, this.warehouse, this.shop));
        long now = System.currentTimeMillis();
        long oneDay = 24 * 60 * 60 * 1000;
        this.bread = new Bread("Bread", now, now - 3 * oneDay, 10000, 0);
        this.butter = new Butter("Butter", now + 2 * oneDay, now - 24 * oneDay, 15000, 0);
        this.cookies = new SweetCookies("Sweet Cookies", now + 180 * oneDay, now - 180 * oneDay, 5000, 0);
        this.crackers = new SaltCrackers("Salt Crackers", now + 180 * oneDay, now - oneDay, 6000, 0);
        List<Food> foodList = new LinkedList<>();
        foodList.addAll(List.of(bread, butter, cookies, crackers));
        ControlQuality cq = new ControlQuality(storeList);
        cq.start(foodList);
    }

    @Test
    public void whenBadThenTrash() {
        List<Food> trashList = this.trash.getList();
        Food result = trashList.get(0);
        assertThat(result.equals(this.bread), is(true));
    }
    @Test
    public void whenAlmostBadThenDiscount() {
        List<Food> shopList = this.shop.getList();
        Food result = shopList.get(0);
        assertThat(result.equals(this.butter), is(true));
    }
    @Test
    public void whenOkayThenShop() {
        List<Food> shopList = this.shop.getList();
        Food result = shopList.get(1);
        assertThat(result.equals(this.cookies), is(true));
    }
    @Test
    public void whenFreshThenWarehouse() {
        List<Food> wareHouseList = this.warehouse.getList();
        Food result = wareHouseList.get(0);
        assertThat(result.equals(this.crackers), is(true));
    }
}