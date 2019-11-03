package com.project.dao;

import com.project.entity.payment.PaymentEntity;
import com.project.entity.payment.Status;

import java.sql.Timestamp;
import java.util.List;

public interface PaymentDao extends CrudDao<PaymentEntity, Long> {
    List<PaymentEntity> findByTimeRange(Timestamp from, Timestamp to);

    List<PaymentEntity> findByStatus(Status status);

    List<PaymentEntity> findByUserId(Long id);

    List<PaymentEntity> findByExpositionId(Long id);
}
