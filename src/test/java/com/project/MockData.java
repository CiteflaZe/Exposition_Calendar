package com.project;

import com.project.domain.Exposition;
import com.project.domain.Hall;
import com.project.domain.Payment;
import com.project.domain.Ticket;
import com.project.domain.User;
import com.project.entity.ExpositionEntity;
import com.project.entity.HallEntity;
import com.project.entity.PaymentEntity;
import com.project.entity.Role;
import com.project.entity.Status;
import com.project.entity.TicketEntity;
import com.project.entity.UserEntity;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class MockData {
    private static final Long USER_ID = 4L;
    private static final String USER_EMAIL = "email@gmail.com";
    private static final String USER_PASSWORD = "userpass1";
    private static final String USER_NAME = "Name";
    private static final String USER_SURNAME = "Surname";
    private static final Role USER_ROLE = Role.USER;

    private static final Long HALL_ID = 4L;
    private static final String HALL_NAME = "Great Hall";
    private static final String HALL_CITY = "Kiev";
    private static final String HALL_STREET = "Some Street";
    private static final Integer HALL_HOUSE_NUMBER = 15;

    private static final Long EXPOSITION_ID = 5L;
    private static final String EXPOSITION_TITLE = "Generic Title";
    private static final String EXPOSITION_THEME = "Theme";
    private static final LocalDate EXPOSITION_START_DATE = LocalDate.of(2019, 11, 12);
    private static final LocalDate EXPOSITION_END_DATE = LocalDate.of(2019, 12, 13);
    private static final BigDecimal EXPOSITION_TICKET_PRICE = BigDecimal.valueOf(28.25);
    private static final String EXPOSITION_DESCRIPTION = "Description";

    private static final Long PAYMENT_ID = 9L;
    private static final LocalDateTime PAYMENT_TIME = LocalDateTime.of(2019, 11, 12, 14, 15, 16);
    private static final Status PAYMENT_STATUS = Status.PASSED;
    private static final BigDecimal PAYMENT_PRICE = BigDecimal.valueOf(804.25);
    private static final Integer PAYMENT_TICKETS_AMOUNT = 6;

    private static final Long TICKET_ID = 5L;
    private static final LocalDate TICKET_VALID_DATE = LocalDate.of(2019, 12, 13);

    public static final User MOCK_USER = initUser();
    public static final UserEntity MOCK_USER_ENTITY = initUserEntity();
    public static final Hall MOCK_HALL = initHall();
    public static final HallEntity MOCK_HALL_ENTITY = initHallEntity();
    public static final Exposition MOCK_EXPOSITION = initExposition();
    public static final ExpositionEntity MOCK_EXPOSITION_ENTITY = initExpositionEntity();
    public static final Payment MOCK_PAYMENT = initPayment();
    public static final PaymentEntity MOCK_PAYMENT_ENTITY = initPaymentEntity();
    public static final Ticket MOCK_TICKET = initTicket();
    public static final TicketEntity MOCK_TICKET_ENTITY = initTicketEntity();

    private static User initUser() {
        return User.builder()
                .withId(USER_ID)
                .withEmail(USER_EMAIL)
                .withPassword(USER_PASSWORD)
                .withName(USER_NAME)
                .withSurname(USER_SURNAME)
                .withRole(USER_ROLE)
                .build();
    }

    private static UserEntity initUserEntity() {
        return UserEntity.builder()
                .withId(USER_ID)
                .withEmail(USER_EMAIL)
                .withPassword(USER_PASSWORD)
                .withName(USER_NAME)
                .withSurname(USER_SURNAME)
                .withRole(USER_ROLE)
                .build();
    }

    private static Hall initHall() {
        return Hall.builder()
                .withName(HALL_NAME)
                .withCity(HALL_CITY)
                .withStreet(HALL_STREET)
                .withHouseNumber(HALL_HOUSE_NUMBER)
                .build();
    }

    private static HallEntity initHallEntity() {
        return HallEntity.builder()
                .withId(HALL_ID)
                .withName(HALL_NAME)
                .withCity(HALL_CITY)
                .withStreet(HALL_STREET)
                .withHouseNumber(HALL_HOUSE_NUMBER)
                .build();
    }

    private static Exposition initExposition() {
        return Exposition.builder()
                .withTitle(EXPOSITION_TITLE)
                .withTheme(EXPOSITION_THEME)
                .withStartDate(EXPOSITION_START_DATE)
                .withEndDate(EXPOSITION_END_DATE)
                .withTicketPrice(EXPOSITION_TICKET_PRICE)
                .withDescription(EXPOSITION_DESCRIPTION)
                .withHall(MOCK_HALL)
                .build();
    }

    private static ExpositionEntity initExpositionEntity() {
        return ExpositionEntity.builder()
                .withId(EXPOSITION_ID)
                .withTitle(EXPOSITION_TITLE)
                .withTheme(EXPOSITION_THEME)
                .withStartDate(EXPOSITION_START_DATE)
                .withEndDate(EXPOSITION_END_DATE)
                .withTicketPrice(EXPOSITION_TICKET_PRICE)
                .withDescription(EXPOSITION_DESCRIPTION)
                .withHall(MOCK_HALL_ENTITY)
                .build();
    }

    private static Payment initPayment() {
        return Payment.builder()
                .withPaymentTime(PAYMENT_TIME)
                .withStatus(PAYMENT_STATUS)
                .withPrice(PAYMENT_PRICE)
                .withTicketAmount(PAYMENT_TICKETS_AMOUNT)
                .withExposition(MOCK_EXPOSITION)
                .withUser(MOCK_USER)
                .build();
    }

    private static PaymentEntity initPaymentEntity() {
        return PaymentEntity.builder()
                .withId(PAYMENT_ID)
                .withPaymentTime(PAYMENT_TIME)
                .withStatus(PAYMENT_STATUS)
                .withPrice(PAYMENT_PRICE)
                .withTicketAmount(PAYMENT_TICKETS_AMOUNT)
                .withExposition(MOCK_EXPOSITION_ENTITY)
                .withUser(MOCK_USER_ENTITY)
                .build();
    }

    private static Ticket initTicket() {
        return Ticket.builder()
                .withId(TICKET_ID)
                .withValidDate(TICKET_VALID_DATE)
                .withUser(MOCK_USER)
                .withExposition(MOCK_EXPOSITION)
                .withPayment(MOCK_PAYMENT)
                .build();
    }

    private static TicketEntity initTicketEntity() {
        return TicketEntity.builder()
                .withId(TICKET_ID)
                .withValidDate(TICKET_VALID_DATE)
                .withUser(MOCK_USER_ENTITY)
                .withExposition(MOCK_EXPOSITION_ENTITY)
                .withPayment(MOCK_PAYMENT_ENTITY)
                .build();
    }
}
