package com.violence.entity;

import com.violence.util.api.annotation.Column;
import com.violence.util.api.annotation.Id;
import com.violence.util.api.annotation.Table;

import java.util.Date;
import java.util.Objects;

@Table(tableName = "catalog")
public class Catalog implements DomainObject {
    @Id
    @Column("catalog_id")
    private Long id;
    @Column("date_from")
    private Date dateFrom;
    @Column("date_to")
    private Date dateTo;
    @Column("user_id")
    private User user;
    @Column("book_id")
    private Book book;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDateFrom() {
        return dateFrom;
    }

    public void setDateFrom(Date dateFrom) {
        this.dateFrom = dateFrom;
    }

    public Date getDateTo() {
        return dateTo;
    }

    public void setDateTo(Date dateTo) {
        this.dateTo = dateTo;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Catalog catalog = (Catalog) o;
        return Objects.equals(id, catalog.id) &&
                Objects.equals(dateFrom, catalog.dateFrom) &&
                Objects.equals(dateTo, catalog.dateTo) &&
                Objects.equals(user, catalog.user) &&
                Objects.equals(book, catalog.book);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, dateFrom, dateTo, user, book);
    }

    @Override
    public String toString() {
        return " ( " + id + ", \'" +
                dateFrom + "\', \'" +
                dateTo + "\', \'" +
                user.getId() + "\', \'" +
                book.getId() +  "\' )";
    }
}
