package com.violence.repository;

import java.util.List;

public interface BaseMethods<T> {
    boolean save(T t);
    boolean edit(T t);
    boolean delete(Long id);
    T getById(Long id);
    List<T> getAll(T t);
}
