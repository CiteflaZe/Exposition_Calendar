package com.project.dao;

import com.project.domain.payment.Payment;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

public interface OrderDao extends CrudDao<Payment, Long> {
    List<Payment> findByStatus(String status);

    List<Payment> findByTimeRange(Timestamp from, Timestamp to);

    List<Payment> findByUserId(Long id);

    Optional<Payment> findByTicketId(Long id);

    List<Payment> findByExpositionId(Long id);
}
