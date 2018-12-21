package com.violence.repository;

import com.violence.entity.AuthorBooks;
import com.violence.util.DataSourceConn;

import java.sql.Statement;
import java.util.List;

public class AuthorBooksRepositoryImpl implements AuthorBooksRepository {

    @Override
    public boolean save(AuthorBooks authorBooks) {
        return false;
    }

    @Override
    public boolean edit(AuthorBooks authorBooks) {
        return false;
    }

    @Override
    public boolean delete(Long id) {
        return false;
    }

    @Override
    public AuthorBooks getById(Long id) {
        return null;
    }

    @Override
    public List<AuthorBooks> getAll() {
        return null;
    }

    @Override
    public void saveList(List<AuthorBooks> authorBooks) {

    }
}
