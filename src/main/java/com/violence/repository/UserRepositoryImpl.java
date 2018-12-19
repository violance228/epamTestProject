package com.violence.repository;

import com.violence.entity.User;
import com.violence.util.DataSourceConn;
import com.violence.util.api.EntityAdapter;

import javax.inject.Inject;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class UserRepositoryImpl implements UserRepository {

    @Override
    public boolean save(User user) {
        return false;
    }

    @Override
    public boolean edit(User user) {
        return false;
    }

    @Override
    public boolean delete(Long id) {
        return false;
    }

    @Override
    public User getById(Long id) throws SQLException {
        PreparedStatement statement = DataSourceConn.getPostgreSqlConnection().prepareStatement("SELECT * FROM users WHERE id = ?");
        statement.setLong(1, id);
        ResultSet resultSet = statement.executeQuery();
        return new EntityAdapter<User>().getObjectFromResultSet(new User(), resultSet);
    }

    @Override
    public List<User> getAll() throws SQLException {
        Statement statement = DataSourceConn.getPostgreSqlConnection().createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM users");
        return new EntityAdapter<User>().getListObjectFromResultSet(new User(), resultSet);
    }
}
