package ru.job4j.foodstorage;

/**
 * the class for salt crackers.
 */
public class SaltCrackers extends Food {
    /**
     * the id of the type for salt crackers in the DB.
     */
    private static final int TYPE_ID = 3;

    public SaltCrackers(String name, long expireDate, long createDate, long price, double discount) {
        super(name, expireDate, createDate, price, discount, TYPE_ID);
    }
}
