package com.project.dao;


import com.project.entity.hall.HallEntity;

import java.util.List;
import java.util.Optional;

public interface HallDao extends CrudDao<HallEntity, Long> {
    Optional<HallEntity> findByName(String name);

    List<HallEntity> findByCity(String city);

    Optional<HallEntity> findByStreetName(String streetName);
}
