package com.project.service.impl;

import com.project.dao.HallDao;
import com.project.domain.Hall;
import com.project.entity.HallEntity;
import com.project.exception.HallAlreadyExistException;
import com.project.service.mapper.HallMapper;
import org.junit.After;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;
import java.util.Optional;

import static com.project.MockData.MOCK_HALL;
import static com.project.MockData.MOCK_HALL_ENTITY;
import static java.util.Collections.emptyList;
import static java.util.Collections.singletonList;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class HallServiceImplTest {

    private static final Hall HALL = MOCK_HALL;
    private static final HallEntity HALL_ENTITY = MOCK_HALL_ENTITY;

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Mock
    private HallDao hallDao;

    @Mock
    private HallMapper hallMapper;

    @InjectMocks
    private HallServiceImpl hallService;

    @After
    public void resetMocks() {
        reset(hallDao, hallMapper);
    }

    @Test
    public void addShouldThrowIllegalArgumentException() {
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage("Hall is null");

        hallService.add(null);
    }

    @Test
    public void addShouldThrowHallAlreadyExistException() {
        expectedException.expect(HallAlreadyExistException.class);
        expectedException.expectMessage("Hall with such name already exists");

        when(hallDao.findByName(anyString())).thenReturn(Optional.of(HALL_ENTITY));

        hallService.add(HALL);
    }

    @Test
    public void addShouldAddHall() {
        when(hallDao.findByName(anyString())).thenReturn(Optional.empty());
        when(hallMapper.mapHallToHallEntity(HALL)).thenReturn(HALL_ENTITY);

        hallService.add(HALL);

        verify(hallDao).save(HALL_ENTITY);
    }

    @Test
    public void showAllShouldReturnEmptyList() {
        when(hallDao.findAll()).thenReturn(emptyList());

        final List<Hall> halls = hallService.showAll();

        assertThat(halls, is(emptyList()));
    }

    @Test
    public void showAllShouldReturnHallList() {
        when(hallDao.findAll()).thenReturn(singletonList(HALL_ENTITY));
        when(hallMapper.mapHallEntityToHall(HALL_ENTITY)).thenReturn(HALL);

        final List<Hall> halls = hallService.showAll();

        assertThat(halls.size(), is(1));
        assertThat(halls.get(0), is(HALL));
    }

}