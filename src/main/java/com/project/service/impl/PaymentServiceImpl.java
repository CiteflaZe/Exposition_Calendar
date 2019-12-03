package com.project.service.impl;

import com.project.dao.PaymentDao;
import com.project.domain.Payment;
import com.project.entity.PaymentEntity;
import com.project.exception.EntityNotFoundException;
import com.project.service.PaymentService;
import com.project.service.mapper.PaymentMapper;
import org.apache.log4j.Logger;

import java.util.List;
import java.util.stream.Collectors;

public class PaymentServiceImpl implements PaymentService {
    private static final Logger LOGGER = Logger.getLogger(PaymentServiceImpl.class);
    private final PaymentDao paymentDao;
    private final PaymentMapper mapper;

    public PaymentServiceImpl(PaymentDao paymentDao, PaymentMapper mapper) {
        this.paymentDao = paymentDao;
        this.mapper = mapper;
    }

    @Override
    public boolean add(Payment payment) {
        if (payment == null || payment.getPrice().doubleValue() <= 0) {
            LOGGER.warn("Payment can't be null and (or) it's price can't be less or equals to zero");
            throw new IllegalArgumentException("Invalid payment parameters");
        }
        return paymentDao.save(mapper.mapPaymentToPaymentEntity(payment));
    }

    @Override
    public List<Payment> showAllByUserId(Long id) {
        final List<PaymentEntity> payments = paymentDao.findAllByUserId(id);
        return mapPaymentEntityListToPaymentList(payments);
    }

    @Override
    public Payment showLastByUserId(Long id) {
        return paymentDao.findLastByUserId(id)
                .map(mapper::mapPaymentEntityToPayment)
                .orElseThrow(() -> new EntityNotFoundException("No payments found"));
    }

    private List<Payment> mapPaymentEntityListToPaymentList(List<PaymentEntity> entities) {
        return entities.stream()
                .map(mapper::mapPaymentEntityToPayment)
                .collect(Collectors.toList());
    }
}
