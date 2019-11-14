package com.project.service.mapper;

import com.project.domain.hall.Hall;
import com.project.entity.hall.HallEntity;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class HallMapperTest {

    private final HallMapper mapper = new HallMapper();

    private final Hall hall = initHall();

    private final HallEntity hallEntity = initHallEntity();

    @Test
    public void mapHallEntityToHallShouldReturnHall() {
        final Hall actual = mapper.mapHallEntityToHall(hallEntity);

        assertThat(actual.getId(), is(hallEntity.getId()));
        assertThat(actual.getName(), is(hallEntity.getName()));
        assertThat(actual.getCity(), is(hallEntity.getCity()));
        assertThat(actual.getStreet(), is(hallEntity.getStreet()));
        assertThat(actual.getHouseNumber(), is(hallEntity.getHouseNumber()));
    }

    @Test
    public void mapHallToHallEntityShouldReturnHallEntity() {
        final HallEntity actual = mapper.mapHallToHallEntity(hall);

        assertThat(actual.getId(), is(hall.getId()));
        assertThat(actual.getName(), is(hall.getName()));
        assertThat(actual.getCity(), is(hall.getCity()));
        assertThat(actual.getStreet(), is(hall.getStreet()));
        assertThat(actual.getHouseNumber(), is(hall.getHouseNumber()));
    }

    private Hall initHall() {
        return Hall.builder()
                .withId(4L)
                .withName("Hall")
                .withCity("City")
                .withStreet("Street")
                .withHouseNumber(13)
                .build();
    }

    private HallEntity initHallEntity() {
        return HallEntity.builder()
                .withId(9L)
                .withName("Generic Hall Name")
                .withCity("Big City")
                .withStreet("Some Street")
                .withHouseNumber(18)
                .build();
    }
}