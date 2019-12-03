package com.project.service.mapper;

import com.project.domain.Ticket;
import com.project.entity.TicketEntity;
import org.junit.Test;

import static com.project.MockData.MOCK_TICKET;
import static com.project.MockData.MOCK_TICKET_ENTITY;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class TicketMapperTest {

    private static final Ticket TICKET = MOCK_TICKET;
    private static final TicketEntity TICKET_ENTITY = MOCK_TICKET_ENTITY;

    private TicketMapper ticketMapper = new TicketMapper();

    @Test
    public void mapTicketEntityToTicketShouldReturnTicket() {
        final Ticket actual = ticketMapper.mapTicketEntityToTicket(TICKET_ENTITY);

        assertThat(actual.getId(), is(MOCK_TICKET_ENTITY.getId()));
        assertThat(actual.getValidDate(), is(MOCK_TICKET_ENTITY.getValidDate()));
        assertThat(actual.getUser().getId(), is(MOCK_TICKET_ENTITY.getUser().getId()));
        assertThat(actual.getPayment().getId(), is(MOCK_TICKET_ENTITY.getPayment().getId()));
        assertThat(actual.getExposition().getId(), is(MOCK_TICKET_ENTITY.getExposition().getId()));
        assertThat(actual.getExposition().getTitle(), is(MOCK_TICKET_ENTITY.getExposition().getTitle()));
        assertThat(actual.getExposition().getHall().getId(), is(MOCK_TICKET_ENTITY.getExposition().getHall().getId()));
        assertThat(actual.getExposition().getHall().getName(), is(MOCK_TICKET_ENTITY.getExposition().getHall().getName()));
    }

    @Test
    public void mapTicketToTicketEntityShouldReturnTicketEntity() {
        final TicketEntity actual = ticketMapper.mapTicketToTicketEntity(TICKET);

        assertThat(actual.getId(), is(MOCK_TICKET.getId()));
        assertThat(actual.getValidDate(), is(MOCK_TICKET.getValidDate()));
        assertThat(actual.getUser().getId(), is(MOCK_TICKET.getUser().getId()));
        assertThat(actual.getPayment().getId(), is(MOCK_TICKET.getPayment().getId()));
        assertThat(actual.getExposition().getId(), is(MOCK_TICKET.getExposition().getId()));
        assertThat(actual.getExposition().getTitle(), is(MOCK_TICKET.getExposition().getTitle()));
        assertThat(actual.getExposition().getHall().getId(), is(MOCK_TICKET.getExposition().getHall().getId()));
        assertThat(actual.getExposition().getHall().getName(), is(MOCK_TICKET.getExposition().getHall().getName()));
    }
}