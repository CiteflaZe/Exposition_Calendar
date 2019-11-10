package com.project.service.mapper;

import com.project.domain.ticket.Ticket;
import com.project.entity.ticket.TicketEntity;

public class TicketMapper {
    public Ticket mapTicketEntityToTicket(TicketEntity ticketEntity){
        return Ticket.builder()
                .withId(ticketEntity.getId())
                .withValidDate(ticketEntity.getValidDate())
                .build();
    }

    public TicketEntity mapTicketToTicketEntity(Ticket ticket){
        return TicketEntity.builder()
                .withId(ticket.getId())
                .withValidDate(ticket.getValidDate())
                .build();
    }

}
