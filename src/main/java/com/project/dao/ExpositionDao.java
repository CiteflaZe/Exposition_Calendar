package com.project.dao;

import com.project.entity.ExpositionEntity;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface ExpositionDao extends CrudDao<ExpositionEntity, Long> {

    Optional<ExpositionEntity> findByTitle(String title);

    List<ExpositionEntity> findAllWhereEndDateGreaterThanNow(Integer startFrom, Integer rowCount);

    long countByEndDateGreaterThan();

}
