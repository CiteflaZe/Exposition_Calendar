package com.project.service.mapper;

import com.project.domain.hall.Hall;
import com.project.entity.hall.HallEntity;

public class HallMapper {
    public Hall mapHallEntityToHall(HallEntity hallEntity) {
        return Hall.builder()
                .withId(hallEntity.getId())
                .withName(hallEntity.getName())
                .withCity(hallEntity.getCity())
                .withStreet(hallEntity.getStreet())
                .withHouseNumber(hallEntity.getHouseNumber())
                .build();
    }

    public HallEntity mapHallToHallEntity(Hall hall) {
        return HallEntity.builder()
                .withId(hall.getId())
                .withName(hall.getName())
                .withCity(hall.getCity())
                .withStreet(hall.getStreet())
                .withHouseNumber(hall.getHouseNumber())
                .build();
    }
}
