package com.project.service.mapper;

import com.project.domain.exposition.Exposition;
import com.project.domain.payment.Payment;
import com.project.domain.user.User;
import com.project.entity.exposition.ExpositionEntity;
import com.project.entity.payment.PaymentEntity;
import com.project.entity.payment.Status;
import com.project.entity.user.UserEntity;
import org.junit.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;


public class PaymentMapperTest {

    private PaymentMapper mapper = new PaymentMapper();

    private final Payment payment = initPayment();

    private final PaymentEntity paymentEntity = initPaymentEntity();

    @Test
    public void mapPaymentEntityToPaymentShouldReturnPayment() {
        final Payment actual = mapper.mapPaymentEntityToPayment(paymentEntity);

        assertThat(actual.getId(), is(paymentEntity.getId()));
        assertThat(actual.getPaymentTime(), is(paymentEntity.getPaymentTime()));
        assertThat(actual.getStatus(), is(paymentEntity.getStatus()));
        assertThat(actual.getTicketAmount(), is(paymentEntity.getTicketAmount()));
        assertThat(actual.getUser().getId(), is(paymentEntity.getUser().getId()));
        assertThat(actual.getExposition().getId(), is(paymentEntity.getExposition().getId()));
    }

    @Test
    public void mapPaymentToPaymentEntityShouldReturnPaymentEntity() {
        final PaymentEntity actual = mapper.mapPaymentToPaymentEntity(payment);

        assertThat(actual.getId(), is(payment.getId()));
        assertThat(actual.getPaymentTime(), is(payment.getPaymentTime()));
        assertThat(actual.getStatus(), is(payment.getStatus()));
        assertThat(actual.getTicketAmount(), is(payment.getTicketAmount()));
        assertThat(actual.getUser().getId(), is(payment.getUser().getId()));
        assertThat(actual.getExposition().getId(), is(payment.getExposition().getId()));
    }

    private Payment initPayment() {
        return Payment.builder()
                .withId(9L)
                .withPaymentTime(LocalDateTime.of(LocalDate.of(2019, 11, 11), LocalTime.MAX))
                .withStatus(Status.PASSED)
                .withTicketAmount(4)
                .withPrice(BigDecimal.valueOf(47.10))
                .withUser(User.builder()
                        .withId(3L)
                        .build())
                .withExposition(Exposition.builder()
                        .withId(4L)
                        .build())
                .build();
    }

    public PaymentEntity initPaymentEntity() {
        return PaymentEntity.builder()
                .withId(6L)
                .withPaymentTime(LocalDateTime.of(LocalDate.of(2019, 4, 21), LocalTime.MIN))
                .withStatus(Status.FAILED)
                .withTicketAmount(9)
                .withPrice(BigDecimal.valueOf(452.12))
                .withUser(UserEntity.builder()
                        .withId(1L)
                        .build())
                .withExposition(ExpositionEntity.builder()
                        .withId(43L)
                        .build())
                .build();
    }
}