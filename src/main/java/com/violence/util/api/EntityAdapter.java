package com.violence.util.api;

import com.violence.entity.DomainObject;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EntityAdapter<T> {
    public List<T> getListObjectFromResultSet(T t, ResultSet resultSet) throws SQLException {
        List<T> result = new ArrayList<>();
        if (t instanceof DomainObject) {
            while (resultSet.next()) {
                result.add((T) ((DomainObject) t).getObject(resultSet));
            }
            return result;
        } else {
            return null;
        }
    }

    public T getObjectFromResultSet(T t, ResultSet resultSet) throws SQLException {
        if (t instanceof DomainObject) {
            while (resultSet.next())
                return t = (T) ((DomainObject) t).getObject(resultSet);
            return null;
        } else {
            return null;
        }
    }
}
