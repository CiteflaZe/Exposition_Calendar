package com.project.service.impl;

import com.project.dao.PaymentDao;
import com.project.domain.Payment;
import com.project.entity.PaymentEntity;
import com.project.exception.EntityNotFoundException;
import com.project.service.mapper.PaymentMapper;
import org.junit.After;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;
import java.util.Optional;

import static com.project.MockData.MOCK_PAYMENT;
import static com.project.MockData.MOCK_PAYMENT_ENTITY;
import static java.util.Collections.emptyList;
import static java.util.Collections.singletonList;
import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class PaymentServiceImplTest {

    private static final Payment PAYMENT = MOCK_PAYMENT;
    private static final PaymentEntity PAYMENT_ENTITY = MOCK_PAYMENT_ENTITY;

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Mock
    private PaymentDao paymentDao;

    @Mock
    private PaymentMapper paymentMapper;

    @InjectMocks
    private PaymentServiceImpl paymentService;

    @After
    public void resetMocks() {
        reset(paymentDao, paymentMapper);
    }

    @Test
    public void addShouldThrowIllegalArgumentException() {
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage("Invalid payment parameters");

        paymentService.add(null);
    }

    @Test
    public void addShouldAddPayment() {
        when(paymentMapper.mapPaymentToPaymentEntity(any(Payment.class))).thenReturn(PAYMENT_ENTITY);

        paymentService.add(PAYMENT);

        verify(paymentDao).save(PAYMENT_ENTITY);
    }

    @Test
    public void showAllByUserIdShouldReturnEmptyList() {
        when(paymentDao.findAllByUserId(anyLong())).thenReturn(emptyList());

        final List<Payment> actual = paymentService.showAllByUserId(4L);

        assertThat(actual, is(emptyList()));
    }

    @Test
    public void showAllByUserIdShouldReturnPaymentsList() {
        when(paymentDao.findAllByUserId(anyLong())).thenReturn(singletonList(PAYMENT_ENTITY));
        when(paymentMapper.mapPaymentEntityToPayment(any(PaymentEntity.class))).thenReturn(PAYMENT);

        final List<Payment> actual = paymentService.showAllByUserId(5L);

        assertThat(actual, is(not(emptyList())));
        assertThat(actual, hasItem(PAYMENT));
    }

    @Test
    public void showLastByUserIdShouldThrowInvalidEntityException() {
        expectedException.expect(EntityNotFoundException.class);
        expectedException.expectMessage("No payments found");

        when(paymentDao.findLastByUserId(anyLong())).thenReturn(Optional.empty());

        paymentService.showLastByUserId(4L);
    }

    @Test
    public void showLastByUserIdShouldReturnPayment() {
        when(paymentDao.findLastByUserId(anyLong())).thenReturn(Optional.of(PAYMENT_ENTITY));
        when(paymentMapper.mapPaymentEntityToPayment(any(PaymentEntity.class))).thenReturn(PAYMENT);

        final Payment actual = paymentService.showLastByUserId(9L);

        assertThat(actual, is(PAYMENT));
    }
}