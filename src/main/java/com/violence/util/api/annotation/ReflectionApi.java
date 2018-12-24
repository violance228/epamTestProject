package com.violence.util.api.annotation;

import javax.servlet.http.HttpServletRequest;
import java.sql.ResultSet;
import java.util.Collection;

public interface ReflectionApi {
    Collection getListObject(Class aClass, ResultSet resultSet);
    Object getObject(Class aClass, ResultSet resultSet);
    Object getObjectFromRequest(HttpServletRequest request, Class aClass);
}
