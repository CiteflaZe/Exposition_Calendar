package com.project.dao;


import com.project.entity.TicketEntity;

import java.util.List;
import java.util.Optional;

public interface TicketDao extends CrudDao<TicketEntity, Long> {

    List<TicketEntity> findByPaymentIdAndUserId(Long paymentId, Long userId);

    Optional<TicketEntity> findFirstByPaymentId(Long id);

}
