package com.example.restapi.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionImplementation {
    static Connection connection = null;
    private ConnectionImplementation() {}

    public static Connection getIConnection() {
        if(connection == null) {
            try {
                Class.forName(ConnectionRawDetails.DRIVER);
                connection = DriverManager.getConnection(ConnectionRawDetails.URL,ConnectionRawDetails.USERNAME,ConnectionRawDetails.PASSWORD);
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return connection;
    }
}