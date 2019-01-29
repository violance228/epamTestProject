package com.violence.repository;

import com.violence.entity.Author;
import com.violence.entity.AuthorBooks;
import com.violence.entity.Book;
import com.violence.entity.Catalog;
import com.violence.util.api.EntityAdapter;
import com.violence.util.api.EntityAdapterImpl;

import java.util.*;

public class BookRepositoryImpl implements BookRepository {

    private EntityAdapter entityAdapter = new EntityAdapterImpl();
    private AuthorBooksRepository authorBooksRepository = new AuthorBooksRepositoryImpl();
    private AuthorRepository authorRepository = new AuthorRepositoryImpl();
    private CatalogRepository catalogRepository = new CatalogRepositoryImpl();

    @Override
    public void save(Book book) {
        if (book.getId() == null)
            book.setId(getLastRecord().getId()+1);
        book.setUse(false);
        entityAdapter.insert(book);
    }

    @Override
    public void edit(Book book) {
        entityAdapter.update(book);
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public Book getById(Long id) {
        String sql = "SELECT " +
                "books.* " +
                "FROM " +
                "books " +
                "WHERE books.book_id = ?";
        Book book = (Book) entityAdapter.getObject(Book.class, sql, id);
        List<AuthorBooks> authorBooks = authorBooksRepository.getAuthorBookListByBookId(book.getId());
        Set<Author> authorSet = new HashSet<>();
        for (AuthorBooks authorBook : authorBooks)
            authorSet.add(authorRepository.getById(authorBook.getAuthorId()));
        book.setAuthors(authorSet);
        return book;
    }

    @Override
    public List<Book> getAll() {
        String sql = "SELECT " +
                     "books.* " +
                     "FROM " +
                     "books ";
        return (List<Book>) entityAdapter.getListObject(Book.class, sql);
    }

    @Override
    public List<Book> getAll(int limit, int offset) {
        String sql = "SELECT " +
                "books.* " +
                "FROM " +
                "books " +
                "ORDER BY book_id ASC LIMIT " + limit + " OFFSET( " +
                offset + ")";
        return (List<Book>) entityAdapter.getListObject(Book.class, sql);
    }

    @Override
    public List<Book> getAllAvailable() {
        String sql = "SELECT " +
                "books.* " +
                "FROM " +
                "books " +
                "WHERE books.is_use = 'false'";
        return (List<Book>) entityAdapter.getListObject(Book.class, sql);
    }

    @Override
    public void saveList(List<Book> books) {
        String sql = "INSERT INTO books (book_id, book_name, size, lang, is_use) VALUES "
                + entityAdapter.prepareObjectToInsert(books);
        entityAdapter.insert(sql);
    }

    @Override
    public Book getLastRecord() {
        String sql = "SELECT * FROM books ORDER BY book_id DESC LIMIT 1";
        return (Book) entityAdapter.getObject(Book.class, sql);
    }

    public void rebuildByDate() {
        List<Catalog> catalogList = catalogRepository.getAll();
        Set<Book> saveList = new HashSet<>();
        for (Catalog catalog : catalogList) {
            if (catalog.getDateTo().getTime() < new Date().getTime() && catalog.getBook().getUse()) {
                catalog.getBook().setUse(false);
                saveList.add(catalog.getBook());
            }
        }
        for (Book book : saveList) {
            save(book);
        }
    }
}
