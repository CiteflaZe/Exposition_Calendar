package com.project.service.mapper;

import com.project.domain.Exposition;
import com.project.domain.Hall;
import com.project.domain.Payment;
import com.project.domain.Ticket;
import com.project.domain.User;
import com.project.entity.ExpositionEntity;
import com.project.entity.HallEntity;
import com.project.entity.PaymentEntity;
import com.project.entity.TicketEntity;
import com.project.entity.UserEntity;

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
                        .withHall(Hall.builder()
                                .withId(ticketEntity.getExposition().getHall().getId())
                                .withName(ticketEntity.getExposition().getHall().getName())
                                .build())
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
                        .withHall(HallEntity.builder()
                                .withId(ticket.getExposition().getHall().getId())
                                .withName(ticket.getExposition().getHall().getName())
                                .build())
                        .build())
                .build();
    }

}
