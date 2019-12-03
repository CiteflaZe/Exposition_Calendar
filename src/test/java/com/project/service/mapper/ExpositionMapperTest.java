package com.project.service.mapper;

import com.project.domain.Exposition;
import com.project.entity.ExpositionEntity;
import org.junit.Test;

import static com.project.MockData.MOCK_EXPOSITION;
import static com.project.MockData.MOCK_EXPOSITION_ENTITY;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;


public class ExpositionMapperTest {

    private static final Exposition EXPOSITION = MOCK_EXPOSITION;
    private static final ExpositionEntity EXPOSITION_ENTITY = MOCK_EXPOSITION_ENTITY;

    private ExpositionMapper expositionMapper = new ExpositionMapper();


    @Test
    public void mapExpositionEntityToExpositionShouldReturnExposition() {
        final Exposition actual = expositionMapper.mapExpositionEntityToExposition(EXPOSITION_ENTITY);

        assertThat(actual.getId(), is(MOCK_EXPOSITION_ENTITY.getId()));
        assertThat(actual.getTitle(), is(MOCK_EXPOSITION_ENTITY.getTitle()));
        assertThat(actual.getTheme(), is(MOCK_EXPOSITION_ENTITY.getTheme()));
        assertThat(actual.getStartDate(), is(MOCK_EXPOSITION_ENTITY.getStartDate()));
        assertThat(actual.getEndDate(), is(MOCK_EXPOSITION_ENTITY.getEndDate()));
        assertThat(actual.getTicketPrice(), is(MOCK_EXPOSITION_ENTITY.getTicketPrice()));
        assertThat(actual.getDescription(), is(MOCK_EXPOSITION_ENTITY.getDescription()));
        assertThat(actual.getHall().getId(), is(MOCK_EXPOSITION_ENTITY.getHall().getId()));
        assertThat(actual.getHall().getName(), is(MOCK_EXPOSITION_ENTITY.getHall().getName()));
    }

    @Test
    public void mapExpositionToExpositionEntityShouldReturnExpositionEntity() {
        final ExpositionEntity actual = expositionMapper.mapExpositionToExpositionEntity(EXPOSITION);

        assertThat(actual.getId(), is(MOCK_EXPOSITION.getId()));
        assertThat(actual.getTitle(), is(MOCK_EXPOSITION.getTitle()));
        assertThat(actual.getTheme(), is(MOCK_EXPOSITION.getTheme()));
        assertThat(actual.getStartDate(), is(MOCK_EXPOSITION.getStartDate()));
        assertThat(actual.getEndDate(), is(MOCK_EXPOSITION.getEndDate()));
        assertThat(actual.getTicketPrice(), is(MOCK_EXPOSITION.getTicketPrice()));
        assertThat(actual.getDescription(), is(MOCK_EXPOSITION.getDescription()));
        assertThat(actual.getHall().getId(), is(MOCK_EXPOSITION.getHall().getId()));
        assertThat(actual.getHall().getName(), is(MOCK_EXPOSITION.getHall().getName()));
    }

}