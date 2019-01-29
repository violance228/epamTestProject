package com.violence.util.api;

import com.violence.entity.DomainObject;
import com.violence.util.DataSourceConn;
import com.violence.util.api.reflectionApi.ReflectionApi;
import com.violence.util.api.reflectionApi.ReflectionApiImpl;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class EntityAdapterImpl implements EntityAdapter {

    private static final Logger logger = Logger.getLogger(EntityAdapterImpl.class);
    private ReflectionApi reflectionApi = new ReflectionApiImpl();

    public Object getObject(Class aClass, String sql, Long id) {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        Object object = null;
        Connection connection = null;
        try {
            connection = DataSourceConn.getPostgreSqlConnection();
            statement = connection.prepareStatement(sql);
            statement.setLong(1, id);
            resultSet = statement.executeQuery();

            object = reflectionApi.getObject(aClass, resultSet);
        } catch (SQLException e) {
            logger.info("sql exception in method getObject: " + e.getMessage());
        } finally {
            DataSourceConn.close(statement, connection, resultSet);
        }
        return object;
    }

    public Object getObject(Class aClass, String sql) {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        Object object = null;
        Connection connection = null;
        try {
            connection = DataSourceConn.getPostgreSqlConnection();
            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();

            object = reflectionApi.getObject(aClass, resultSet);
        } catch (SQLException e) {
            logger.info("sql exception: in method getObject" + e.getMessage());
        } finally {
            DataSourceConn.close(statement, connection, resultSet);
        }
        return object;
    }

    public Object getObjectFromResultSet(Class aClass, ResultSet resultSet) {
        return reflectionApi.getObject(aClass, resultSet);
    }

    public Object getObject(Class aClass, String sql, Map<Integer, String> params) {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        Object object = null;
        Connection connection = null;
        try {
            connection = DataSourceConn.getPostgreSqlConnection();
            statement = connection.prepareStatement(sql);
            statement = setParamsFromMap(params, statement);
            resultSet = statement.executeQuery();

            object = reflectionApi.getObject(aClass, resultSet);
        } catch (SQLException e) {
            logger.info("sql exception in method getObject: " + e.getMessage());
        } finally {
            DataSourceConn.close(statement, connection, resultSet);
        }
        return object;
    }

    public Collection getListObject(Class aClass, String sql) {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        Connection connection = null;
        Collection collection = null;
        try {
            connection = DataSourceConn.getPostgreSqlConnection();
            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();
            collection = reflectionApi.getListObject(aClass, resultSet);
        } catch (SQLException e) {
            logger.info("sql exception in method getListObject: " + e.getMessage());
        } finally {
            DataSourceConn.close(statement, connection, null);
        }
        return collection;
    }

    public Collection getListObject(Class aClass, String sql, Map<Integer, String> params) {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        Collection collection = null;
        Connection connection = null;
        try {
            connection = DataSourceConn.getPostgreSqlConnection();
            statement = connection.prepareStatement(sql);
            resultSet = setParamsFromMap(params, statement).executeQuery();
            collection =  reflectionApi.getListObject(aClass, resultSet);
        } catch (SQLException e) {
            logger.info("sql exception in method getListObject with string param: " + e.getMessage());
        } finally {
            DataSourceConn.close(statement, connection, resultSet);
        }
        return collection;
    }

    public Collection getListObject(String sql, Class aClass, Map<Integer, Long> params) {
        PreparedStatement statement = null;
        ResultSet rs = null;
        Collection collection = null;
        Connection connection = null;
        try {
            connection = DataSourceConn.getPostgreSqlConnection();
            statement = connection.prepareStatement(sql);
            rs = setParamsFromMap(statement, params).executeQuery();
            collection = reflectionApi.getListObject(aClass, rs);
        } catch (SQLException e) {
            logger.info("sql exception in method getListObject with integer param: " + e.getMessage());
        } finally {
            DataSourceConn.close(statement, connection, rs);
        }
        return collection;
    }

    public ResultSet getResultSet(String sql) {
        ResultSet resultSet = null;
        Connection connection = DataSourceConn.getPostgreSqlConnection();
        try {
            resultSet = connection.prepareStatement(sql).executeQuery();
        } catch (SQLException e) {
            logger.info("sql exception: " + e.getMessage());
        } finally {
            DataSourceConn.close(null, connection, resultSet);
        }
        return resultSet;
    }

    public void insert(Object o) {
        Connection connection = DataSourceConn.getPostgreSqlConnection();
        try {
            connection.setAutoCommit(false);
            String sql = "INSERT INTO " + reflectionApi.getTableNameByClass(o.getClass()) +
                    " ( " + reflectionApi.getObjectFieldsName(o) + " ) " +
                    " VALUES ( " + reflectionApi.getObjectFieldsValueForInsert(o) + " );";
            connection.prepareStatement(sql).execute();
            connection.commit();
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException e1) {
                logger.info("can't rollback save: " + e1.getMessage());
            }
            logger.info("sql exception when save object: " + e.getMessage());
        } finally {
            try {
                if (!connection.isClosed()) {
                    connection.close();
                }
            } catch (SQLException e) {
                logger.info("can't close connection" + e.getMessage());
            }
        }
    }

    public void update(Object object) {
        PreparedStatement statement = null;
        Connection connection = DataSourceConn.getPostgreSqlConnection();
        String sql = "";
        try {
            connection.setAutoCommit(false);
            sql = "UPDATE " + reflectionApi.getTableNameByClass(object.getClass()) +
                    " SET " + reflectionApi.getObjectFieldsValueForUpdate(object) +
                    " WHERE " + reflectionApi.getColumnIdName(object) + " = ?";
            statement = connection.prepareStatement(sql);
            statement.setLong(1, reflectionApi.getColumnIdValue(object));
            statement.executeUpdate();
            connection.commit();
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException e1) {
                logger.info("can't rollback update: "+ sql + e1.getMessage());
            }
            logger.info("sql exception when update object: " + e.getMessage());
        } finally {
            DataSourceConn.close(statement, connection,null);
        }
    }

    private PreparedStatement setParamsFromMap(Map<Integer, String> params, PreparedStatement statement) throws SQLException {
        for (Map.Entry<Integer, String> param : params.entrySet())
            statement.setString(param.getKey(), param.getValue());

        return statement;
    }

    private PreparedStatement setParamsFromMap(PreparedStatement statement, Map<Integer, Long> params) throws SQLException {
        for (Map.Entry<Integer, Long> param : params.entrySet())
            statement.setLong(param.getKey(), param.getValue());

        return statement;
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
        result.append("(");
        if (object instanceof DomainObject) {
            result.append(" ,");
        }
        return result.delete(result.length() - 2, result.length()).append(")").toString();
    }
}
