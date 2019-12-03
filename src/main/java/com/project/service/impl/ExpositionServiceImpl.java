package com.project.service.impl;

import com.project.dao.ExpositionDao;
import com.project.domain.Exposition;
import com.project.entity.ExpositionEntity;
import com.project.exception.ExpositionAlreadyExistException;
import com.project.service.ExpositionService;
import com.project.service.mapper.ExpositionMapper;
import org.apache.log4j.Logger;

import java.util.List;
import java.util.stream.Collectors;

public class ExpositionServiceImpl implements ExpositionService {
    private static final Logger LOGGER = Logger.getLogger(ExpositionServiceImpl.class);
    private final ExpositionDao expositionDao;
    private final ExpositionMapper mapper;

    public ExpositionServiceImpl(ExpositionDao expositionDao, ExpositionMapper mapper) {
        this.expositionDao = expositionDao;
        this.mapper = mapper;
    }

    @Override
    public boolean add(Exposition exposition) {
        if (exposition == null) {
            LOGGER.warn("Exposition can't be null");
            throw new IllegalArgumentException("Exposition is null");
        }

        if(expositionDao.findByTitle(exposition.getTitle()).isPresent()){
            LOGGER.warn("Exposition with this title already exists");
            throw new ExpositionAlreadyExistException("Exposition with this title already exists");
        }

        final ExpositionEntity entity = mapper.mapExpositionToExpositionEntity(exposition);
        return expositionDao.save(entity);
    }

    @Override
    public List<Exposition> showAll(Integer startFrom, Integer rowCount) {
        final List<ExpositionEntity> entities = expositionDao.findAll(startFrom, rowCount);
        return mapExpositionEntityListToExpositionList(entities);
    }

    @Override
    public List<Exposition> showAllNotFinished(Integer page, Integer rowCount) {
        final List<ExpositionEntity> entities = expositionDao.findAllWhereEndDateGreaterThanNow(page, rowCount);
        return mapExpositionEntityListToExpositionList(entities);
    }

    @Override
    public Long showEntriesAmount() {
        return expositionDao.countEntries();
    }

    @Override
    public Long showNotFinishedEntriesAmount() {
        return expositionDao.countByEndDateGreaterThan();
    }

    private List<Exposition> mapExpositionEntityListToExpositionList(List<ExpositionEntity> entities) {
        return entities.stream()
                .map(mapper::mapExpositionEntityToExposition)
                .collect(Collectors.toList());
    }
}
