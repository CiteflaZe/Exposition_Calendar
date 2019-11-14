package com.project.domain.ticket;

import com.project.domain.exposition.Exposition;
import com.project.domain.hall.Hall;
import com.project.domain.payment.Payment;
import com.project.domain.user.User;

import java.time.LocalDate;
import java.util.Objects;

public class Ticket {
    private final Long id;
    private final LocalDate validDate;
    private final User user;
    private final Payment payment;
    private final Exposition exposition;
    private final Hall hall;

    private Ticket(Builder builder) {
        this.id = builder.id;
        this.validDate = builder.validDate;
        this.user = builder.user;
        this.payment = builder.payment;
        this.exposition = builder.exposition;
        this.hall = builder.hall;
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

    public Hall getHall() {
        return hall;
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
                Objects.equals(exposition, ticket.exposition) &&
                Objects.equals(hall, ticket.hall);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, validDate, user, payment, exposition, hall);
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "id=" + id +
                ", validDate=" + validDate +
                ", user=" + user +
                ", payment=" + payment +
                ", exposition=" + exposition +
                ", hall=" + hall +
                '}';
    }

    public static class Builder {
        private Long id;
        private LocalDate validDate;
        private User user;
        private Payment payment;
        private Exposition exposition;
        private Hall hall;

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

        public Builder withHall(Hall hall) {
            this.hall = hall;
            return this;
        }
    }
}
