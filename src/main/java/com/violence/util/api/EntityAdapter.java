package com.violence.util.api;

import java.sql.ResultSet;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * created by user violence
 * created on 21.12.2018
 * class created for project SecurityExample
 */

public interface EntityAdapter {
    Object getObjectFromResultSet(Class aClass, ResultSet resultSet);
    Object getObject(Class aClass, String sql, Long id);
    Object getObject(Class aClass, String sql, Map<Integer, String> params);
    Collection getListObject(Class aClass, String sql);
    ResultSet getResultSet(String sql);
    List<Object> getListObject(Class aClass, String sql, Map<Integer, String> params);
    Set<Object> getSetObjectFromResultSet(Class aClass, String fieldName, Long id, ResultSet resultSet);
    void insert(String sql);
    void update(String sql, Long id);
    <T> String prepareObjectToInsert(List<T> authors);
}
