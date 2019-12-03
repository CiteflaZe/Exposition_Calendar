package com.project.service;

import com.project.domain.Payment;

import java.util.List;
import java.util.Optional;

public interface PaymentService {
    boolean add(Payment payment);

    List<Payment> showAllByUserId(Long id);

    Payment showLastByUserId(Long id);
}
