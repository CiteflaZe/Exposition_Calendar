package com.project.domain.ticket;

import com.project.domain.exposition.Exposition;
import com.project.domain.hall.Hall;

import java.time.LocalDate;
import java.util.Objects;

public class Ticket {
    private final Long id;
    private final LocalDate expirationDate;
    private final Exposition exposition;
    private final Hall hall;

    private Ticket(Builder builder) {
        this.id = builder.id;
        this.expirationDate = builder.expirationDate;
        this.exposition = builder.exposition;
        this.hall = builder.hall;
    }
    
    public static Builder builder(){
        return new Builder();
    }

    public Long getId() {
        return id;
    }

    public LocalDate getExpirationDate() {
        return expirationDate;
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
                Objects.equals(expirationDate, ticket.expirationDate) &&
                Objects.equals(exposition, ticket.exposition) &&
                Objects.equals(hall, ticket.hall);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, expirationDate, exposition, hall);
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "id=" + id +
                ", expirationDate=" + expirationDate +
                ", exposition=" + exposition +
                ", hall=" + hall +
                '}';
    }
    
    public static class Builder{
        private Long id;
        private LocalDate expirationDate;
        private Exposition exposition;
        private Hall hall;
        
        private Builder(){}
        
        public Ticket build(){
            return new Ticket(this);
        }

        public Builder withId(Long id) {
            this.id = id;
            return this;
        }

        public Builder withExpirationDate(LocalDate expirationDate) {
            this.expirationDate = expirationDate;
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
