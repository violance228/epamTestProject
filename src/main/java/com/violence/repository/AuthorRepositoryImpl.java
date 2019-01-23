package com.violence.repository;

import com.violence.entity.Author;
import com.violence.entity.Book;
import com.violence.util.api.EntityAdapter;
import com.violence.util.api.EntityAdapterImpl;

import java.util.List;

public class AuthorRepositoryImpl implements AuthorRepository {

    private EntityAdapter entityAdapter = new EntityAdapterImpl();

    @Override
    public void save(Author author) {
        String sql = "INSERT INTO authors (author_id, author_name, author_surname, country) VALUES " + author.toString();
        entityAdapter.insert(sql);
    }

    @Override
    public void edit(Author author) {
        String sql = "UPDATE users" +
                "SET " + author.getFieldVsValue() +
                "WHERE CustomerID = ?";
        entityAdapter.update(sql, author.getId());
    }

    @Override
    public void delete(Long id) { }

    @Override
    public Author getById(Long id) {
        String sql = "SELECT " +
                "authors.* " +
                "FROM authors " +
                "WHERE authors.authors_id = ?";
        return (Author) entityAdapter.getObject(Author.class, sql, id);
    }

    @Override
    public List<Author> getAll() {
        String sql = "SELECT * " +
                    "FROM authors ";
        return (List<Author>) entityAdapter.getListObject(Author.class, sql);
    }

    @Override
    public void saveList(List<Author> authors) {
        String sql = "INSERT INTO authors (author_id, author_name, author_surname, country) VALUES "
                + entityAdapter.prepareObjectToInsert(authors);
        entityAdapter.insert(sql);
    }

    @Override
    public Author getLastRecord() {
        String sql = "SELECT * FROM authors ORDER BY authors_id DESC LIMIT 1";
        return (Author) entityAdapter.getObject(Author.class, sql);
    }
}
