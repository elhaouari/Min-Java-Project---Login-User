package com.min.dao;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

/**
 *
 * @author PCName
 */
public final class DAOFactory {

    private final String db_url;
    private final String db_user;
    private final String db_pass;

    private static final String FILE_PROPERTIES = "com/min/dao/dao.properties";
    private static final String PROPERTY_URL = "DB_URL";
    private static final String PROPERTY_DRIVER = "DB_DRIVER";
    private static final String PROPERTY_USER = "DB_USER";
    private static final String PROPERTY_PASS = "DB_PASS";

    private static DAOFactory instance;
    private static Connection connection;

    private DAOFactory(String url, String user, String pass) throws SQLException {
        this.db_url = url;
        this.db_user = user;
        this.db_pass = pass;

        connection = getConnection();
    }

    /**
     * get instance of DAOFactory
     *
     * @return
     * @throws java.lang.Exception
     */
    public static DAOFactory getInstance() throws Exception {
        if (instance == null) {
            Properties properties = new Properties();
            String url;
            String driver;
            String user;
            String pass;

            // load file of config
            ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
            InputStream fileProperties = classLoader.getResourceAsStream(FILE_PROPERTIES);

            if (fileProperties == null) {
                throw new Exception("The properties file " + FILE_PROPERTIES + " is not found.");
            }

            try {
                // read configuration from the file property
                properties.load(fileProperties);
                driver = properties.getProperty(PROPERTY_DRIVER);
                url = properties.getProperty(PROPERTY_URL);
                user = properties.getProperty(PROPERTY_USER);
                pass = properties.getProperty(PROPERTY_PASS);
            } catch (IOException e) {
                throw new Exception("Unable to load properties file " + FILE_PROPERTIES, e);
            }

            try {
                Class.forName(driver);
            } catch (ClassNotFoundException e) {
                throw new Exception("The driver was not found in the classpath.", e);
            }

            instance = new DAOFactory(url, user, pass);
        }
        return instance;
    }

    /**
     * get connection object
     *
     * @return
     * @throws SQLException
     */
    public Connection getConnection() throws SQLException {
        if (connection == null) {
            connection = DriverManager.getConnection(db_url, db_user, db_pass);
        }
        return connection;
    }

    /**
     * close connection of database
     * @throws SQLException 
     */
    public void close() throws SQLException{
        connection.close();
    }
    
    /**
     * Query SELECT
     *
     * @param sql
     * @param params
     * @return
     * @throws SQLException
     */
    public ResultSet select(String sql, Object... params) throws SQLException {

        PreparedStatement stat = connection.prepareStatement(sql);
        for (int i = 0; i < params.length; i++) {
            stat.setObject(i + 1, params[i]);
        }

        return stat.executeQuery();
    }

    /**
     * Query INSERT|UPDATE|DELETE
     *
     * @param sql
     * @param params
     * @return
     * @throws SQLException
     */
    public int update(String sql, Object... params) throws SQLException {

        PreparedStatement stat = connection.prepareStatement(sql);
        for (int i = 0; i < params.length; i++) {
            stat.setObject(i + 1, params[i]);
        }

        return stat.executeUpdate();
    }

}
