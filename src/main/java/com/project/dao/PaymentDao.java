package com.project.dao;

import com.project.entity.payment.PaymentEntity;
import com.project.entity.payment.Status;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface PaymentDao extends CrudDao<PaymentEntity, Long> {
    List<PaymentEntity> findByTimeRange(LocalDateTime from, LocalDateTime to);

    List<PaymentEntity> findByStatus(Status status);

    List<PaymentEntity> findByUserId(Long id);

    List<PaymentEntity> findByExpositionId(Long id);
}
