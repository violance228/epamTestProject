package com.violence.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataSourceConn {

    private static final String url = "jdbc:postgresql://localhost/dvdrental";
    private static final String user = "postgres";
    private static final String password = "<add your password>";

    public static Connection getPostgreSqlConnection() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(url, user, password);
            System.out.println("connect to DB");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }
}
