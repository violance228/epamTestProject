package com.violence.repository;

import com.violence.entity.Book;

import java.util.List;

public class BookRepositoryImpl implements BookRepository {
    @Override
    public boolean save(Book book) {
        return false;
    }

    @Override
    public boolean edit(Book book) {
        return false;
    }

    @Override
    public boolean delete(Long id) {
        return false;
    }

    @Override
    public Book getById(Long id) {
        return null;
    }

    @Override
    public List<Book> getAll(Book book) {
        return null;
    }
}
