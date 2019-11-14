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

public class TicketMapper {
    public Ticket mapTicketEntityToTicket(TicketEntity ticketEntity){
        return Ticket.builder()
                .withId(ticketEntity.getId())
                .withValidDate(ticketEntity.getValidDate())
                .withUser(User.builder()
                        .withId(ticketEntity.getUser().getId())
                        .build())
                .withPayment(Payment.builder()
                        .withId(ticketEntity.getPayment().getId())
                        .build())
                .withExposition(Exposition.builder()
                        .withId(ticketEntity.getExposition().getId())
                        .withTitle(ticketEntity.getExposition().getTitle())
                        .build())
                .withHall(Hall.builder()
                        .withId(ticketEntity.getHall().getId())
                        .withName(ticketEntity.getHall().getName())
                        .build())
                .build();
    }

    public TicketEntity mapTicketToTicketEntity(Ticket ticket){
        return TicketEntity.builder()
                .withId(ticket.getId())
                .withValidDate(ticket.getValidDate())
                .withUser(UserEntity.builder()
                        .withId(ticket.getUser().getId())
                        .build())
                .withPayment(PaymentEntity.builder()
                        .withId(ticket.getPayment().getId())
                        .build())
                .withExposition(ExpositionEntity.builder()
                        .withId(ticket.getExposition().getId())
                        .withTitle(ticket.getExposition().getTitle())
                        .build())
                .withHall(HallEntity.builder()
                        .withId(ticket.getHall().getId())
                        .withName(ticket.getHall().getName())
                        .build())
                .build();
    }

}
