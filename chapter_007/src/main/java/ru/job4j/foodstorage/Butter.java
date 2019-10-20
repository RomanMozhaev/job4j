package ru.job4j.foodstorage;

public class Butter extends Food {
    private static final int TYPE_ID = 2;

    public Butter(String name, long expireDate, long createDate, long price, double discount) {
        super(name, expireDate, createDate, price, discount, TYPE_ID);
    }
}
