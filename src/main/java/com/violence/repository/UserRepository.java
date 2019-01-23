package com.violence.repository;

import com.violence.entity.User;
import com.violence.repository.baseMethods.BaseMethods;

public interface UserRepository extends BaseMethods<User> {
    User getUserByLogin(String login);
    User getUserByPhone(String phone);
}
