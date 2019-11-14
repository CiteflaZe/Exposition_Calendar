package com.project.service;

import com.project.domain.exposition.Exposition;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface ExpositionService {
    boolean add(Exposition exposition);

    List<Exposition> showAll();

    Optional<Exposition> showByTitle(String title);

    List<Exposition> showByTheme(String theme);

    List<Exposition> showByDate(LocalDate date);

    List<Exposition> showByHallId(Long id);

    List<Exposition> showByPriceRange(BigDecimal min, BigDecimal max);
}
