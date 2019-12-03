package com.project.dao;

import com.project.entity.PaymentEntity;

import java.util.List;
import java.util.Optional;

public interface PaymentDao extends CrudDao<PaymentEntity, Long> {

    Optional<PaymentEntity> findLastByUserId(Long id);

    List<PaymentEntity> findAllByUserId(Long id);

}
