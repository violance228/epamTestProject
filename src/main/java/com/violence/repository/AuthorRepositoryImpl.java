package com.violence.repository;

import com.violence.entity.Author;

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
        return null;
    }

    @Override
    public List<Author> getAll() {
        return null;
    }
}
