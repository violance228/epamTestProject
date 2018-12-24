package com.violence.repository;

import com.violence.entity.User;
import com.violence.util.api.EntityAdapter;
import com.violence.util.api.EntityAdapterImpl;

import java.util.List;

public class UserRepositoryImpl implements UserRepository {

    private EntityAdapter entityAdapter = new EntityAdapterImpl();

    @Override
    public void save(User user) {
        String sql = "INSERT INTO users (user_id, user_name, user_surname, login, password, email, phone) VALUES " + user.getId();
        entityAdapter.insert(sql);
    }

    @Override
    public void edit(User user) {
        String sql = "UPDATE users" +
                "SET " + user.getFieldVsValue() +
                "WHERE CustomerID = ?";
        entityAdapter.update(sql, user.getId());
    }

    @Override
    public void delete(Long id) {

    }


    @Override
    public User getUserByLogin(String login) {
        String sql = "SELECT " +
                "authors.* " +
                "FROM authors " +
                "WHERE authors.authors_id = ?";
        return (User) entityAdapter.getObject(User.class, sql, login);
    }

    @Override
    public User getById(Long id) {
        String sql = "SELECT * FROM users WHERE user_id = ?";
        return (User) entityAdapter.getObject(User.class, sql, id.toString());
    }

    @Override
    public List<User> getAll() {
        String sql = "SELECT * FROM users";
        return (List<User>) entityAdapter.getListObject(User.class, sql);
    }

    @Override
    public void saveList(List<User> users) {
        String sql = "INSERT INTO users (user_id, user_name, user_surname, login, password, email, phone) VALUES "
                + entityAdapter.prepareObjectToInsert(users);
        entityAdapter.insert(sql);
    }
}
