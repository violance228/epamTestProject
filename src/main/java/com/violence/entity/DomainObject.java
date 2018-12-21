package com.violence.entity;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;

public interface DomainObject<T> extends Serializable {
    T getObject(ResultSet resultSet) throws SQLException;
    String getFieldVsValue();
}
