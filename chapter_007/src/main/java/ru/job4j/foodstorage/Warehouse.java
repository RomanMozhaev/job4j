package ru.job4j.foodstorage;

import java.sql.Connection;

/**
 * the class for the warehouse
 */
public class Warehouse extends Storage {
    /**
     * the table name for warehouse in the DB.
     *
     * @param connection - Connection to the DB.
     */
    public Warehouse(Connection connection) {
        super("warehouse", connection);
    }
}
