package ru.job4j.foodstorage;

import java.sql.Connection;

/**
 * the class for the trash
 */
public class Trash extends Storage {
    /**
     * the table name for trash in the DB.
     *
     * @param connection - Connection to the DB.
     */
    public Trash(Connection connection) {
        super("trash", connection);
    }
}
