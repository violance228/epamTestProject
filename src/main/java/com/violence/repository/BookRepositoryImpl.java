package com.violence.repository;

import com.violence.entity.Book;
import com.violence.util.api.EntityAdapter;
import com.violence.util.api.EntityAdapterImpl;

import java.util.List;

public class BookRepositoryImpl implements BookRepository {

    private EntityAdapter entityAdapter = new EntityAdapterImpl();

    @Override
    public void save(Book book) {

        String sql = "INSERT INTO books (book_id, book_name, size, lang, is_use) VALUES " + book.getId();
        entityAdapter.insert(sql);
    }

    @Override
    public void edit(Book book) {
        String sql = "UPDATE users" +
                "SET " + book.getFieldVsValue() +
                "WHERE CustomerID = ?";
        entityAdapter.update(sql, book.getId());
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public Book getById(Long id) {
        String sql = "SELECT " +
                "books.* " +
                "author_book.* " +
                "authors.* " +
                "FROM " +
                "books " +
                "INNER JOIN author_book ON author_book.author_book_book_id = books.book_id " +
                "INNER JOIN authors ON author_book.author_book_author_id = authors.author_id" +
                "WHERE books.books_id = ?";
        
        return (Book) entityAdapter.getObject(Book.class, sql, id.toString());
    }

    @Override
    public List<Book> getAll() {
        String sql = "SELECT " +
                     "books.* " +
                     "catalog.* " +
                     "users.* " +
                     "FROM " +
                     "books " +
                     "INNER JOIN catalog ON catalog.book_id = books.book_id " +
                     "INNER JOIN users ON catalog.user_id = users.user_id";
        return (List<Book>) entityAdapter.getListObject(Book.class, sql);
    }

    @Override
    public void saveList(List<Book> books) {
        String sql = "INSERT INTO books (book_id, book_name, size, lang, is_use) VALUES "
                + entityAdapter.prepareObjectToInsert(books);
        entityAdapter.insert(sql);
    }
}
