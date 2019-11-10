package com.project.entity.payment;

import com.project.entity.exposition.ExpositionEntity;
import com.project.entity.ticket.TicketEntity;
import com.project.entity.user.UserEntity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

public class PaymentEntity {
    private final Long id;
    private final LocalDateTime paymentTime;
    private final Status status;
    private final BigDecimal price;
    private final UserEntity user;
    private final ExpositionEntity exposition;
    private final List<TicketEntity> tickets;

    private PaymentEntity(Builder builder) {
        this.id = builder.id;
        this.paymentTime = builder.paymentTime;
        this.status = builder.status;
        this.price = builder.price;
        this.user = builder.user;
        this.tickets = builder.tickets;
        this.exposition = builder.exposition;
    }

    public static Builder builder() {
        return new Builder();
    }

    public Long getId() {
        return id;
    }

    public LocalDateTime getPaymentTime() {
        return paymentTime;
    }

    public Status getStatus() {
        return status;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public UserEntity getUser() {
        return user;
    }

    public List<TicketEntity> getTickets() {
        return tickets;
    }

    public ExpositionEntity getExposition() {
        return exposition;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        PaymentEntity that = (PaymentEntity) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(paymentTime, that.paymentTime) &&
                status == that.status &&
                Objects.equals(price, that.price) &&
                Objects.equals(user, that.user) &&
                Objects.equals(exposition, that.exposition) &&
                Objects.equals(tickets, that.tickets);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, paymentTime, status, price, user, exposition, tickets);
    }

    @Override
    public String toString() {
        return "PaymentEntity{" +
                "id=" + id +
                ", paymentTime=" + paymentTime +
                ", status=" + status +
                ", price=" + price +
                ", user=" + user +
                ", exposition=" + exposition +
                ", tickets=" + tickets +
                '}';
    }

    public static class Builder {
        private Long id;
        private LocalDateTime paymentTime;
        private Status status;
        private BigDecimal price;
        private UserEntity user;
        private ExpositionEntity exposition;
        private List<TicketEntity> tickets;

        private Builder() {
        }

        public PaymentEntity build() {
            return new PaymentEntity(this);
        }

        public Builder withId(Long id) {
            this.id = id;
            return this;
        }

        public Builder withPaymentTime(LocalDateTime paymentTime) {
            this.paymentTime = paymentTime;
            return this;
        }

        public Builder withStatus(Status status) {
            this.status = status;
            return this;
        }

        public Builder WithPrice(BigDecimal price) {
            this.price = price;
            return this;
        }

        public Builder withUser(UserEntity user) {
            this.user = user;
            return this;
        }

        public Builder withExposition(ExpositionEntity exposition) {
            this.exposition = exposition;
            return this;
        }

        public Builder withTickets(List<TicketEntity> tickets) {
            this.tickets = tickets;
            return this;
        }
    }
}
