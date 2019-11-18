package com.project.service;

import com.project.domain.ticket.Ticket;

import java.time.LocalDate;
import java.util.List;

public interface TicketService {
    boolean add(Ticket ticket);

    List<Ticket> showByPaymentId(Long id);
}
