package com.violence.util;

import java.sql.*;

public class DataSourceConn {

    private static final String url = "jdbc:postgresql://localhost:5432/postgres";
    private static final String user = "postgres";
    private static final String password = "postgres";

    public static Connection getPostgreSqlConnection() {
        Connection connection = null;
        try {
            Long start = System.currentTimeMillis();
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(url, user, password);
            System.out.println("connect to DB ----- " + (System.currentTimeMillis() - start));
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return connection;
    }

    public static void close(Statement statement, ResultSet resultSet) {
        try {
            if (resultSet != null)
                resultSet.close();
            if (statement != null)
                statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
