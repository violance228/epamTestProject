package com.violence.util.api.reflectionApi;

import javax.servlet.http.HttpServletRequest;
import java.sql.ResultSet;
import java.util.Collection;

public interface ReflectionApi {
    Collection getListObject(Class aClass, ResultSet resultSet);
    Object getObject(Class aClass, ResultSet resultSet);
    Object getObjectFromRequest(HttpServletRequest request, Class aClass);
    <T> String getObjectFieldsName(T object);
    <T> String getObjectFieldsValueForInsert(T object);
    <T> String getObjectFieldsValueForUpdate(T object);
    String getColumnIdName(Object object);
    Long getColumnIdValue(Object object);
    String getTableNameByClass(Class aClass);
}
