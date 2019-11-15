package com.project.service.impl;

import com.project.dao.HallDao;
import com.project.domain.hall.Hall;
import com.project.entity.hall.HallEntity;
import com.project.exception.InvalidEntityException;
import com.project.service.HallService;
import com.project.service.mapper.HallMapper;
import org.apache.log4j.Logger;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class HallServiceImpl implements HallService {
    private static final Logger LOGGER = Logger.getLogger(HallServiceImpl.class);
    private final HallDao hallDao;
    private final HallMapper mapper;

    public HallServiceImpl(HallDao hallDao, HallMapper mapper) {
        this.hallDao = hallDao;
        this.mapper = mapper;
    }

    @Override
    public boolean add(Hall hall) {
        if (hall == null){
            LOGGER.warn("Hall can't be null");
            throw new InvalidEntityException("Hall is null");
        }
        final HallEntity entity = mapper.mapHallToHallEntity(hall);
        return hallDao.save(entity);
    }

    @Override
    public List<Hall> showAll(Integer startFrom, Integer rowCount) {
        final List<HallEntity> entities = hallDao.findAll(startFrom, rowCount);
        return mapHallEntityListToHallList(entities);
    }

    @Override
    public Integer showEntriesAmount() {
        return hallDao.countEntries();
    }

    @Override
    public Optional<Hall> showByName(String name) {
        final Optional<HallEntity> entity = hallDao.findByName(name);
        return entity.map(mapper::mapHallEntityToHall);
    }

    @Override
    public List<Hall> showByCity(String city) {
        final List<HallEntity> entities = hallDao.findByCity(city);
        return mapHallEntityListToHallList(entities);
    }

    @Override
    public Optional<Hall> showByStreet(String street) {
        final Optional<HallEntity> entity = hallDao.findByStreetName(street);
        return entity.map(mapper::mapHallEntityToHall);
    }

    private List<Hall> mapHallEntityListToHallList(List<HallEntity> entities){
        return entities.stream()
                .map(mapper::mapHallEntityToHall)
                .collect(Collectors.toList());
    }
}
