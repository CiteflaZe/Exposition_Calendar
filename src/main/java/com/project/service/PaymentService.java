package com.project.service;

import com.project.domain.payment.Payment;
import com.project.entity.payment.Status;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface PaymentService {
    boolean add(Payment payment);

    List<Payment> showAll(Integer rowCount, Integer startFrom);

    List<Payment> showByUserId(Long id);

    List<Payment> showByStatus(Status status);

    List<Payment> showByExpositionId(Long id);

    List<Payment> showByTimeRange(LocalDateTime from, LocalDateTime to);
}
