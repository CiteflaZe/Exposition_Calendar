package com.project.service.impl;

import com.project.dao.ExpositionDao;
import com.project.domain.Exposition;
import com.project.entity.ExpositionEntity;
import com.project.exception.EntityNotFoundException;
import com.project.exception.ExpositionAlreadyExistException;
import com.project.service.mapper.ExpositionMapper;
import org.junit.After;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static com.project.MockData.MOCK_EXPOSITION;
import static com.project.MockData.MOCK_EXPOSITION_ENTITY;
import static java.util.Collections.emptyList;
import static java.util.Collections.singletonList;
import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class ExpositionServiceImplTest {

    private static final Exposition EXPOSITION = MOCK_EXPOSITION;
    private static final ExpositionEntity EXPOSITION_ENTITY = MOCK_EXPOSITION_ENTITY;


    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Mock
    private ExpositionDao expositionDao;

    @Mock
    private ExpositionMapper expositionMapper;

    @InjectMocks
    private ExpositionServiceImpl expositionService;

    @After
    public void resetMocks() {
        reset(expositionDao, expositionMapper);
    }

    @Test
    public void addShouldThrowIllegalArgumentException() {
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage("Exposition is null");

        expositionService.add(null);
    }

    @Test
    public void addShouldThrowIllegalArgumentExceptionWithIncorrectDates(){
        Exposition exposition = Exposition.builder()
                .withId(MOCK_EXPOSITION.getId())
                .withTitle(MOCK_EXPOSITION.getTitle())
                .withTheme(MOCK_EXPOSITION.getTheme())
                .withStartDate(MOCK_EXPOSITION.getEndDate())
                .withEndDate(MOCK_EXPOSITION.getStartDate())
                .withTicketPrice(MOCK_EXPOSITION.getTicketPrice())
                .withDescription(MOCK_EXPOSITION.getDescription())
                .withHall(MOCK_EXPOSITION.getHall())
                .build();

        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage("Start date is greater than end date");

        expositionService.add(exposition);
    }

    @Test
    public void addShouldThrowExpositionAlreadyExistException() {
        expectedException.expect(ExpositionAlreadyExistException.class);
        expectedException.expectMessage("Exposition with this title already exists");

        when(expositionDao.findByTitle(anyString())).thenReturn(Optional.of(EXPOSITION_ENTITY));

        expositionService.add(EXPOSITION);
    }

    @Test
    public void addShouldSaveExposition() {
        when(expositionDao.findByTitle(anyString())).thenReturn(Optional.empty());
        when(expositionMapper.mapExpositionToExpositionEntity(any(Exposition.class))).thenReturn(EXPOSITION_ENTITY);

        expositionService.add(EXPOSITION);

        verify(expositionDao).save(EXPOSITION_ENTITY);
    }

    @Test
    public void showByIdShouldThrowEntityNotFoundException(){
        expectedException.expect(EntityNotFoundException.class);
        expectedException.expectMessage("There is no expositions with this id");

        when(expositionDao.findById(anyLong())).thenReturn(Optional.empty());

        expositionService.showById(1L);
    }

    @Test
    public void showByIdShouldReturnExposition(){
        when(expositionDao.findById(anyLong())).thenReturn(Optional.of(EXPOSITION_ENTITY));
        when(expositionMapper.mapExpositionEntityToExposition(any(ExpositionEntity.class))).thenReturn(EXPOSITION);

        final Exposition actual = expositionService.showById(1L);

        assertThat(actual, is(EXPOSITION));
    }

    @Test
    public void showAllShouldReturnEmptyList() {
        when(expositionDao.findAll(anyInt(), anyInt())).thenReturn(emptyList());

        final List<Exposition> actual = expositionService.showAll(2, 3);

        verify(expositionDao).findAll(anyInt(), anyInt());
        assertThat(actual, is(emptyList()));
    }

    @Test
    public void showAllShouldReturnExpositionList() {
        when(expositionDao.findAll(anyInt(), anyInt())).thenReturn(singletonList(EXPOSITION_ENTITY));
        when(expositionMapper.mapExpositionEntityToExposition(any(ExpositionEntity.class))).thenReturn(EXPOSITION);

        final List<Exposition> actual = expositionService.showAll(2, 3);

        verify(expositionDao).findAll(anyInt(), anyInt());
        assertThat(actual, is(not(emptyList())));
        assertThat(actual, hasItem(EXPOSITION));
    }

    @Test
    public void showAllNotFinishedShouldReturnEmptyList() {
        when(expositionDao.findAllWhereEndDateGreaterThanNow(anyInt(), anyInt())).thenReturn(emptyList());

        final List<Exposition> actual = expositionService.showAllNotFinished(2, 3);

        verify(expositionDao).findAllWhereEndDateGreaterThanNow(anyInt(), anyInt());
        assertThat(actual, is(emptyList()));
    }

    @Test
    public void showAllNotFinishedShouldReturnExpositionList() {
        when(expositionDao.findAllWhereEndDateGreaterThanNow(anyInt(), anyInt())).thenReturn(singletonList(EXPOSITION_ENTITY));
        when(expositionMapper.mapExpositionEntityToExposition(any(ExpositionEntity.class))).thenReturn(EXPOSITION);

        final List<Exposition> actual = expositionService.showAllNotFinished(2, 3);

        verify(expositionDao).findAllWhereEndDateGreaterThanNow(anyInt(), anyInt());
        assertThat(actual, is(not(emptyList())));
        assertThat(actual, hasItem(EXPOSITION));
    }

    @Test
    public void showEntriesAmountShouldReturnEntriesAmount() {
        when(expositionDao.countEntries()).thenReturn(4L);

        final Long actual = expositionService.showEntriesAmount();

        assertThat(actual, is(4L));
    }

    @Test
    public void showNotFinishedEntriesAmountShouldReturnEntriesAmount() {
        when(expositionDao.countByEndDateGreaterThan()).thenReturn(4L);

        final Long actual = expositionService.showNotFinishedEntriesAmount();

        assertThat(actual, is(4L));
    }
}