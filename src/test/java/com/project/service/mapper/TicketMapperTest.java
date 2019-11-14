package com.project.service.mapper;

import com.project.domain.exposition.Exposition;
import com.project.domain.hall.Hall;
import com.project.domain.payment.Payment;
import com.project.domain.ticket.Ticket;
import com.project.domain.user.User;
import com.project.entity.exposition.ExpositionEntity;
import com.project.entity.hall.HallEntity;
import com.project.entity.payment.PaymentEntity;
import com.project.entity.ticket.TicketEntity;
import com.project.entity.user.UserEntity;
import org.hamcrest.MatcherAssert;
import org.hamcrest.core.Is;
import org.junit.Test;

import java.time.LocalDate;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.core.Is.*;

public class TicketMapperTest {

    private TicketMapper mapper = new TicketMapper();

    private final Ticket ticket = initTicket();

    private final TicketEntity ticketEntity = initTicketEntity();

    @Test
    public void mapTicketEntityToTicketShouldReturnTicket() {
        final Ticket actual = mapper.mapTicketEntityToTicket(ticketEntity);

        assertThat(actual.getId(), is(ticketEntity.getId()));
        assertThat(actual.getValidDate(), is(ticketEntity.getValidDate()));
        assertThat(actual.getUser().getId(), is(ticketEntity.getUser().getId()));
        assertThat(actual.getPayment().getId(), is(ticketEntity.getPayment().getId()));
        assertThat(actual.getExposition().getId(), is(ticketEntity.getExposition().getId()));
        assertThat(actual.getExposition().getTitle(), is(ticketEntity.getExposition().getTitle()));
        assertThat(actual.getHall().getId(), is(ticketEntity.getHall().getId()));
        assertThat(actual.getHall().getName(), is(ticketEntity.getHall().getName()));
    }

    @Test
    public void mapTicketToTicketEntityShouldReturnTicketEntity() {
        final TicketEntity actual = mapper.mapTicketToTicketEntity(ticket);

        assertThat(actual.getId(), is(ticket.getId()));
        assertThat(actual.getValidDate(), is(ticket.getValidDate()));
        assertThat(actual.getUser().getId(), is(ticket.getUser().getId()));
        assertThat(actual.getPayment().getId(), is(ticket.getPayment().getId()));
        assertThat(actual.getExposition().getId(), is(ticket.getExposition().getId()));
        assertThat(actual.getExposition().getTitle(), is(ticket.getExposition().getTitle()));
        assertThat(actual.getHall().getId(), is(ticket.getHall().getId()));
        assertThat(actual.getHall().getName(), is(ticket.getHall().getName()));
    }

    private Ticket initTicket() {
        return Ticket.builder()
                .withId(6L)
                .withValidDate(LocalDate.of(2019, 8, 9))
                .withUser(User.builder()
                        .withId(2L)
                        .build())
                .withPayment(Payment.builder()
                        .withId(3L)
                        .build())
                .withExposition(Exposition.builder()
                        .withId(4L)
                        .withTitle("Title")
                        .build())
                .withHall(Hall.builder()
                        .withId(5L)
                        .withName("Hall")
                        .build())
                .build();
    }

    private TicketEntity initTicketEntity() {
        return TicketEntity.builder()
                .withId(34L)
                .withValidDate(LocalDate.of(2019, 3, 21))
                .withUser(UserEntity.builder()
                        .withId(9L)
                        .build())
                .withPayment(PaymentEntity.builder()
                        .withId(11L)
                        .build())
                .withExposition(ExpositionEntity.builder()
                        .withId(10L)
                        .withTitle("Generic Title")
                        .build())
                .withHall(HallEntity.builder()
                        .withId(15L)
                        .withName("Simple Hall")
                        .build())
                .build();
    }
}