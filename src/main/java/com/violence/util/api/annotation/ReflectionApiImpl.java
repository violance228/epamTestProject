package com.violence.util.api.annotation;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Field;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class ReflectionApiImpl implements ReflectionApi {
    public Object getObject(Class aClass, ResultSet resultSet) {
        Object object = null;
        try {
            Field[] fields = aClass.getDeclaredFields();
            while (resultSet.next())
                object = getObjectFromResultSet(fields, aClass, resultSet);
            return object;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return object;
    }

    public Collection getListObject(Class aClass, ResultSet resultSet) {
        List<Object> objectList = new ArrayList<>();
        try {
            Field[] fields = aClass.getDeclaredFields();
            while (resultSet.next())
                objectList.add(getObjectFromResultSet(fields, aClass, resultSet));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return objectList;
    }

    private Object getObjectFromResultSet(Field[] fields, Class aClass, ResultSet resultSet) {
        Object object = null;
        try {
            object = aClass.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
        Long id = null;
        for (Field field : fields) {
            Column annotation = field.getAnnotation(Column.class);
            if (annotation != null) {
                try {
                    field.setAccessible(true);
                    if (field.getType() == Long.class) {
                        id = resultSet.getLong(annotation.value());
                        field.set(object, resultSet.getLong(annotation.value()));
                    } else if (field.getType() == String.class) {
                        field.set(object, resultSet.getString(annotation.value()));
                    } else if (field.getType() == Boolean.class) {
                        field.set(object, resultSet.getBoolean(annotation.value()));
                    } else if (field.getType() == Date.class) {
                        field.set(object, resultSet.getDate(annotation.value()));
                    } else if (field.getType() == Integer.class) {
                        field.set(object, resultSet.getInt(annotation.value()));
                    } else {
                        field.set(object, getObjectFromResultSet(
                                field.getType().getDeclaredFields(),
                                field.getType(),
                                resultSet)
                        );
                    }
                } catch (IllegalAccessException | SQLException e) {
                    e.printStackTrace();
                }
            }
        } return object;
    }

    public Object getObjectFromRequest(HttpServletRequest request, Class aClass) {
        Object object = null;
        try {
            object = aClass.newInstance();
            Field[] fields = aClass.getDeclaredFields();
            for (Field field : fields) {
                field.setAccessible(true);
                field.set(object, request.getParameter(field.getName()));
            }
            return object;
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        } return object;
    }

//    private Set<Object> getSetFormResultSet(Object t, ResultSet resultSet) {
//        Set<Object> tSet = new HashSet<>();
//        try {
//            while (resultSet.next())
//                tSet.add(getObject(t.getClass(), resultSet));
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return tSet;
//    }

//    private ResultSet getResultSetFromExecuteClassForSet(Class aClass, String searchParam, Long id) {
//        ResultSet resultSet = null;
//        Method[] methods = aClass.getDeclaredMethods();
//        for (Method method : methods) {
//            Column annotation = method.getAnnotation(Column.class);
//            if (annotation != null && annotation.value().contains(searchParam)) {
//                try {
//                    resultSet = (ResultSet) method.invoke(aClass.newInstance(), id);
//                } catch (IllegalAccessException | InvocationTargetException | InstantiationException e) {
//                    e.printStackTrace();
//                }
//            }
//        }
//        return resultSet;
//    }
}
