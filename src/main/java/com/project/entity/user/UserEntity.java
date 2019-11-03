package com.project.entity.user;

import com.project.entity.payment.PaymentEntity;
import com.project.entity.ticket.TicketEntity;

import java.util.List;
import java.util.Objects;

public class UserEntity {
    private final Long id;
    private final String name;
    private final String surname;
    private final String email;
    private final String password;
    private final Role role;
    private final List<PaymentEntity> payments;
    private final List<TicketEntity> tickets;

    private UserEntity(Builder builder) {
        this.id = builder.id;
        this.name = builder.name;
        this.surname = builder.surname;
        this.email = builder.email;
        this.password = builder.password;
        this.role = builder.role;
        this.payments = builder.payments;
        this.tickets = builder.tickets;
    }

    public static Builder builder(){
        return new Builder();
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public Role getRole() {
        return role;
    }

    public List<PaymentEntity> getPayments() {
        return payments;
    }

    public List<TicketEntity> getTickets() {
        return tickets;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        UserEntity that = (UserEntity) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(name, that.name) &&
                Objects.equals(surname, that.surname) &&
                Objects.equals(email, that.email) &&
                Objects.equals(password, that.password) &&
                role == that.role &&
                Objects.equals(payments, that.payments) &&
                Objects.equals(tickets, that.tickets);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, surname, email, password, role, payments, tickets);
    }

    @Override
    public String toString() {
        return "UserEntity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", role=" + role +
                ", payments=" + payments +
                ", tickets=" + tickets +
                '}';
    }
    
    public static class Builder{
        private Long id;
        private String name;
        private String surname;
        private String email;
        private String password;
        private Role role;
        private List<PaymentEntity> payments;
        private List<TicketEntity> tickets;
        
        private Builder(){}
        
        public UserEntity build(){
            return new UserEntity(this);
        }

        public Builder withId(Long id) {
            this.id = id;
            return this;
        }

        public Builder withName(String name) {
            this.name = name;
            return this;
        }

        public Builder withSurname(String surname) {
            this.surname = surname;
            return this;
        }

        public Builder withEmail(String email) {
            this.email = email;
            return this;
        }

        public Builder withPassword(String password) {
            this.password = password;
            return this;
        }

        public Builder withRole(Role role) {
            this.role = role;
            return this;
        }

        public Builder withPayments(List<PaymentEntity> payments) {
            this.payments = payments;
            return this;
        }

        public Builder withTickets(List<TicketEntity> tickets) {
            this.tickets = tickets;
            return this;
        }
    }
}
