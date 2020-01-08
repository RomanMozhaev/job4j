package ru.job4j.firsthttp;

import org.apache.commons.dbcp2.BasicDataSource;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

/**
 * the class for working with Postgres data base
 */
public class DBStore implements Store {
    /**
     * the logger
     */
    private final static Logger LOG = LogManager.getLogger(DBStore.class.getName());
    /**
     * the connections' pool
     */
    private static final BasicDataSource SOURCE = new BasicDataSource();
    /**
     * the singleton instance
     */
    private static final DBStore INSTANCE = new DBStore();

    /**
     * the main constructor.
     * the properties of the database are set.
     */
    private DBStore() {
        SOURCE.setUrl("jdbc:postgresql://localhost:5432/server");
        SOURCE.setUsername("postgres");
        SOURCE.setPassword("password");
        SOURCE.setMinIdle(5);
        SOURCE.setMaxIdle(10);
        SOURCE.setMaxOpenPreparedStatements(100);
        SOURCE.setDriverClassName("org.postgresql.Driver");
    }

    /**
     * getter of the instance.
     * @return the instance.
     */
    public static DBStore getInstance() {
        return INSTANCE;
    }

    /**
     * returns the map with all users. Reads data from database.
     * @return the map.
     */
    @Override
    public Map<Integer, User> findAll() {
        Map<Integer, User> users = new HashMap<>();
        String query = "SELECT * FROM users";
        try (Connection connection = SOURCE.getConnection();
             Statement st = connection.createStatement()
        ) {
            ResultSet set = st.executeQuery(query);
            while (set.next()) {
                int id = set.getInt("user_id");
                String name = set.getString("user_name");
                String email = set.getString("user_email");
                long date = set.getLong("create_time");
                users.put(id, new User(id, name, email, date));
            }
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
        return users;
    }

    /**
     * adds user to data base.
     * @param user the new user.
     * @return - true if the user was added; otherwise false.
     */
    @Override
    public boolean add(User user) {
        boolean result = false;
        String query = "INSERT INTO users (user_name, user_email, create_time) VALUES (?, ?, ?)";
        try (Connection connection = SOURCE.getConnection();
             PreparedStatement st = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
        ) {
            st.setString(1, user.getName());
            st.setString(2, user.getEmail());
            st.setLong(3, user.getCreateDate());
            st.executeUpdate();
            ResultSet set = st.getGeneratedKeys();
            if (set.next()) {
                result = true;
            }
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
        return result;
    }

    /**
     * deletes user from the data base. only user id is used.
     * @param user - the user for deleting.
     * @return true if the user was deleted; otherwise false.
     */
    @Override
    public boolean delete(User user) {
        boolean result = false;
        String query = "DELETE FROM users WHERE user_id = ?";
        try (Connection connection = SOURCE.getConnection();
             PreparedStatement st = connection.prepareStatement(query)) {
            st.setInt(1, user.getId());
            int rows = st.executeUpdate();
            if (rows > 0) {
                result = true;
            }
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
        return result;
    }

    /**
     * updates the fields of specified user.
     * @param user - the user with field for updating.
     * @return true if the user was updated; otherwise false.
     */
    @Override
    public boolean update(User user) {
        boolean result = false;
        String query = "UPDATE users SET (user_name, user_email) = (?, ?) WHERE user_id = ?";
        try (Connection connection = SOURCE.getConnection();
             PreparedStatement st = connection.prepareStatement(query)) {
            st.setString(1, user.getName());
            st.setString(2, user.getEmail());
            st.setInt(3, user.getId());
            int rows = st.executeUpdate();
            if (rows > 0) {
                result = true;
            }
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
        return result;
    }

    /**
     * finds the user by id.
     * @param id - the user id
     * @return User instance or null.
     */
    @Override
    public User findById(int id) {
        User user = null;
        String query = "SELECT * FROM users WHERE user_id = ?";
        try (Connection connection = SOURCE.getConnection();
             PreparedStatement st = connection.prepareStatement(query)
        ) {
            st.setInt(1, id);
            ResultSet set = st.executeQuery();
            if (set.next()) {
                int userId = set.getInt("user_id");
                String name = set.getString("user_name");
                String email = set.getString("user_email");
                long date = set.getLong("create_time");
                user = new User(userId, name, email, date);
            }

        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
        return user;
    }
}