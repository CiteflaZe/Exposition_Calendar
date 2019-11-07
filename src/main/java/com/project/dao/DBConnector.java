package com.project.dao;

import com.project.exception.DataBaseRuntimeException;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class DBConnector {
    private static final Logger LOGGER = Logger.getLogger(DBConnector.class);
    private final String url;
    private final String user;
    private final String password;

    public DBConnector(String configFileName){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        ResourceBundle bundle = ResourceBundle.getBundle(configFileName);
        this.url = bundle.getString("db.url");
        this.user = bundle.getString("db.user");
        this.password = bundle.getString("db.password");
    }

    public Connection getConnection(){
        try {
            return DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            LOGGER.error("Connection was not established");
            throw new DataBaseRuntimeException(e);
        }
    }
}
