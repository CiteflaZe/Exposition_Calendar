package com.project;

import com.project.dao.DBConnector;

import java.sql.Connection;

public class Application {
    public static void main(String[] args) {
        DBConnector DBConnector = new DBConnector("database");

        Connection connection = DBConnector.getConnection();

    }
}
