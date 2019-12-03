package com.project.service;

import com.project.domain.Exposition;

import java.util.List;

public interface ExpositionService {
    boolean add(Exposition exposition);

    List<Exposition> showAll(Integer page, Integer rowCount);

    List<Exposition> showAllNotFinished(Integer page, Integer rowCount);

    Long showEntriesAmount();

    Long showNotFinishedEntriesAmount();
}
