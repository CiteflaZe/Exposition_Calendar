package com.project.service.mapper;

import com.project.domain.exposition.Exposition;
import com.project.domain.hall.Hall;
import com.project.entity.exposition.ExpositionEntity;
import com.project.entity.hall.HallEntity;
import org.junit.Test;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;


public class ExpositionMapperTest {

    private ExpositionMapper mapper = new ExpositionMapper();

    private final Exposition exposition = initExposition();

    private final ExpositionEntity expositionEntity = initExpositionEntity();


    @Test
    public void mapExpositionEntityToExpositionShouldReturnExposition() {
        final Exposition actual = mapper.mapExpositionEntityToExposition(expositionEntity);

        assertThat(actual.getId(), is(expositionEntity.getId()));
        assertThat(actual.getTitle(), is(expositionEntity.getTitle()));
        assertThat(actual.getTheme(), is(expositionEntity.getTheme()));
        assertThat(actual.getStartTime(), is(expositionEntity.getStartTime()));
        assertThat(actual.getFinishTime(), is(expositionEntity.getFinishTime()));
        assertThat(actual.getTicketPrice(), is(expositionEntity.getTicketPrice()));
        assertThat(actual.getDescription(), is(expositionEntity.getDescription()));
        assertThat(actual.getHall().getId(), is(expositionEntity.getHall().getId()));
        assertThat(actual.getHall().getName(), is(expositionEntity.getHall().getName()));
    }

    @Test
    public void mapExpositionToExpositionEntityShouldReturnExpositionEntity() {
        final ExpositionEntity actual = mapper.mapExpositionToExpositionEntity(exposition);

        assertThat(actual.getId(), is(exposition.getId()));
        assertThat(actual.getTitle(), is(exposition.getTitle()));
        assertThat(actual.getTheme(), is(exposition.getTheme()));
        assertThat(actual.getStartTime(), is(exposition.getStartTime()));
        assertThat(actual.getFinishTime(), is(exposition.getFinishTime()));
        assertThat(actual.getTicketPrice(), is(exposition.getTicketPrice()));
        assertThat(actual.getDescription(), is(exposition.getDescription()));
        assertThat(actual.getHall().getId(), is(exposition.getHall().getId()));
        assertThat(actual.getHall().getName(), is(exposition.getHall().getName()));
    }

    private Exposition initExposition() {
        return Exposition.builder()
                .withId(1L)
                .withTitle("Title")
                .withTheme("Theme")
                .withStartTime(LocalDate.of(2019, 7, 13))
                .withFinishTime(LocalDate.of(2019, 8, 24))
                .withTicketPrice(BigDecimal.valueOf(14.25))
                .withDescription("Some generic description")
                .withHall(Hall.builder()
                        .withId(5L)
                        .withName("Hall")
                        .build())
                .build();
    }

    private ExpositionEntity initExpositionEntity() {
        return ExpositionEntity.builder()
                .withId(2L)
                .withTitle("Title 2")
                .withTheme("Generic Theme")
                .withStartTime(LocalDate.of(2019, 9, 11))
                .withFinishTime(LocalDate.of(2019, 10, 20))
                .withTicketPrice(BigDecimal.valueOf(46.20))
                .withDescription("Made up description")
                .withHall(HallEntity.builder()
                        .withId(4L)
                        .withName("Some Hall")
                        .build())
                .build();
    }

}