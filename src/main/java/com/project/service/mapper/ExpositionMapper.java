package com.project.service.mapper;

import com.project.domain.exposition.Exposition;
import com.project.domain.hall.Hall;
import com.project.entity.exposition.ExpositionEntity;
import com.project.entity.hall.HallEntity;

public class ExpositionMapper {
    public Exposition mapExpositionEntityToExposition(ExpositionEntity expositionEntity){
        return Exposition.builder()
                .withId(expositionEntity.getId())
                .withTitle(expositionEntity.getTitle())
                .withTheme(expositionEntity.getTheme())
                .withStartTime(expositionEntity.getStartTime())
                .withFinishTime(expositionEntity.getFinishTime())
                .withTicketPrice(expositionEntity.getTicketPrice())
                .withDescription(expositionEntity.getDescription())
                .withHall(Hall.builder()
                        .withId(expositionEntity.getHall().getId())
                        .build())
                .build();
    }

    public ExpositionEntity mapExpositionToExpositionEntity(Exposition exposition){
        return ExpositionEntity.builder()
                .withId(exposition.getId())
                .withTitle(exposition.getTitle())
                .withTheme(exposition.getTheme())
                .withStartTime(exposition.getStartTime())
                .withFinishTime(exposition.getFinishTime())
                .withTicketPrice(exposition.getTicketPrice())
                .withDescription(exposition.getDescription())
                .withHall(HallEntity.builder()
                        .withId(exposition.getHall().getId())
                        .build())
                .build();
    }
}
