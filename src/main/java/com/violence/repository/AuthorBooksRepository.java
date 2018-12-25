package com.violence.repository;

import com.violence.entity.AuthorBooks;
import com.violence.repository.baseMethods.BaseMethods;

import java.sql.ResultSet;

public interface AuthorBooksRepository extends BaseMethods<AuthorBooks> {
    ResultSet getByAuthorId(Long id);
    ResultSet getByBookId(Long id);
}
