package com.project.service.impl;

import com.project.dao.PaymentDao;
import com.project.domain.payment.Payment;
import com.project.entity.payment.PaymentEntity;
import com.project.entity.payment.Status;
import com.project.exception.InvalidEntityException;
import com.project.service.PaymentService;
import com.project.service.mapper.PaymentMapper;
import org.apache.log4j.Logger;

import java.time.LocalDateTime;
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
            throw new InvalidEntityException("Invalid payment parameters");
        }
        return paymentDao.save(mapper.mapPaymentToPaymentEntity(payment));
    }

    @Override
    public List<Payment> showAll(Integer rowCount, Integer startFrom) {
        final List<PaymentEntity> entities = paymentDao.findAll(rowCount, startFrom);
        return mapPaymentEntityListToPaymentList(entities);
    }

    @Override
    public List<Payment> showByUserId(Long id) {
        final List<PaymentEntity> entities = paymentDao.findByUserId(id);
        return mapPaymentEntityListToPaymentList(entities);
    }

    @Override
    public List<Payment> showByStatus(Status status) {
        final List<PaymentEntity> entities = paymentDao.findByStatus(status);
        return mapPaymentEntityListToPaymentList(entities);
    }

    @Override
    public List<Payment> showByExpositionId(Long id) {
        final List<PaymentEntity> entities = paymentDao.findByExpositionId(id);
        return mapPaymentEntityListToPaymentList(entities);
    }

    @Override
    public List<Payment> showByTimeRange(LocalDateTime from, LocalDateTime to) {
        final List<PaymentEntity> entities = paymentDao.findByTimeRange(from, to);
        return mapPaymentEntityListToPaymentList(entities);
    }

    private List<Payment> mapPaymentEntityListToPaymentList(List<PaymentEntity> entities) {
        return entities.stream()
                .map(mapper::mapPaymentEntityToPayment)
                .collect(Collectors.toList());
    }
}
