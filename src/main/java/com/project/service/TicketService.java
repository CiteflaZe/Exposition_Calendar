package com.project.service;

import com.project.domain.Ticket;

import java.util.List;

public interface TicketService {

    boolean add(Ticket ticket);

    Ticket showOneByPaymentId(Long id);

    List<Ticket> showAllByPaymentIdAndUserId(Long paymentId, Long userId);

    List<Ticket> showAllByUserId(Long id);

}
