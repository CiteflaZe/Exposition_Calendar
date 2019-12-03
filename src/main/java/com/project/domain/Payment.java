package com.project.domain;

import com.project.entity.Status;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

public class Payment {
    private final Long id;
    private final LocalDateTime paymentTime;
    private final Status status;
    private final Integer ticketAmount;
    private final BigDecimal price;
    private final User user;
    private final Exposition exposition;

    private Payment(Builder builder) {
        this.id = builder.id;
        this.paymentTime = builder.paymentTime;
        this.status = builder.status;
        this.ticketAmount = builder.ticketAmount;
        this.price = builder.price;
        this.user = builder.user;
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

    public Integer getTicketAmount() {
        return ticketAmount;
    }

    public User getUser() {
        return user;
    }

    public Exposition getExposition() {
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
        Payment payment = (Payment) o;
        return Objects.equals(id, payment.id) &&
                Objects.equals(paymentTime, payment.paymentTime) &&
                status == payment.status &&
                Objects.equals(ticketAmount, payment.ticketAmount) &&
                Objects.equals(price, payment.price) &&
                Objects.equals(user, payment.user) &&
                Objects.equals(exposition, payment.exposition);
    }

    @Override
    public String toString() {
        return "Payment{" +
                "id=" + id +
                ", paymentTime=" + paymentTime +
                ", status=" + status +
                ", ticketAmount=" + ticketAmount +
                ", price=" + price +
                ", user=" + user +
                ", exposition=" + exposition +
                '}';
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, paymentTime, status, ticketAmount, price, user, exposition);
    }

    public static class Builder {
        private Long id;
        private LocalDateTime paymentTime;
        private Status status;
        private Integer ticketAmount;
        private BigDecimal price;
        private User user;
        private Exposition exposition;

        private Builder() {
        }

        public Payment build() {
            return new Payment(this);
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

        public Builder withTicketAmount(Integer ticketAmount) {
            this.ticketAmount = ticketAmount;
            return this;
        }

        public Builder withPrice(BigDecimal price) {
            this.price = price;
            return this;
        }

        public Builder withUser(User user) {
            this.user = user;
            return this;
        }

        public Builder withExposition(Exposition exposition) {
            this.exposition = exposition;
            return this;
        }
    }
}
