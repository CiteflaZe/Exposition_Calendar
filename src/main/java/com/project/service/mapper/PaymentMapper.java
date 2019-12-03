package com.project.service.mapper;

import com.project.domain.Exposition;
import com.project.domain.Payment;
import com.project.domain.User;
import com.project.entity.ExpositionEntity;
import com.project.entity.PaymentEntity;
import com.project.entity.UserEntity;

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
