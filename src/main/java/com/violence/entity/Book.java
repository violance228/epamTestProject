package com.violence.entity;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;
import java.util.Set;

public class Book implements DomainObject<Book> {
    private Long id;
    private String name;
    private String size;
    private String lang;
    private Boolean isUse;
    private Set<Author> authors;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Boolean getUse() {
        return isUse;
    }

    public void setUse(Boolean use) {
        isUse = use;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getLang() {
        return lang;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }

    public Set<Author> getAuthors() {
        return authors;
    }

    public void setAuthors(Set<Author> authors) {
        this.authors = authors;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return Objects.equals(name, book.name) &&
                Objects.equals(size, book.size) &&
                Objects.equals(lang, book.lang);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, size, lang);
    }

    @Override
    public String toString() {
        return "Book{" +
                "name='" + name + '\'' +
                ", size=" + size +
                ", lang='" + lang + '\'' +
                '}';
    }

    @Override
    public Book getObject(ResultSet resultSet) throws SQLException {
        Book book = new Book();

        book.setId(resultSet.getLong("ats_id"));
        book.setName(resultSet.getString("ats_code"));
        book.setSize(resultSet.getString("name"));
        book.setLang(resultSet.getString("parent_id"));
//        book.setUse(resultSet.getBoolean("password"));

        return book;
    }
}
