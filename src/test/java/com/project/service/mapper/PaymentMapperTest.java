package com.project.service.mapper;

import com.project.domain.Payment;
import com.project.entity.PaymentEntity;
import org.junit.Test;

import static com.project.MockData.MOCK_PAYMENT;
import static com.project.MockData.MOCK_PAYMENT_ENTITY;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class PaymentMapperTest {

    private static final Payment PAYMENT = MOCK_PAYMENT;
    private static final PaymentEntity PAYMENT_ENTITY = MOCK_PAYMENT_ENTITY;

    private final PaymentMapper paymentMapper = new PaymentMapper();

    @Test
    public void mapPaymentEntityToPaymentShouldReturnPayment() {
        final Payment actual = paymentMapper.mapPaymentEntityToPayment(PAYMENT_ENTITY);

        assertThat(actual.getId(), is(MOCK_PAYMENT_ENTITY.getId()));
        assertThat(actual.getPaymentTime(), is(MOCK_PAYMENT_ENTITY.getPaymentTime()));
        assertThat(actual.getStatus(), is(MOCK_PAYMENT_ENTITY.getStatus()));
        assertThat(actual.getTicketAmount(), is(MOCK_PAYMENT_ENTITY.getTicketAmount()));
        assertThat(actual.getUser().getId(), is(MOCK_PAYMENT_ENTITY.getUser().getId()));
        assertThat(actual.getExposition().getId(), is(MOCK_PAYMENT_ENTITY.getExposition().getId()));
    }

    @Test
    public void mapPaymentToPaymentEntityShouldReturnPaymentEntity() {
        final PaymentEntity actual = paymentMapper.mapPaymentToPaymentEntity(PAYMENT);

        assertThat(actual.getId(), is(MOCK_PAYMENT.getId()));
        assertThat(actual.getPaymentTime(), is(MOCK_PAYMENT.getPaymentTime()));
        assertThat(actual.getStatus(), is(MOCK_PAYMENT.getStatus()));
        assertThat(actual.getTicketAmount(), is(MOCK_PAYMENT.getTicketAmount()));
        assertThat(actual.getUser().getId(), is(MOCK_PAYMENT.getUser().getId()));
        assertThat(actual.getExposition().getId(), is(MOCK_PAYMENT.getExposition().getId()));
    }
}