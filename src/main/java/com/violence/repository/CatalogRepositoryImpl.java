package com.violence.repository;

import com.violence.entity.Catalog;
import com.violence.util.DataSourceConn;
import com.violence.util.api.EntityAdapter;
import com.violence.util.api.EntityAdapterImpl;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CatalogRepositoryImpl implements CatalogRepository {

    private static final Logger logger = Logger.getLogger(CatalogRepositoryImpl.class);
    private EntityAdapter entityAdapter = new EntityAdapterImpl();

    @Override
    public void save(Catalog catalog) {
        if (catalog.getId() == null)
            catalog.setId(getLastRecord().getId()+1);
        entityAdapter.insert(catalog);
    }

    @Override
    public void edit(Catalog catalog) {
        entityAdapter.update(catalog);
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public List<Catalog> getAllCatalogByUserId(Long userId) {
        String sql = "SELECT " +
                "books.*, " +
                "catalog.*, " +
                "users.* " +
                "FROM " +
                "catalog " +
                "INNER JOIN books ON catalog.book_id = books.book_id " +
                "INNER JOIN users ON catalog.user_id = users.user_id " +
                "WHERE catalog.user_id = ?";
        Map<Integer, Long> map = new HashMap<>();
        map.put(1, userId);
        return (List<Catalog>) entityAdapter.getListObject(sql, Catalog.class, map);
    }

    @Override
    public List<Catalog> getAllCatalogByBookId(Long bookId) {
        String sql = "SELECT " +
                "books.*, " +
                "catalog.*, " +
                "users.* " +
                "FROM " +
                "catalog " +
                "INNER JOIN books ON catalog.book_id = books.book_id " +
                "INNER JOIN users ON catalog.user_id = users.user_id " +
                "WHERE catalog.book_id = ?";
        Map<Integer, Long> map = new HashMap<>();
        map.put(1, bookId);
        return (List<Catalog>) entityAdapter.getListObject(sql, Catalog.class, map);
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
    public List<Catalog> getAll(int limit, int offset) {
        String sql = "SELECT " +
                "books.*, " +
                "catalog.*, " +
                "users.* " +
                "FROM " +
                "catalog " +
                "INNER JOIN books ON catalog.book_id = books.book_id " +
                "INNER JOIN users ON catalog.user_id = users.user_id " +
                "ORDER BY catalog_id ASC LIMIT " + limit + " OFFSET( " +
                offset + ")";
        return (List<Catalog>) entityAdapter.getListObject(Catalog.class, sql);
    }

    @Override
    public Integer getSize() {
        String sql = "SELECT COUNT(*) FROM catalog";
        ResultSet set = null;
        Integer size = null;
        Connection connection = DataSourceConn.getPostgreSqlConnection();
        try {
            set = connection.prepareStatement(sql).executeQuery();
            set.next();
            size = set.getInt("count");
        } catch (SQLException e) {
            logger.info(e.getMessage());
        } finally {
            DataSourceConn.close(null, connection, set);
        }
        return size;
    }

    @Override
    public void saveList(List<Catalog> catalogs) {
        String sql = "INSERT INTO catalog (catalog_id, date_from, date_to, user_id, book_id) VALUES "
                + entityAdapter.prepareObjectToInsert(catalogs);
        entityAdapter.insert(sql);
    }

    @Override
    public Catalog getLastRecord() {
        String sql = "SELECT * FROM catalog ORDER BY catalog_id DESC LIMIT 1";
        return (Catalog) entityAdapter.getObject(Catalog.class, sql);
    }
}
