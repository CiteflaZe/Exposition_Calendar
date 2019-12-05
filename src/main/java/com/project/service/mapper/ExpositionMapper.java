package com.project.service.mapper;

import com.project.domain.Exposition;
import com.project.domain.Hall;
import com.project.entity.ExpositionEntity;
import com.project.entity.HallEntity;

public class ExpositionMapper {

    public Exposition mapExpositionEntityToExposition(ExpositionEntity expositionEntity){
        return Exposition.builder()
                .withId(expositionEntity.getId())
                .withTitle(expositionEntity.getTitle())
                .withTheme(expositionEntity.getTheme())
                .withStartDate(expositionEntity.getStartDate())
                .withEndDate(expositionEntity.getEndDate())
                .withTicketPrice(expositionEntity.getTicketPrice())
                .withDescription(expositionEntity.getDescription())
                .withHall(Hall.builder()
                        .withId(expositionEntity.getHall().getId())
                        .withName(expositionEntity.getHall().getName())
                        .build())
                .build();
    }

    public ExpositionEntity mapExpositionToExpositionEntity(Exposition exposition){
        return ExpositionEntity.builder()
                .withTitle(exposition.getTitle())
                .withTheme(exposition.getTheme())
                .withStartDate(exposition.getStartDate())
                .withEndDate(exposition.getEndDate())
                .withTicketPrice(exposition.getTicketPrice())
                .withDescription(exposition.getDescription())
                .withHall(HallEntity.builder()
                        .withId(exposition.getHall().getId())
                        .withName(exposition.getHall().getName())
                        .build())
                .build();
    }
}
