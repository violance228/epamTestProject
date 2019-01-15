package com.violence.repository;

import com.violence.entity.Catalog;
import com.violence.util.api.EntityAdapter;
import com.violence.util.api.EntityAdapterImpl;

import java.util.List;

public class CatalogRepositoryImpl implements CatalogRepository {

    private EntityAdapter entityAdapter = new EntityAdapterImpl();

    @Override
    public void save(Catalog catalog) {
        String sql = "INSERT INTO catalog (catalog_id, date_from, date_to, user_id, book_id) VALUES " + catalog.getId();
        entityAdapter.insert(sql);
    }

    @Override
    public void edit(Catalog catalog) {
        String sql = "UPDATE users " +
                "SET " + catalog.getFieldVsValue() +
                " WHERE CustomerID = ?";
        entityAdapter.update(sql, catalog.getId());
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public Catalog getById(Long id) {
        String sql = "SELECT catalog.*, users.*, books.* FROM catalog " +
                "INNER JOIN users ON catalog.user_id = users.user_id " +
                "INNER JOIN books ON catalog.book_id = books.book_id " +
                "WHERE catalog.catalog_id = ?";
        return (Catalog) entityAdapter.getObject(Catalog.class, sql, id);
    }

    @Override
    public List<Catalog> getAll() {
        String sql = "SELECT " +
                "books.*, " +
                "catalog.*, " +
                "users.* " +
                "FROM " +
                "catalog " +
                "INNER JOIN books ON catalog.book_id = books.book_id " +
                "INNER JOIN users ON catalog.user_id = users.user_id";
        return (List<Catalog>) entityAdapter.getListObject(Catalog.class, sql);
    }

    @Override
    public void saveList(List<Catalog> catalogs) {
        String sql = "INSERT INTO catalog (catalog_id, date_from, date_to, user_id, book_id) VALUES "
                + entityAdapter.prepareObjectToInsert(catalogs);
        entityAdapter.insert(sql);
    }
}
