package com.project.service;

import com.project.domain.Hall;

import java.util.List;

public interface HallService {

    boolean add(Hall hall);

    List<Hall> showAll();

}
