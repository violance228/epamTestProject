package com.violence.util.api.annotation;

import java.sql.ResultSet;
import java.util.Collection;

public interface ColumnAnnotation {
    Collection getListObject(Class aClass, ResultSet resultSet);
    Object getObject(Class aClass, ResultSet resultSet);
}
