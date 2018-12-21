package com.violence.entity;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.Objects;

public class Catalog implements DomainObject<Catalog> {
    private Long id;
    private Date dateFrom;
    private Date dateTo;
    private User user;
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

    @Override
    public Catalog getObject(ResultSet resultSet) throws SQLException {
        Catalog catalog = new Catalog();

        catalog.setId(resultSet.getLong("catalog_id"));
        catalog.setDateFrom(resultSet.getDate("date_from"));
        catalog.setDateTo(resultSet.getDate("date_to"));
        catalog.setBook(new Book().getObject(resultSet));
        catalog.setUser(new User().getObject(resultSet));

        return catalog;
    }
}
