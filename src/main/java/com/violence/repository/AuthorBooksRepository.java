package com.violence.repository;

import com.violence.entity.AuthorBooks;
import com.violence.repository.baseMethods.BaseMethods;

import java.sql.ResultSet;
import java.util.List;

public interface AuthorBooksRepository extends BaseMethods<AuthorBooks> {
    ResultSet getRSByAuthorId(Long id);
    ResultSet getRSByBookId(Long id);
    List<AuthorBooks> getAuthorBookListByAuthorId(Long authorId);
    List<AuthorBooks> getAuthorBookListByBookId(Long bookId);
}
