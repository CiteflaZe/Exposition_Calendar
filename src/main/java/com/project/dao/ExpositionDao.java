package com.project.dao;

import com.project.entity.exposition.ExpositionEntity;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface ExpositionDao extends CrudDao<ExpositionEntity, Long> {
    Optional<ExpositionEntity> findByTitle(String title);

    List<ExpositionEntity> findByTheme(String theme);

    List<ExpositionEntity> findByPriceRange(BigDecimal min, BigDecimal max);

    List<ExpositionEntity> findByDate(LocalDate date);

    List<ExpositionEntity> findByHallId(Long id);
}
