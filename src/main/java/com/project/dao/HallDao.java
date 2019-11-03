package com.project.dao;

import com.project.domain.hall.Hall;

import java.util.List;
import java.util.Optional;

public interface HallDao extends CrudDao<Hall, Long> {
    Optional<Hall> findByName(String name);

    List<Hall> findByCity(String city);

    Optional<Hall> findByStreetName(String streetName);
}
