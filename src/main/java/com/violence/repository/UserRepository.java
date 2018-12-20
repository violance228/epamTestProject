package com.violence.repository;

import com.violence.entity.Book;
import com.violence.entity.User;

public interface UserRepository extends BaseMethods<User> {
    Book getByIdd(Long id);
}
