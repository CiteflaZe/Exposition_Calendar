package com.project.service;

import com.project.domain.exposition.Exposition;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface ExpositionService {
    boolean add(Exposition exposition);

    List<Exposition> showAll(Integer startFrom, Integer rowCount);

    Integer showEntriesAmount();
}
