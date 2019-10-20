package ru.job4j.foodstorage;

import java.sql.Connection;

/**
 * the class for the shop
 */
public class Shop extends Storage {
    /**
     * the table name for shop in the DB.
     *
     * @param connection - Connection to the DB.
     */
    public Shop(Connection connection) {
        super("shop", connection);
    }
}
