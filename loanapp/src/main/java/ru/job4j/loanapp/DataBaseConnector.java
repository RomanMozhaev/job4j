package ru.job4j.loanapp;

import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Map;

public class DataBaseConnector implements Connector {
    /**
     * the logger.
     */
    private final static Logger LOG = LogManager.getLogger(DataBaseConnector.class.getName());
    /**
     * the connections pool.
     */
    private static final BasicDataSource SOURCE = new BasicDataSource();
    /**
     * the DBConnector class instance.
     */
    private static final DataBaseConnector INSTANCE = new DataBaseConnector();

    /**
     * the main constructor. The connection properties are set.
     */
    private DataBaseConnector() {
        SOURCE.setUrl("jdbc:postgresql://localhost:5432/tracker");
        SOURCE.setUsername("postgres");
        SOURCE.setPassword("password");
        SOURCE.setMinIdle(5);
        SOURCE.setMaxIdle(10);
        SOURCE.setMaxOpenPreparedStatements(100);
        SOURCE.setDriverClassName("org.postgresql.Driver");
    }

    /**
     * the getter of the DBConnector instance.
     *
     * @return - the instance.
     */
    public static DataBaseConnector getInstance() {
        return INSTANCE;
    }

    /**
     * the method for adding new applications to database
     * @param map - the map of the new apps divided into groups by countries.
     */
    @Override
    public void addNewApps(Map<String, HashSet<LoanApplication>> map) {
        String query = "insert into applications (first_name, second_name, pid, term, sum, country) values (?, ?, ?, ?, ?, ?);";
        Connection connection = null;
        PreparedStatement st = null;
        try {
            connection = SOURCE.getConnection();
            connection.setAutoCommit(false);
            st = connection.prepareStatement(query);
            for (Map.Entry<String, HashSet<LoanApplication>> entry : map.entrySet()) {
                for (LoanApplication app : entry.getValue()) {
                    st.setString(1, app.getName());
                    st.setString(2, app.getSurname());
                    st.setInt(3, app.getPid());
                    st.setInt(4, app.getTerm());
                    st.setInt(5, app.getSum());
                    st.setString(6, app.getCountry());
                    st.addBatch();
                }
            }
            st.executeBatch();
            connection.commit();
        } catch (Exception e) {
            if (connection != null) {
                try {
                    connection.rollback();
                } catch (SQLException sqlEx) {
                    LOG.error(sqlEx.getMessage(), sqlEx);
                }
            }
        } finally {
            try {
                if (st != null) {
                    st.close();
                }
                if (connection != null) {
                    connection.setAutoCommit(true);
                    connection.close();
                }
            } catch (Exception e) {
                LOG.error(e.getMessage(), e);
            }
        }
    }

    /**
     * the method for checking pid in black list.
     * @param pid - personal id
     * @return true if the pid in the list.
     */
    @Override
    public boolean findInBlackList(int pid) {
        boolean result = false;
        String query = "select * from blacklist where pid = ?";
        try (Connection connection = SOURCE.getConnection();
             PreparedStatement st = connection.prepareStatement(query)) {
            st.setInt(1, pid);
            st.executeQuery();
            ResultSet set = st.getResultSet();
            if (set.next()) {
                result = true;
            }
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
        return result;
    }

    /**
     * the method returns all applications from database tied to the required pid.
     * @param pid - personal id.
     * @return the array list with loan applications.
     */
    @Override
    public ArrayList<LoanApplication> findAllByPID(int pid) {
        ArrayList<LoanApplication> result = new ArrayList<>();
        String query = "select * from applications where pid = ?;";
        try (Connection connection = SOURCE.getConnection();
             PreparedStatement st = connection.prepareStatement(query)) {
            st.setInt(1, pid);
            st.executeQuery();
            ResultSet set = st.getResultSet();
            while (set.next()) {
                result.add(new LoanApplication(
                        set.getString("country"),
                        set.getString("first_name"),
                        set.getString("second_name"),
                        set.getInt("pid"),
                        set.getInt("term"),
                        set.getInt("sum")));
            }
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
        return result;
    }

    /**
     * returns all loan applications which were added to the database.
     * @return the array list with loan applications.
     */
    @Override
    public ArrayList<LoanApplication> findAll() {
        ArrayList<LoanApplication> result = new ArrayList<>();
        String query = "select * from applications;";
        try (Connection connection = SOURCE.getConnection();
             PreparedStatement st = connection.prepareStatement(query)) {
            st.executeQuery();
            ResultSet set = st.getResultSet();
            while (set.next()) {
                result.add(new LoanApplication(
                        set.getString("country"),
                        set.getString("first_name"),
                        set.getString("second_name"),
                        set.getInt("pid"),
                        set.getInt("term"),
                        set.getInt("sum")
                ));
            }
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
        return result;
    }
}
