package com.violence.util.api.annotation;

import java.lang.reflect.Field;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

public class ColumnAnnotationImpl implements ColumnAnnotation {
    public Object getObject(Class aClass, ResultSet resultSet) {
        Object object = null;
        try {
            object = aClass.newInstance();
            Field[] fields = aClass.getDeclaredFields();
            while (resultSet.next())
                object = getObjectFromResultSet(fields, object, resultSet);

            return object;
        } catch (InstantiationException | IllegalAccessException | SQLException e) {
            e.printStackTrace();
        }
        return object;
    }

    public Collection getListObject(Class aClass, ResultSet resultSet) {
        List<Object> objectList = new ArrayList<>();
        Object object = null;
        try {
            object = aClass.newInstance();
            Field[] fields = aClass.getDeclaredFields();
            while (resultSet.next()) {
                objectList.add(getObjectFromResultSet(fields, object, resultSet));
            }
            return objectList;
        } catch (IllegalAccessException | InstantiationException | SQLException e) {
            e.printStackTrace();
        }
        return objectList;
    }

    private Object getObjectFromResultSet(Field[] fields, Object object, ResultSet resultSet) {
        for (Field field : fields) {
            Column annotation = field.getAnnotation(Column.class);
            if (annotation != null) {
                try {
                    field.setAccessible(true);
                    if (field.getType() == Long.class) {
                        field.set(object, resultSet.getLong(annotation.value()));
                    } else if (field.getType() == String.class) {
                        field.set(object, resultSet.getString(annotation.value()));
                    } else if (field.getType() == Boolean.class) {
                        field.set(object, resultSet.getBoolean(annotation.value()));
                    } else if (field.getType() == Date.class) {
                        field.set(object, resultSet.getDate(annotation.value()));
                    } else {
                        field.set(object, getObjectFromResultSet(
                                field.getType().getDeclaredFields(),
                                field.getType().newInstance(),
                                resultSet)
                        );
                    }
                } catch (IllegalAccessException | SQLException | InstantiationException e) {
                    e.printStackTrace();
                }
            }
        }
        return object;
    }
}
