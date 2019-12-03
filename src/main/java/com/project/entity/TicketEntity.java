package com.project.entity;

import java.time.LocalDate;
import java.util.Objects;

public class TicketEntity {
    private final Long id;
    private final LocalDate validDate;
    private final UserEntity user;
    private final PaymentEntity payment;
    private final ExpositionEntity exposition;

    private TicketEntity(Builder builder) {
        this.id = builder.id;
        this.validDate = builder.validDate;
        this.exposition = builder.exposition;
        this.user = builder.user;
        this.payment = builder.payment;
    }

    public static Builder builder() {
        return new Builder();
    }

    public Long getId() {
        return id;
    }

    public LocalDate getValidDate() {
        return validDate;
    }

    public ExpositionEntity getExposition() {
        return exposition;
    }

    public UserEntity getUser() {
        return user;
    }

    public PaymentEntity getPayment() {
        return payment;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        TicketEntity that = (TicketEntity) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(validDate, that.validDate) &&
                Objects.equals(exposition, that.exposition) &&
                Objects.equals(user, that.user) &&
                Objects.equals(payment, that.payment);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, validDate, exposition, user, payment);
    }

    @Override
    public String toString() {
        return "TicketEntity{" +
                "id=" + id +
                ", expirationDate=" + validDate +
                ", exposition=" + exposition.getId() +
                ", user=" + user.getId() +
                ", payment=" + payment.getId() +
                '}';
    }

    public static class Builder {
        private Long id;
        private LocalDate validDate;
        private ExpositionEntity exposition;
        private UserEntity user;
        private PaymentEntity payment;

        private Builder() {
        }

        public TicketEntity build() {
            return new TicketEntity(this);
        }

        public Builder withId(Long id) {
            this.id = id;
            return this;
        }

        public Builder withValidDate(LocalDate validDate) {
            this.validDate = validDate;
            return this;
        }

        public Builder withExposition(ExpositionEntity exposition) {
            this.exposition = exposition;
            return this;
        }

        public Builder withUser(UserEntity user) {
            this.user = user;
            return this;
        }

        public Builder withPayment(PaymentEntity payment) {
            this.payment = payment;
            return this;
        }
    }
}
