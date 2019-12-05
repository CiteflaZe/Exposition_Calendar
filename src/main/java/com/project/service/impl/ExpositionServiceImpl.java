package com.project.service.impl;

import com.project.dao.ExpositionDao;
import com.project.domain.Exposition;
import com.project.entity.ExpositionEntity;
import com.project.exception.EntityNotFoundException;
import com.project.exception.ExpositionAlreadyExistException;
import com.project.service.ExpositionService;
import com.project.service.mapper.ExpositionMapper;
import org.apache.log4j.Logger;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static java.util.Collections.emptyList;

public class ExpositionServiceImpl implements ExpositionService {
    private static final Logger LOGGER = Logger.getLogger(ExpositionServiceImpl.class);

    private final ExpositionDao expositionDao;
    private final ExpositionMapper expositionMapper;

    public ExpositionServiceImpl(ExpositionDao expositionDao, ExpositionMapper expositionMapper) {
        this.expositionDao = expositionDao;
        this.expositionMapper = expositionMapper;
    }

    @Override
    public boolean add(Exposition exposition) {
        if (exposition == null) {
            LOGGER.warn("Exposition can't be null");
            throw new IllegalArgumentException("Exposition is null");
        }

        if(exposition.getStartDate().compareTo(exposition.getEndDate()) > 0){
            LOGGER.warn("Start date is greater than end date");
            throw new IllegalArgumentException("Start date is greater than end date");
        }

        if (expositionDao.findByTitle(exposition.getTitle()).isPresent()) {
            LOGGER.warn("Exposition with this title already exists");
            throw new ExpositionAlreadyExistException("Exposition with this title already exists");
        }

        final ExpositionEntity entity = expositionMapper.mapExpositionToExpositionEntity(exposition);
        return expositionDao.save(entity);
    }

    @Override
    public Exposition showById(Long id) {
        final Optional<ExpositionEntity> entity = expositionDao.findById(id);

        if(!entity.isPresent()){
            LOGGER.warn("There is no expositions with this id");
            throw new EntityNotFoundException("There is no expositions with this id");
        }

        return expositionMapper.mapExpositionEntityToExposition(entity.get());
    }

    @Override
    public List<Exposition> showAll(Integer page, Integer rowCount) {
        Integer offset = page * rowCount - rowCount;

        final List<ExpositionEntity> entities = expositionDao.findAll(offset, rowCount);
        return mapExpositionEntityListToExpositionList(entities);
    }

    @Override
    public List<Exposition> showAllNotFinished(Integer page, Integer rowCount) {
        Integer offset = page * rowCount - rowCount;

        final List<ExpositionEntity> entities = expositionDao.findAllWhereEndDateGreaterThanNow(offset, rowCount);
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
        return entities.isEmpty() ?
                emptyList() :
                entities.stream()
                        .map(expositionMapper::mapExpositionEntityToExposition)
                        .collect(Collectors.toList());
    }
}
