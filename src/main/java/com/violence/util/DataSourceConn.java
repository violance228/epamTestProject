package com.violence.util;

import org.apache.log4j.Logger;

import java.sql.*;

public class DataSourceConn {

    private static final Logger logger = Logger.getLogger(DataSourceConn.class);
    private static final String url = "jdbc:postgresql://localhost:5432/library";
    private static final String user = "postgres";
    private static final String password = "postgres";

    public static Connection getPostgreSqlConnection() {
        Connection connection = null;
        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(url, user, password);
        } catch (SQLException | ClassNotFoundException e) {
            logger.info("can't connect into dataBase, " + e.getCause());
        }
        return connection;
    }

    public static void close(Statement statement, Connection connection,ResultSet resultSet) {
        try {
            if (resultSet != null)
                resultSet.close();
            if (connection != null)
                connection.close();
            if (statement != null)
                statement.close();
        } catch (SQLException e) {
            logger.info("can't close connection into dataBase, " + e.getCause());
        }
    }
}
