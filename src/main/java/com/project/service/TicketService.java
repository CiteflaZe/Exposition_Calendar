package com.project.service;

import com.project.domain.ticket.Ticket;

import java.time.LocalDate;
import java.util.List;

public interface TicketService {
    boolean add(Ticket ticket);

    List<Ticket> showByUserId(Long id);

    List<Ticket> showByValidDateRange(LocalDate from, LocalDate to);

    List<Ticket> showByExpositionId(Long id);
}
