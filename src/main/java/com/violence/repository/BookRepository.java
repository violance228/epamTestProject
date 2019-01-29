package com.violence.repository;

import com.violence.entity.Book;
import com.violence.repository.baseMethods.BaseMethods;

import java.util.List;
import java.util.Set;

public interface BookRepository extends BaseMethods<Book> {
    List<Book> getAllAvailable();
    void rebuildByDate();
}
