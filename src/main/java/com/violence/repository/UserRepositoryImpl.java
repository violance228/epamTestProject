package com.violence.repository;

import com.violence.entity.User;

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
        return null;
    }

    @Override
    public List<User> getAll(User user) {
        return null;
    }
}
