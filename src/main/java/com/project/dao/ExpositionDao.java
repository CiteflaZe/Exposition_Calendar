package com.project.dao;

import com.project.domain.exposition.Exposition;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

public interface ExpositionDao extends CrudDao<Exposition, Long> {
    Optional<Exposition> findByTitle(String title);

    List<Exposition> findByTheme(String theme);

    List<Exposition> findByPriceRange(BigDecimal min, BigDecimal max);

    List<Exposition> findByTimeRange(Timestamp start, Timestamp finish);
}
