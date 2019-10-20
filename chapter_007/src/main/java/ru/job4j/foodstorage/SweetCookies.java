package ru.job4j.foodstorage;
/**
 * the class for Sweet Cookies.
 */
public class SweetCookies extends Food {
    /**
     * the id of the type for Sweet Cookies in the DB.
     */
    private static final int TYPE_ID = 4;
    public SweetCookies(String name, long expireDate, long createDate, long price, double discount) {
        super(name, expireDate, createDate, price, discount, TYPE_ID);
    }
}
