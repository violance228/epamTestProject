package com.violence.repository;

import com.violence.entity.Author;
import com.violence.entity.AuthorBooks;
import com.violence.util.DataSourceConn;
import com.violence.util.api.EntityAdapter;
import com.violence.util.api.EntityAdapterImpl;
import com.violence.util.api.annotation.Column;

import java.lang.annotation.Target;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AuthorBooksRepositoryImpl implements AuthorBooksRepository {

    private EntityAdapter entityAdapter = new EntityAdapterImpl();

    @Override
    public void save(AuthorBooks authorBooks) {
        entityAdapter.insert(authorBooks);
    }

    @Override
    public void edit(AuthorBooks authorBooks) {

    }

    @Override
    public void delete(Long id) {
    }

    @Override
    public AuthorBooks getById(Long id) {
        String sql = "SELECT" +
                "authors.* " +
                "author_book.* " +
                "books.* " +
                "FROM " +
                "authors " +
                "INNER JOIN author_books ON author_book.author_id = authors.author_id " +
                "INNER JOIN books ON author_book.book_id = books.book_id" +
                "WHERE author_book.author_id = ?";
        return (AuthorBooks) entityAdapter.getObject(AuthorBooks.class, sql, id);
    }

    @Column("author_id")
    @Override
    public ResultSet getRSByAuthorId(Long id) {
        String sql = "SELECT " +
                "authors.*, " +
                "books.* " +
                "FROM " +
                "author_books " +
                "INNER JOIN authors ON author_books.author_id = authors.author_id "+
                "INNER JOIN books ON author_books.book_id = books.book_id " +
                "WHERE author_books.author_id = " + id + " ";
        return entityAdapter.getResultSet(sql);
    }

    @Column("book_id")
    @Override
    public ResultSet getRSByBookId(Long id) {
        String sql = "SELECT" +
                "authors.* " +
                "books.* " +
                "FROM " +
                "authors " +
                "INNER JOIN author_books ON author_books.author_id = authors.author_id " +
                "INNER JOIN books ON author_books.book_id = books.book_id" +
                "WHERE author_books.book_id = " + id + " ";

        return entityAdapter.getResultSet(sql);
    }

    @Override
    public List<AuthorBooks> getAuthorBookListByAuthorId(Long authorId) {
        String sql = "SELECT " +
                "author_book.* " +
                "FROM " +
                "author_book " +
                "WHERE author_book.author_book_author_id = ?";
        Map<Integer, Long> map = new HashMap<>();
        map.put(1, authorId);
        return (List<AuthorBooks>) entityAdapter.getListObject(sql, AuthorBooks.class, map);
    }

    @Override
    public List<AuthorBooks> getAuthorBookListByBookId(Long bookId) {
        String sql = "SELECT " +
                "author_book.* " +
                "FROM " +
                "author_book " +
                "WHERE author_book.author_book_book_id = ?";
        Map<Integer, Long> map = new HashMap<>();
        map.put(1, bookId);
        return (List<AuthorBooks>) entityAdapter.getListObject(sql, AuthorBooks.class, map);
    }

    @Override
    public List<AuthorBooks> getAll() {
        return null;
    }

    @Override
    public void saveList(List<AuthorBooks> authorBooks) {

    }

    @Override
    public AuthorBooks getLastRecord() {
        String sql = "SELECT * FROM author_book ORDER BY author_book_id DESC LIMIT 1";
        return (AuthorBooks) entityAdapter.getObject(AuthorBooks.class, sql);
    }
}
