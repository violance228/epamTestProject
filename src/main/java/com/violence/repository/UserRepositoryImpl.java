package com.violence.repository;

import com.violence.entity.User;
import com.violence.util.api.EntityAdapter;
import com.violence.util.api.EntityAdapterImpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserRepositoryImpl implements UserRepository {

    private EntityAdapter entityAdapter = new EntityAdapterImpl();

    @Override
    public void save(User user) {
        if (user.getId() == null)
            user.setId(getLastRecord().getId()+1);

        String sql = "INSERT INTO users (user_id, user_name, user_surname, login, password, email, phone) VALUES " + entityAdapter.prepareObjectToInsert(user);
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
                "users.* " +
                "FROM users " +
                "WHERE users.login = ?";
        Map<Integer, String> map = new HashMap<>();
        map.put(1, login);
        return (User) entityAdapter.getObject(User.class, sql, map);
    }

    @Override
    public User getUserByPhone(String phone) {
        String sql = "SELECT " +
                "users.* " +
                "FROM users " +
                "WHERE users.phone = ?";
        Map<Integer, String> map = new HashMap<>();
        map.put(1, phone);
        return (User) entityAdapter.getObject(User.class, sql, map);
    }

    @Override
    public User getById(Long id) {
        String sql = "SELECT * FROM users WHERE user_id = ?";
        return (User) entityAdapter.getObject(User.class, sql, id);
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

    public User getLastRecord() {
        String sql = "SELECT * FROM users ORDER BY user_id DESC LIMIT 1";
        return (User) entityAdapter.getObject(User.class, sql);
    }
}
