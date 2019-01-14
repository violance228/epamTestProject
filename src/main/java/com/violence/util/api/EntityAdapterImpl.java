package com.violence.util.api;

import com.sun.rowset.JdbcRowSetImpl;
import com.violence.util.DataSourceConn;
import com.violence.util.api.annotation.ReflectionApi;
import com.violence.util.api.annotation.ReflectionApiImpl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class EntityAdapterImpl implements EntityAdapter {

    private ReflectionApi reflectionApi = new ReflectionApiImpl();

//    private List<Object> getListObjectFromResultSet(Object o, ResultSet resultSet) throws SQLException {
//        List<Object> result = new ArrayList<>();
//        if (o instanceof DomainObject) {
//            while (resultSet.next())
//                result.add(((DomainObject) o).getObject(resultSet));
//            return result;
//        } else {
//            return null;
//        }
//    }

    public Object getObject(Class aClass, String sql, Long id) {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = DataSourceConn.getPostgreSqlConnection().prepareStatement(sql);
            statement.setLong(1, id);
            resultSet = statement.executeQuery();

            return reflectionApi.getObject(aClass, resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        } finally {
            DataSourceConn.close(statement, resultSet);
        }
    }

    public Object getObject(Class aClass, String sql) {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = DataSourceConn.getPostgreSqlConnection().prepareStatement(sql);
            resultSet = statement.executeQuery();

            return reflectionApi.getObject(aClass, resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        } finally {
            DataSourceConn.close(statement, resultSet);
        }
    }

    public Object getObjectFromResultSet(Class aClass, ResultSet resultSet) {
        return reflectionApi.getObject(aClass, resultSet);
    }

    public Object getObject(Class aClass, String sql, Map<Integer, String> params) {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = DataSourceConn.getPostgreSqlConnection().prepareStatement(sql);
            statement = setParamsFromMap(params, statement);
            resultSet = statement.executeQuery();

            return reflectionApi.getObject(aClass, resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        } finally {
            DataSourceConn.close(statement, resultSet);
        }
    }

    public Collection getListObject(Class aClass, String sql) {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = DataSourceConn.getPostgreSqlConnection().prepareStatement(sql);
            resultSet = statement.executeQuery();
            return reflectionApi.getListObject(aClass, resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<Object> getListObject(Class aClass, String sql, Map<Integer, String> params) {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = DataSourceConn.getPostgreSqlConnection().prepareStatement(sql);
            resultSet = setParamsFromMap(params, statement).executeQuery();
            return (List<Object>) reflectionApi.getListObject(aClass, resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        } finally {
            DataSourceConn.close(statement, resultSet);
        }
    }

    public ResultSet getResultSet(String sql) {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            resultSet = DataSourceConn.getPostgreSqlConnection().prepareStatement(sql).executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultSet;
    }

    public void insert(String sql) {
        try {

            DataSourceConn.getPostgreSqlConnection().prepareStatement(sql).execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void update(String sql, Long id) {
        PreparedStatement statement = null;
        try {
            statement = DataSourceConn.getPostgreSqlConnection().prepareStatement(sql);
            statement.setLong(1, id);
            statement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (statement != null)
                    statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    private PreparedStatement setParamsFromMap(Map<Integer, String> params, PreparedStatement statement) throws SQLException {
        for (Map.Entry<Integer, String> param : params.entrySet())
            statement.setString(param.getKey(), param.getValue());

        return statement;
    }

    public Set<Object> getSetObjectFromResultSet(Class aClass, String fieldName, Long id, ResultSet resultSet) {
        Set<Object> result = new HashSet<>();
        try {
            if (resultSet != null) {
                ResultSet set = new JdbcRowSetImpl(resultSet);
                while (set.next()) {
                    if (set.getString(fieldName) != null && set.getLong(fieldName) == id) {
                        result.add(reflectionApi.getObject(aClass, set));
                    }
                }
            }
            return result;
        } catch (SQLException e) {
            e.printStackTrace();
            return result;
        }
    }

    public <T> String prepareObjectToInsert(List<T> ts) {
        StringBuilder result = new StringBuilder();
        for (T t : ts) {
            result.append(t.toString());
            result.append(" ,");
        }
        return result.delete(result.length() - 2, result.length()).append(";").toString();
    }

    public <T> String prepareObjectToInsert(T object) {
        StringBuilder result = new StringBuilder();
        result.append(object.toString());
        result.append(" ,");
        return result.delete(result.length() - 2, result.length()).append(";").toString();
    }

//    private Object gee(Class aClass) {
//        try {
//            Object object = aClass.newInstance();
//            Field[] field =aClass.getFields();
//            Method[] methods = aClass.getMethods();
//        } catch (InstantiationException | IllegalAccessException e) {
//            e.printStackTrace();
//        }
//    }
}
