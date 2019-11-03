package com.project.dao;


import com.project.entity.ticket.TicketEntity;

import java.time.LocalDate;
import java.util.List;

public interface TicketDao extends CrudDao<TicketEntity, Long> {
    List<TicketEntity> findByExpirationDateRange(LocalDate from, LocalDate to);

    List<TicketEntity> findByUserId(Long id);

    List<TicketEntity> findByExpositionId(Long id);

    List<TicketEntity> findByHallId(Long id);

    List<TicketEntity> findByPaymentId(Long id);
}
