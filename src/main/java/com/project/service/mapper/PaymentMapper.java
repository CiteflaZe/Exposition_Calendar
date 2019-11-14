package com.project.service.mapper;

import com.project.domain.exposition.Exposition;
import com.project.domain.payment.Payment;
import com.project.domain.user.User;
import com.project.entity.exposition.ExpositionEntity;
import com.project.entity.payment.PaymentEntity;
import com.project.entity.user.UserEntity;

public class PaymentMapper {
    public Payment mapPaymentEntityToPayment(PaymentEntity paymentEntity){
        return Payment.builder()
                .withId(paymentEntity.getId())
                .withPaymentTime(paymentEntity.getPaymentTime())
                .withStatus(paymentEntity.getStatus())
                .withTicketAmount(paymentEntity.getTicketAmount())
                .withPrice(paymentEntity.getPrice())
                .withUser(User.builder()
                        .withId(paymentEntity.getUser().getId())
                        .build())
                .withExposition(Exposition.builder()
                        .withId(paymentEntity.getExposition().getId())
                        .build())
                .build();
    }

    public PaymentEntity mapPaymentToPaymentEntity(Payment payment){
        return PaymentEntity.builder()
                .withId(payment.getId())
                .withPaymentTime(payment.getPaymentTime())
                .withStatus(payment.getStatus())
                .withTicketAmount(payment.getTicketAmount())
                .withPrice(payment.getPrice())
                .withUser(UserEntity.builder()
                        .withId(payment.getUser().getId())
                        .build())
                .withExposition(ExpositionEntity.builder()
                        .withId(payment.getExposition().getId())
                        .build())
                .build();
    }
}
