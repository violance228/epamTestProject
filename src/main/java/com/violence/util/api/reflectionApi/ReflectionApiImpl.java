package com.violence.util.api.reflectionApi;

import com.violence.util.Utils;
import com.violence.util.api.annotation.Column;
import com.violence.util.api.annotation.Id;
import com.violence.util.api.annotation.Table;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Field;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.*;

public class ReflectionApiImpl implements ReflectionApi {

    private static final Logger logger = Logger.getLogger(ReflectionApiImpl.class);


    /**
     *
     * @param aClass
     * @param resultSet
     * @return object class transmitted first param
     */

    public Object getObject(Class aClass, ResultSet resultSet) {
        Object object = null;
        try {
            Field[] fields = aClass.getDeclaredFields();
            while (resultSet.next())
                object = getObjectFromResultSet(fields, aClass, resultSet);
            return object;
        } catch (SQLException e) {
            logger.info("sql exception in method getObject reflApi: " + e.getMessage());
        }
        return object;
    }

    /**
     * @param aClass
     * @param resultSet
     * @return collection objects transmitted first param
     */

    public Collection getListObject(Class aClass, ResultSet resultSet) {
        List<Object> objectList = new ArrayList<>();
        try {
            Field[] fields = aClass.getDeclaredFields();
            while (resultSet.next())
                objectList.add(getObjectFromResultSet(fields, aClass, resultSet));
        } catch (SQLException e) {
            logger.info("sql exception in method getListObject reflApi: " + e.getMessage());
        }
        return objectList;
    }

    /**
     * @param fields
     * @param aClass
     * @param resultSet
     * @return loop resultSet and fills in fields object class transmitted second param, and return finished obj
     */

    private Object getObjectFromResultSet(Field[] fields, Class aClass, ResultSet resultSet) {
        Object object = null;
        try {
            object = aClass.newInstance();
            for (Field field : fields) {
                Column annotation = field.getAnnotation(Column.class);
                if (annotation != null) {
                    field.setAccessible(true);
                    if (field.getType() == Long.class) {
                        field.set(object, resultSet.getLong(annotation.value()));
                    } else if (field.getType() == String.class) {
                        field.set(object, resultSet.getString(annotation.value()));
                    } else if (field.getType() == Boolean.class) {
                        field.set(object, resultSet.getBoolean(annotation.value()));
                    } else if (field.getType() == Date.class) {
                        field.set(object, resultSet.getDate(annotation.value()));
                    } else if (field.getType() == Integer.class) {
                        field.set(object, resultSet.getInt(annotation.value()));
                    } else if (field.getType() == int.class) {
                        field.set(object, resultSet.getInt(annotation.value()));
                    } else {
                        field.set(object, getObjectFromResultSet(
                                field.getType().getDeclaredFields(),
                                field.getType(),
                                resultSet)
                        );
                    }
                } else if (field.getType() == Integer.class) {
                    field.set(object, resultSet.getInt("count"));
                }
            }
        } catch (IllegalAccessException | SQLException | InstantiationException e) {
            logger.info("exception in method getObjectFromResultSet reflApi: " + e.getMessage());
        }
        return object;
    }

    /**
     * @param request, param in request must named like fields in class
     * @param aClass
     * @return in loop fill in fields obj and return finished obj
     */
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
        } catch (InstantiationException | IllegalAccessException e) {
            logger.info("exception in method getObjectFromRequest reflApi: " + e.getMessage());
        } return object;
    }

    private void setFieldFromRequest(Field field, Object object, Object param) {
        try {
            field.setAccessible(true);
            if (field.getType() == Long.class) {
                field.set(object, Long.valueOf(param.toString()));
            } else if(field.getType() == String.class) {
                field.set(object, String.valueOf(param));
            } else if (field.getType() == Date.class) {
                field.set(object, Utils.dateFromHtml.parse(String.valueOf(param)));
            } else if (field.getType() == Integer.class) {
                field.set(object, Integer.valueOf(param.toString()));
            } else {
                field.set(object, null);
            }
            field.setAccessible(false);
        } catch (IllegalAccessException | ParseException e) {
            logger.info("exception in method setFieldFromRequest reflApi: " + e.getMessage());
        }
    }

    /**
     * @param object
     * @param <T>
     * @return string with name fields, split by dot
     */
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

    /**
     * @param object
     * @param <T>
     * @return finished body for sql execute update obj
     */
    public <T> String getObjectFieldsValueForUpdate(T object){
        StringBuilder stringBuilder = new StringBuilder();
        Field[] fields = object.getClass().getDeclaredFields();
        for (Field field : fields) {
            Column annotation = field.getAnnotation(Column.class);
            Id id = field.getAnnotation(Id.class);
            if (annotation != null && id == null) {
                stringBuilder.append(annotation.value());
                stringBuilder.append("=");
                stringBuilder.append("\'");
                stringBuilder.append(getFieldValue(field, object));
                stringBuilder.append("\'");
                stringBuilder.append(",");
            }
        } return stringBuilder.delete(stringBuilder.length()-1, stringBuilder.length()).toString();
    }

    /**
     * @param object
     * @param <T>
     * @return finished body for sql execute insert obj
     */
    public <T> String getObjectFieldsValueForInsert(T object) {
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

    /**
     * @param object
     * @return field name, field annotate class Id
     */
    public String getColumnIdName(Object object) {
        String result = "";
        Field[] fields = object.getClass().getDeclaredFields();
        for (Field field : fields) {
            if (field.getAnnotation(Id.class) != null) {
                if (field.getAnnotation(Column.class) != null) {
                    result = field.getAnnotation(Column.class).value();
                } else {
                    result = field.getName();
                }
            }
        }
        return result;
    }

    /**
     * @param object
     * @return loop object and try to find field annotate class Id, and return value this field
     */
    public Long getColumnIdValue(Object object) {
        Long result = null;
        Field[] fields = object.getClass().getDeclaredFields();
        for (Field field : fields) {
            if (field.getAnnotation(Id.class) != null) {
                try {
                    field.setAccessible(true);
                    result = (Long) field.get(object);
                    field.setAccessible(false);
                } catch (IllegalAccessException e) {
                    logger.info("IllegalAccessException in method getColumnIdValue reflApi: " + e.getMessage());
                }
            }
        }
        return result;
    }

    /**
     * @param field
     * @param object
     * @return string value obj field
     */
    private String getFieldValue(Field field, Object object) {
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
                    if (objField.getAnnotation(Id.class) != null) {
                        objField.setAccessible(true);
                        result = String.valueOf(objField.get(o));
                    }
                }
            }
        } catch (IllegalAccessException e) {
            logger.info("IllegalAccessException in method getFieldValue reflApi: " + e.getMessage());
        }
        field.setAccessible(false);
        return result;
    }

    public String getTableNameByClass(Class aClass) {
        Table table = (Table) aClass.getAnnotation(Table.class);
        return table != null ? table.tableName() : "";
    }
}
