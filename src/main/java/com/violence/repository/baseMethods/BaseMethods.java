package com.violence.repository.baseMethods;

import java.util.List;

public interface BaseMethods<T> {
    void save(T t);
    void edit(T t);
    void delete(Long id);
    T getById(Long id);
    List<T> getAll();
    List<T> getAll(int limit, int offset);
    void saveList(List<T> ts);
    T getLastRecord();
}
