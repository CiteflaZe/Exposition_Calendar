package com.project.domain;

import java.time.LocalDate;
import java.util.Objects;

public class Ticket {
    private final Long id;
    private final LocalDate validDate;
    private final User user;
    private final Payment payment;
    private final Exposition exposition;

    private Ticket(Builder builder) {
        this.id = builder.id;
        this.validDate = builder.validDate;
        this.user = builder.user;
        this.payment = builder.payment;
        this.exposition = builder.exposition;
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

    public User getUser() {
        return user;
    }

    public Payment getPayment() {
        return payment;
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
        Ticket ticket = (Ticket) o;
        return Objects.equals(id, ticket.id) &&
                Objects.equals(validDate, ticket.validDate) &&
                Objects.equals(user, ticket.user) &&
                Objects.equals(payment, ticket.payment) &&
                Objects.equals(exposition, ticket.exposition);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, validDate, user, payment, exposition);
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "id=" + id +
                ", validDate=" + validDate +
                ", user=" + user +
                ", payment=" + payment +
                ", exposition=" + exposition +
                '}';
    }

    public static class Builder {
        private Long id;
        private LocalDate validDate;
        private User user;
        private Payment payment;
        private Exposition exposition;

        private Builder() {
        }

        public Ticket build() {
            return new Ticket(this);
        }

        public Builder withId(Long id) {
            this.id = id;
            return this;
        }

        public Builder withValidDate(LocalDate validDate) {
            this.validDate = validDate;
            return this;
        }

        public Builder withUser(User user) {
            this.user = user;
            return this;
        }

        public Builder withPayment(Payment payment) {
            this.payment = payment;
            return this;
        }

        public Builder withExposition(Exposition exposition) {
            this.exposition = exposition;
            return this;
        }
    }
}
