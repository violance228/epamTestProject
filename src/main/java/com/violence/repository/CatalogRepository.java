package com.violence.repository;

import com.violence.entity.Catalog;
import com.violence.repository.baseMethods.BaseMethods;

import java.util.List;

public interface CatalogRepository extends BaseMethods<Catalog> {
    List<Catalog> getAllCatalogByUserId(Long userId);
}
