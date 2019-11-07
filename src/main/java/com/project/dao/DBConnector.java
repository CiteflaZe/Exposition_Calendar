package com.project.dao;

import com.project.exception.DataBaseRuntimeException;
import org.apache.commons.dbcp.BasicDataSource;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class DBConnector {
    private static final Logger LOGGER = Logger.getLogger(DBConnector.class);

    private static final BasicDataSource dataSource = new BasicDataSource();

    public DBConnector(String configFileName) {
        ResourceBundle bundle = ResourceBundle.getBundle(configFileName);

        dataSource.setDriverClassName(bundle.getString("db.driver"));
        dataSource.setUrl(bundle.getString("db.url"));
        dataSource.setUsername(bundle.getString("db.user"));
        dataSource.setPassword(bundle.getString("db.password"));
        dataSource.setMinIdle(Integer.parseInt(bundle.getString("db.minIdle")));
        dataSource.setMaxIdle(Integer.parseInt(bundle.getString("db.maxIdle")));
        dataSource.setMaxOpenPreparedStatements(Integer.parseInt(bundle.getString("db.maxOpenPreparedStatements")));
    }

    public Connection getConnection() {
        try {
            return dataSource.getConnection();
        } catch (SQLException e) {
            LOGGER.error("Connection was not established", e);
            throw new DataBaseRuntimeException("Connection not established", e);
        }
    }
}
