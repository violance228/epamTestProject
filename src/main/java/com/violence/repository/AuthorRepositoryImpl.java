package com.violence.repository;

import com.violence.entity.Author;
import com.violence.entity.AuthorBooks;
import com.violence.entity.Book;
import com.violence.util.api.EntityAdapter;
import com.violence.util.api.EntityAdapterImpl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class AuthorRepositoryImpl implements AuthorRepository {

    private EntityAdapter entityAdapter = new EntityAdapterImpl();
    private AuthorBooksRepository authorBooksRepository = new AuthorBooksRepositoryImpl();

    @Override
    public void save(Author author) {
        author.setId(getLastRecord().getId()+1);
        entityAdapter.insert(author);
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
                "WHERE authors.author_id = ?";
        Author author = (Author) entityAdapter.getObject(Author.class, sql, id);
        List<AuthorBooks> authorBooks = authorBooksRepository.getAuthorBookListByAuthorId(author.getId());
        Set<Book> bookSet = new HashSet<>();
        for (AuthorBooks authorBook : authorBooks) {
            bookSet.add((Book) entityAdapter.getObject(Book.class,
                    "SELECT books.* FROM books WHERE books.book_id = ?",
                    authorBook.getBookId()));
        }
        author.setBooks(bookSet);
        return author;
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
        String sql = "SELECT * FROM authors ORDER BY author_id DESC LIMIT 1";
        return (Author) entityAdapter.getObject(Author.class, sql);
    }
}
