package com.project.service.mapper;

import com.project.domain.payment.Payment;
import com.project.entity.payment.PaymentEntity;

public class PaymentMapper {
    public Payment mapPaymentEntityToPayment(PaymentEntity paymentEntity){
        return Payment.builder()
                .withId(paymentEntity.getId())
                .withPaymentTime(paymentEntity.getPaymentTime())
                .withStatus(paymentEntity.getStatus())
                .withAmount(paymentEntity.getAmount())
                .build();
    }

    public PaymentEntity mapPaymentToPaymentEntity(Payment payment){
        return PaymentEntity.builder()
                .withId(payment.getId())
                .withPaymentTime(payment.getPaymentTime())
                .withStatus(payment.getStatus())
                .withAmount(payment.getAmount())
                .build();
    }
}