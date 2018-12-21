package com.violence.util.api;

import com.violence.entity.Author;

import java.sql.ResultSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * created by user violence
 * created on 21.12.2018
 * class created for project SecurityExample
 */

public interface EntityAdapter {
    <T> T getObjectFromResultSet(T t, ResultSet resultSet);
    <T> T getObject(T t, String sql, Long id);
    <T> T getObject(T t, String sql, Map<Integer, String> params);
    <T> List<T> getListObject(T t, String sql);
    <T> List<T> getListObject(T t, String sql, Map<Integer, String> params);
    <T> Set<T> getSetObjectFromResultSet(T t, String fieldName, Long id, ResultSet resultSet);
    void insert(String sql);
    <T> String prepareObjectToInsert(List<T> authors);
}
