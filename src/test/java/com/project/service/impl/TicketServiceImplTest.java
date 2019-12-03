package com.project.service.impl;

import com.project.dao.TicketDao;
import com.project.domain.Ticket;
import com.project.entity.TicketEntity;
import com.project.exception.EntityNotFoundException;
import com.project.service.mapper.TicketMapper;
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

import static com.project.MockData.MOCK_TICKET;
import static com.project.MockData.MOCK_TICKET_ENTITY;
import static java.util.Collections.emptyList;
import static java.util.Collections.singletonList;
import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class TicketServiceImplTest {

    private static final Ticket TICKET = MOCK_TICKET;
    private static final TicketEntity TICKET_ENTITY = MOCK_TICKET_ENTITY;

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Mock
    private TicketDao ticketDao;

    @Mock
    private TicketMapper ticketMapper;

    @InjectMocks
    private TicketServiceImpl ticketService;

    @After
    public void resetMock() {
        reset(ticketDao, ticketMapper);
    }

    @Test
    public void addShouldThrowIllegalArgumentException() {
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage("Ticket is null");

        ticketService.add(null);
    }

    @Test
    public void addShouldReturnTicket() {
        when(ticketMapper.mapTicketToTicketEntity(any(Ticket.class))).thenReturn(TICKET_ENTITY);

        ticketService.add(TICKET);

        verify(ticketDao).save(TICKET_ENTITY);
    }

    @Test
    public void showOneByPaymentIdShouldThrowInvalidEntityException() {
        expectedException.expect(EntityNotFoundException.class);
        expectedException.expectMessage("No tickets found");

        when(ticketDao.findFirstByPaymentId(anyLong())).thenReturn(Optional.empty());

        ticketService.showOneByPaymentId(4L);
    }

    @Test
    public void showOneByPaymentIdShouldReturnTicket() {
        when(ticketDao.findFirstByPaymentId(anyLong())).thenReturn(Optional.of(TICKET_ENTITY));
        when(ticketMapper.mapTicketEntityToTicket(any(TicketEntity.class))).thenReturn(TICKET);

        final Ticket actual = ticketService.showOneByPaymentId(4L);

        assertThat(actual, is(TICKET));
    }

    @Test
    public void showAllByPaymentIdAndUserIdShouldReturnEmptyEmptyList() {
        when(ticketDao.findByPaymentIdAndUserId(anyLong(), anyLong())).thenReturn(emptyList());

        final List<Ticket> actual = ticketService.showAllByPaymentIdAndUserId(1L, 2L);

        assertThat(actual, is(emptyList()));
    }

    @Test
    public void showAllByPaymentIdAndUserIdShouldReturnTicketsList() {
        when(ticketDao.findByPaymentIdAndUserId(anyLong(), anyLong())).thenReturn(singletonList(TICKET_ENTITY));
        when(ticketMapper.mapTicketEntityToTicket(any(TicketEntity.class))).thenReturn(TICKET);

        final List<Ticket> actual = ticketService.showAllByPaymentIdAndUserId(2L, 4L);

        assertThat(actual, is(not(emptyList())));
        assertThat(actual, hasItem(TICKET));

    }
}