package com.violence.entity;

import com.violence.util.api.annotation.Column;
import com.violence.util.api.annotation.Table;

import java.util.Objects;

@Table(tableName = "author_book")
public class AuthorBooks implements DomainObject {
    @Column(value = "author_book_author_id")
    private Long authorId;
    @Column(value = "author_book_book_id")
    private Long bookId;

    public Long getAuthorId() {
        return authorId;
    }

    public void setAuthorId(Long authorId) {
        this.authorId = authorId;
    }

    public Long getBookId() {
        return bookId;
    }

    public void setBookId(Long bookId) {
        this.bookId = bookId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AuthorBooks that = (AuthorBooks) o;
        return Objects.equals(authorId, that.authorId) &&
                Objects.equals(bookId, that.bookId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(authorId, bookId);
    }
}
