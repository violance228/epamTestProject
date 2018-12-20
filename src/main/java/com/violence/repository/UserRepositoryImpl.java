package com.violence.repository;

import com.violence.entity.Book;
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
    public User getById(Long id) throws SQLException, NoSuchMethodException {
        return null;
    }

    @Override
    public Book getByIdd(Long id) {
        String sql = "SELECT * FROM ats WHERE ats_id = ?";
        return new EntityAdapter<Book>().getObject(new Book(), sql, id);
    }

    @Override
    public List<User> getAll() {
        String sql = "SELECT * FROM goods";
        return new EntityAdapter<User>().getListObject(new User(), sql);
    }
}
