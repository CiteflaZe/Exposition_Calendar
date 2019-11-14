package com.project.entity.ticket;

import com.project.entity.exposition.ExpositionEntity;
import com.project.entity.hall.HallEntity;
import com.project.entity.payment.PaymentEntity;
import com.project.entity.user.UserEntity;

import java.time.LocalDate;
import java.util.Objects;

public class TicketEntity {
    private final Long id;
    private final LocalDate validDate;
    private final UserEntity user;
    private final PaymentEntity payment;
    private final ExpositionEntity exposition;
    private final HallEntity hall;

    private TicketEntity(Builder builder) {
        this.id = builder.id;
        this.validDate = builder.validDate;
        this.exposition = builder.exposition;
        this.hall = builder.hall;
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

    public HallEntity getHall() {
        return hall;
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
                Objects.equals(hall, that.hall) &&
                Objects.equals(user, that.user) &&
                Objects.equals(payment, that.payment);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, validDate, exposition, hall, user, payment);
    }

    @Override
    public String toString() {
        return "TicketEntity{" +
                "id=" + id +
                ", expirationDate=" + validDate +
                ", exposition=" + exposition.getId() +
                ", hall=" + hall.getId() +
                ", user=" + user.getId() +
                ", payment=" + payment.getId() +
                '}';
    }

    public static class Builder {
        private Long id;
        private LocalDate validDate;
        private ExpositionEntity exposition;
        private HallEntity hall;
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

        public Builder withHall(HallEntity hall) {
            this.hall = hall;
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
