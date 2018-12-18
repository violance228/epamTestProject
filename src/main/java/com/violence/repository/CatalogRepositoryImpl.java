package com.violence.repository;

import com.violence.entity.Catalog;

import java.util.List;

public class CatalogRepositoryImpl implements CatalogRepository {
    @Override
    public boolean save(Catalog catalog) {
        return false;
    }

    @Override
    public boolean edit(Catalog catalog) {
        return false;
    }

    @Override
    public boolean delete(Long id) {
        return false;
    }

    @Override
    public Catalog getById(Long id) {
        return null;
    }

    @Override
    public List<Catalog> getAll(Catalog catalog) {
        return null;
    }
}
