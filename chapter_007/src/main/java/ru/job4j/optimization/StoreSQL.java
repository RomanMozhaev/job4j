package ru.job4j.optimization;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.job4j.sqlstorage.UsageLog4j2;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StoreSQL implements AutoCloseable {

    private final Config config;
    private Connection connect;
    private static final Logger LOG = LogManager.getLogger(UsageLog4j2.class.getName());

    public StoreSQL(Config config) {
        this.config = config;
    }


    public Connection init() {
        config.init();
        String url = config.get("url");
        String database = config.get("database");
//        String username = config.get("username");
//        String password = config.get("password");
        try {
            this.connect = DriverManager.getConnection(url + database);
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
        return this.connect;
    }

    public void generate(int size) {
        try (Statement st = this.connect.createStatement()) {
            String table = "CREATE TABLE IF NOT EXISTS entry (field int);";
            st.executeUpdate(table);
            PreparedStatement ps = this.connect.prepareStatement("INSERT INTO entry VALUES (?)");
            for (int i = 1; i <= size; i++) {
                ps.setInt(1, i);
                ps.executeUpdate();
            }
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
    }

    public List<XMLUsage.Entry> load() {
        List<XMLUsage.Entry> result = new ArrayList<>();
        try (PreparedStatement ps = this.connect.prepareStatement("SELECT field FROM entry")) {
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {
                result.add(new XMLUsage.Entry(resultSet.getInt(1)));
            }
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
        return result;
    }

    @Override
    public void close() throws Exception {
        if (connect != null) {
            connect.close();
        }
    }
}
