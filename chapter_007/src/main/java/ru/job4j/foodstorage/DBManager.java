package ru.job4j.foodstorage;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;


public class DBManager {
    private Connection connect;
    private static final Logger LOG = LogManager.getLogger(DBManager.class.getName());
    private final String url;
    private final String username;
    private final String password;


    public DBManager(String url, String username, String password) {
        this.url = url;
        this.username = username;
        this.password = password;
    }

    /**
     * initializes the connection to the data base and creates tables if they do not exist
     *
     * @return Connection
     */
    public Connection init() {
        try {
            this.connect = DriverManager.getConnection(this.url, this.username, this.password);
            this.connect.setAutoCommit(false);
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
        return this.connect;
    }

    public Connection getConnect() {
        return connect;
    }

}
