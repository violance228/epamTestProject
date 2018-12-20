package com.violence.util.api;

import com.violence.entity.DomainObject;
import com.violence.util.DataSourceConn;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class EntityAdapter {

    private <T> List<T> getListObjectFromResultSet(T t, ResultSet resultSet) throws SQLException {
        List<T> result = new ArrayList<>();
        if (t instanceof DomainObject) {
            while (resultSet.next())
                result.add((T) ((DomainObject) t).getObject(resultSet));

            return result;
        } else {
            return null;
        }
    }

    private <T> T getObjectFromResultSet(T t, ResultSet resultSet) throws SQLException {
        if (t instanceof DomainObject) {
            while (resultSet.next())
                return (T) ((DomainObject) t).getObject(resultSet);
            return null;
        } else {
            return null;
        }
    }

    public <T> T getObject(T t, String sql, Long id) {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = DataSourceConn.getPostgreSqlConnection().prepareStatement(sql);
            statement.setLong(1, id);
            resultSet = statement.executeQuery();

            return getObjectFromResultSet(t, resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public <T> T getObject(T t, String sql, Map<Integer, String> params) {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = DataSourceConn.getPostgreSqlConnection().prepareStatement(sql);
            statement = setParamsFromMap(params, statement);
            resultSet = statement.executeQuery();

            return getObjectFromResultSet(t, resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public <T> List<T> getListObject(T t, String sql) {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = DataSourceConn.getPostgreSqlConnection().prepareStatement(sql);
            resultSet = statement.executeQuery();
            return getListObjectFromResultSet(t, resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public <T> List<T> getListObject(T t, String sql, Map<Integer, String> params) {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = DataSourceConn.getPostgreSqlConnection().prepareStatement(sql);
            statement = setParamsFromMap(params, statement);
            resultSet = statement.executeQuery();
            return getListObjectFromResultSet(t, resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public PreparedStatement setParamsFromMap(Map<Integer, String> params, PreparedStatement statement) throws SQLException {
        for (Map.Entry<Integer, String> param : params.entrySet())
            statement.setString(param.getKey(), param.getValue());

        return statement;
    }
}
