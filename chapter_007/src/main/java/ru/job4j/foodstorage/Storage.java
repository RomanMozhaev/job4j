package ru.job4j.foodstorage;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

/**
 * the common class for all sores.
 */
public class Storage {
    /**
     * productList - list of the product
     * tableName - name of the table for a store in DB
     * connection - Connection to the DB
     * LOG - logger
     */
    private List<Food> productsList;
    private String tableName;
    private Connection connection;
    private static final Logger LOG = LogManager.getLogger(Storage.class.getName());

    public Storage(String tableName, Connection connection) {
        this.productsList = new LinkedList<>();
        this.tableName = tableName;
        this.connection = connection;
    }

    /**
     * gets and returns product list from the DB
     *
     * @return - product list
     */
    public List<Food> getProductsList() {
        List<Food> productsList = readListFromDB();
        return productsList;
    }

    /**
     * reads data from the DB and writes data to the List<Food>.
     *
     * @return - the List.
     */
    private List<Food> readListFromDB() {
        FoodType foodType = new FoodType();
        List<Food> productList = new LinkedList<>();
        try {
            String query = "SELECT tp.name, t.name, t.expireDate, t.createDate, t.price, t.discount"
                    + " FROM " + this.tableName
                    + " AS t INNER JOIN types AS tp"
                    + " ON t.type_id = tp.type_id;";
            Statement s = this.connection.createStatement();
            ResultSet rs = s.executeQuery(query);
            while (rs.next()) {
                String className = rs.getString(1);
                String name = rs.getString(2);
                long expireDate = rs.getLong(3);
                long createDate = rs.getLong(4);
                long price = rs.getLong(5);
                double discount = rs.getDouble(6);
                Food food = foodType.getType(className, name, expireDate, createDate, price, discount);
                productList.add(food);
            }

        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
        return productList;
    }

    /**
     * saves list into the Data Base
     *
     * @param list - list for saving
     */
    public void writeToDB(List<Food> list) {
        try {
            String query = "INSERT INTO " + this.tableName + " (name , expireDate, createDate, price, discount, type_id) VALUES (?, ?, ?, ?, ?, ?);";
            PreparedStatement ps = this.connection.prepareStatement(query);
            for (Food f : list) {
                ps.setString(1, f.getName());
                ps.setLong(2, f.getExpireDate());
                ps.setLong(3, f.getCreateDate());
                ps.setLong(4, f.getPrice());
                ps.setDouble(5, f.getDiscount());
                ps.setInt(6, f.getTypeId());
                ps.addBatch();
            }
            ps.executeBatch();
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
    }

    /**
     * deletes all data from the table.
     */
    public void deleteFromDB() {
        try {
            String query = "DELETE FROM " + this.tableName;
            PreparedStatement ps = this.connection.prepareStatement(query);
            ps.execute();
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
    }

    /**
     * deletes all data from the table and writes the new list
     *
     * @param list - list for adding to the db
     */
    public void rewriteAll(List<Food> list) {
        deleteFromDB();
        writeToDB(list);
    }
}
