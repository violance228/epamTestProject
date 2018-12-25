package com.violence.entity;

import com.violence.repository.AuthorBooksRepositoryImpl;
import com.violence.util.api.annotation.Column;
import com.violence.util.api.annotation.Contact;

import java.util.Objects;
import java.util.Set;

public class Book implements DomainObject<Book> {
    @Column("book_id")
    private Long id;
    @Column("book_name")
    private String name;
    @Column("size")
    private Integer size;
    @Column("lang")
    private String lang;
    @Column("is_use")
    private Boolean isUse;
    @Column("user")
    @Contact(value = "author_id", queryExecuteClass = AuthorBooksRepositoryImpl.class)
    private Set<Author> authors;

    public Book(Long id, String name, Integer size, String lang, Boolean isUse) {
        this.id = id;
        this.name = name;
        this.size = size;
        this.lang = lang;
        this.isUse = isUse;
    }

    public Book() { }

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

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
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
        return " ( " + id + ", \'" +
                name + "\', \'" +
                size + "\', \'" +
                lang + "\', \'" +
                isUse +  "\' )";
    }

    @Override
    public String getFieldVsValue() {
        return  "book_id = '" + id + '\'' +
                ", book_name = '" + name + '\'' +
                ", size = " + size +
                ", lang = '" + lang + '\'' +
                ", is_use = '" + isUse + '\'';
    }

//    @Override
//    public Book getObject(ResultSet resultSet) throws SQLException {
//        Book book = new Book();
//
//        book.setId(resultSet.getLong("book_id"));
//        book.setName(resultSet.getString("book_name"));
//        book.setSize(resultSet.getInt("size"));
//        book.setLang(resultSet.getString("lang"));
//        book.setUse(resultSet.getBoolean("is_use"));
////        book.setAuthors(new EntityAdapterImpl().getSetObjectFromResultSet(new Author(), "author_book_book_id" , book.getId(), resultSet));
//
//        return book;
//    }
}
