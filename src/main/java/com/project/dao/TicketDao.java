package com.project.dao;

import com.project.domain.ticket.Ticket;

import java.sql.Date;
import java.util.List;

public interface TicketDao extends CrudDao<Ticket, Long> {
    List<Ticket> findByExpirationDateRange(Date from, Date to);

    List<Ticket> findByUserId(Long id);

    List<Ticket> findByExpositionId(Long id);
}
