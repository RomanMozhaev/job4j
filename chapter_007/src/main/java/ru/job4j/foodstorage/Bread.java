package ru.job4j.foodstorage;

public class Bread extends Food {

    private static final int TYPE_ID = 1;

    public Bread(String name, long expireDate, long createDate, long price, double discount) {
        super(name, expireDate, createDate, price, discount, TYPE_ID);
    }
}
