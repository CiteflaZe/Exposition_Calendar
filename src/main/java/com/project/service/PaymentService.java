package com.project.service;

import com.project.domain.payment.Payment;
import com.project.entity.payment.Status;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface PaymentService {
    boolean add(Payment payment);

    Optional<Payment> showLastPaymentByUserId(Long id);

    List<Payment> showAll(Integer rowCount, Integer startFrom);

    List<Payment> showByUserId(Long id);

    List<Payment> showByStatus(Status status);

    List<Payment> showByExpositionId(Long id);

    List<Payment> showByTimeRange(LocalDateTime from, LocalDateTime to);
}
