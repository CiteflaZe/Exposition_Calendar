package com.project.service;

import com.project.domain.hall.Hall;

import java.util.List;
import java.util.Optional;

public interface HallService {
    boolean add(Hall hall);

    Optional<Hall> showByName(String name);

    List<Hall> showByCity(String city);

    Optional<Hall> showByStreet(String street);
}
