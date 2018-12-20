package com.violence.repository;

import com.violence.entity.Author;
import com.violence.util.DataSourceConn;
import com.violence.util.api.EntityAdapter;

import java.util.List;

public class AuthorRepositoryImpl implements AuthorRepository {
    @Override
    public boolean save(Author author) {
        return false;
    }

    @Override
    public boolean edit(Author author) {
        return false;
    }

    @Override
    public boolean delete(Long id) {
        return false;
    }

    @Override
    public Author getById(Long id) {
        String sql = "";
        return new EntityAdapter().getObject(new Author(), sql, id);
    }

    @Override
    public List<Author> getAll() {
        String sql = "";
        return new EntityAdapter().getListObject(new Author(), sql);
    }
}
