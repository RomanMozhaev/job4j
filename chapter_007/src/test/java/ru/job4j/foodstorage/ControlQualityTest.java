package ru.job4j.foodstorage;

import org.junit.Before;
import org.junit.Test;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class ControlQualityTest {
    private Connection connection;
    private Storage trash;
    private Storage shop;
    private Storage warehouse;
    private Food bread;
    private Food butter;
    private Food cookies;
    private Food crackers;
    List<Food> foodList;

    @Before
    public void init() throws SQLException {
        Config c = new Config();
        c.init();
        DBManager manager = new DBManager(c.get("url"), c.get("username"), c.get("password"));
        manager.init();
        this.connection = ConnectionRollback.create(manager.getConnect());
        this.trash = new Trash(this.connection);
        this.shop = new Shop(this.connection);
        this.warehouse = new Warehouse(this.connection);
        long now = System.currentTimeMillis();
        long oneDay = 24 * 60 * 60 * 1000;
        this.bread = new Bread("Bread", now, now - 3 * oneDay, 10000, 0);
        this.butter = new Butter("Butter", now + 2 * oneDay, now - 24 * oneDay, 15000, 0);
        this.cookies = new SweetCookies("Sweet Cookies", now + 180 * oneDay, now - 180 * oneDay, 5000, 0);
        this.crackers = new SaltCrackers("Salt Crackers", now + 180 * oneDay, now - oneDay, 6000, 0);
        this.foodList = new LinkedList<>();
        this.foodList.addAll(List.of(bread, butter, cookies, crackers));
        String[] discountCommands = {"10", "n"};
        ControlQuality cq = new ControlQuality(this.trash, this.shop, this.warehouse, new TestInput(discountCommands));
        cq.start(foodList);
    }

    @Test
    public void whenBadThenTrash() {
        List<Food> trashList = this.trash.getProductsList();
        Food result = trashList.get(0);
        assertThat(result.equals(this.bread), is(true));
    }
    @Test
    public void whenAlmostBadThenDiscount() {
        List<Food> shopList = this.shop.getProductsList();
        Food result = shopList.get(0);
        assertThat(result.equals(this.butter), is(true));
    }
    @Test
    public void whenOkayThenShop() {
        List<Food> shopList = this.shop.getProductsList();
        Food result = shopList.get(1);
        assertThat(result.equals(this.cookies), is(true));
    }
    @Test
    public void whenFreshThenWarehouse() {
        List<Food> wareHouseList = this.warehouse.getProductsList();
        Food result = wareHouseList.get(0);
        assertThat(result.equals(this.crackers), is(true));
    }
}