package com.project.service.impl;

import com.project.dao.ExpositionDao;
import com.project.domain.exposition.Exposition;
import com.project.entity.exposition.ExpositionEntity;
import com.project.exception.InvalidEntityException;
import com.project.service.ExpositionService;
import com.project.service.mapper.ExpositionMapper;
import org.apache.log4j.Logger;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
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
            throw new InvalidEntityException("Exposition is null");
        }
        final ExpositionEntity entity = mapper.mapExpositionToExpositionEntity(exposition);
        return expositionDao.save(entity);
    }

    @Override
    public List<Exposition> showAll(Integer startFrom, Integer rowCount) {
        final List<ExpositionEntity> entities = expositionDao.findAll(startFrom, rowCount);
        return entities.stream()
                .map(mapper::mapExpositionEntityToExposition)
                .collect(Collectors.toList());
    }

    @Override
    public Integer showEntriesAmount() {
        return expositionDao.countEntries();
    }
}
