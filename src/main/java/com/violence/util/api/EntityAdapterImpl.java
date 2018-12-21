package com.violence.util.api;

import com.sun.rowset.JdbcRowSetImpl;
import com.violence.entity.DomainObject;
import com.violence.util.DataSourceConn;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class EntityAdapterImpl implements EntityAdapter {

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

    public <T> T getObjectFromResultSet(T t, ResultSet resultSet) {
        if (t instanceof DomainObject) {
            try {
                while (resultSet.next())
                    return (T) ((DomainObject) t).getObject(resultSet);
                return null;
            } catch (SQLException e) {
                e.printStackTrace();
                return null;
            }
        } else
            return null;
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

    public void insert(String sql) {
        try {
            DataSourceConn.getPostgreSqlConnection().prepareStatement(sql).execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private PreparedStatement setParamsFromMap(Map<Integer, String> params, PreparedStatement statement) throws SQLException {
        for (Map.Entry<Integer, String> param : params.entrySet())
            statement.setString(param.getKey(), param.getValue());

        return statement;
    }

    public <T> Set<T> getSetObjectFromResultSet(T t, String fieldName, Long id, ResultSet resultSet) {
        Set<T> result = new HashSet<>();
        if (t instanceof DomainObject) {
            try {
                if (resultSet != null) {
                    ResultSet set = new JdbcRowSetImpl(resultSet);
                    while (set.next()) {
                        if (set.getString(fieldName) != null && set.getLong(fieldName) == id) {
                            result.add(getObjectFromResultSet(t, set));
                        }
                    }
                }
                return result;
            } catch (SQLException e) {
                e.printStackTrace();
                return result;
            }
        }
        return result;
    }

    public  <T> String prepareObjectToInsert(List<T> ts) {
        StringBuilder result = new StringBuilder();
        for (T t : ts) {
            result.append(t.toString());
            result.append(" ,");
        }
        return result.delete(result.length() - 2, result.length()).append(";").toString();
    }
}
