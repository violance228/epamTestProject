package com.violence.repository;

import com.violence.entity.User;

public interface UserRepository extends BaseMethods<User> {
    User getUserByLogin(String login);
}
