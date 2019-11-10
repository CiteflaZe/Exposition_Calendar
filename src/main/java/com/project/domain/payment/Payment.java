package com.project.domain.payment;

import com.project.entity.payment.Status;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

public class Payment {
    private final Long id;
    private final LocalDateTime paymentTime;
    private final Status status;
    private final BigDecimal price;

    private Payment(Builder builder) {
        this.id = builder.id;
        this.paymentTime = builder.paymentTime;
        this.status = builder.status;
        this.price = builder.price;
    }

    public static Builder builder(){
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
                Objects.equals(price, payment.price);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, paymentTime, status, price);
    }
    
    public static class Builder{
        private Long id;
        private LocalDateTime paymentTime;
        private Status status;
        private BigDecimal price;

        private Builder(){}

        public Payment build(){
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

        public Builder withPrice(BigDecimal price) {
            this.price = price;
            return this;
        }
    }
}
