package com.violence.repository.baseMethods;

import java.util.List;

public interface BaseMethods<T> {
    void save(T t);
    void edit(T t);
    void delete(Long id);
    T getById(Long id);
    List<T> getAll();
    void saveList(List<T> ts);
}
