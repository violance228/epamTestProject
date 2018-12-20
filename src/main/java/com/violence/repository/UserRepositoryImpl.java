package com.violence.repository;

import com.violence.entity.User;
import com.violence.util.api.EntityAdapter;

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
    public User getById(Long id) {
        String sql = "SELECT * FROM user WHERE user_id = ?";
        return new EntityAdapter().getObject(new User(), sql, id);
    }

    @Override
    public List<User> getAll() {
        String sql = "SELECT * FROM user";
        return new EntityAdapter().getListObject(new User(), sql);
    }
}
