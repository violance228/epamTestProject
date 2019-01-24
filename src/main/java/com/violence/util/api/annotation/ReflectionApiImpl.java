package com.violence.util.api.annotation;

import com.violence.entity.DomainObject;
import com.violence.util.Utils;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Field;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
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
                if(request.getParameter(field.getName()) != null) {
                    setFieldFromRequest(field, object, request.getParameter(field.getName()));
                }
            }
            return object;
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        } return object;
    }

    private void setFieldFromRequest(Field field, Object object, Object param) {
        try {
            if (field.getType() == Long.class) {
                field.setAccessible(true);
                field.set(object, Long.valueOf(param.toString()));
            } else if(field.getType() == String.class) {
                field.setAccessible(true);
                field.set(object, String.valueOf(param));
            } else if (field.getType() == Date.class) {
                field.setAccessible(true);
                field.set(object, Utils.dateFromHtml.parse(String.valueOf(param)));
            } else if (field.getType() == Integer.class) {
                field.setAccessible(true);
                field.set(object, Integer.valueOf(param.toString()));
            } else {
                field.set(object, null);
            }
        } catch (IllegalAccessException | ParseException e) {
            e.printStackTrace();
        }
    }

    public <T> String getObjectFieldsName(T object) {
        StringBuilder stringBuilder = new StringBuilder();
        Field[] fields = object.getClass().getDeclaredFields();
        for (Field field : fields) {
            Column annotation = field.getAnnotation(Column.class);
            if (annotation != null) {
                stringBuilder.append(annotation.value());
                stringBuilder.append(",");
            }
        }
        return stringBuilder.delete(stringBuilder.length()-1, stringBuilder.length()).toString();
    }

    public <T> String getObjectFieldsValue(T object) {
        StringBuilder stringBuilder = new StringBuilder();
        Field[] fields = object.getClass().getDeclaredFields();
        for (Field field : fields) {
            if (field.getAnnotation(Column.class) != null) {
                stringBuilder.append("\'");
                stringBuilder.append(getFieldValue(field, object));
                stringBuilder.append("\'");
                stringBuilder.append(",");
            }
        }
        return stringBuilder.delete(stringBuilder.length()-1, stringBuilder.length()).toString();
    }

    private <T> String getFieldValue(Field field, Object object) {
        String result = "";
        field.setAccessible(true);
        try {
            if (field.getType() ==  Long.class) {
                result = String.valueOf(field.get(object));
            } else if (field.getType() ==  String.class) {
                result = field.get(object).toString();
            } else if (field.getType() == Integer.class){
                result = String.valueOf(field.get(object));
            } else if(field.getType() == Date.class) {
                result = String.valueOf(field.get(object));
            } else if(field.getType() == Boolean.class) {
                result = String.valueOf(field.get(object));
            } else {
                Object o = field.get(object);
                Field[] fields = o.getClass().getDeclaredFields();
                for (Field objField : fields) {
                    if (objField.getName().contains("id")) {
                        objField.setAccessible(true);
                        result = String.valueOf(objField.get(o));
                    }
                }
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        field.setAccessible(false);
        return result;
    }

    public String getTableNameByClass(Class aClass) {
        Table table = (Table) aClass.getAnnotation(Table.class);
        return table != null ? table.tableName() : "";
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
