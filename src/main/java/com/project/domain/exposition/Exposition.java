package com.project.domain.exposition;

import com.project.domain.hall.Hall;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Objects;

public class Exposition {
    private final Long id;
    private final String title;
    private final String theme;
    private final Timestamp startTime;
    private final Timestamp finishTime;
    private final BigDecimal ticketPrice;
    private final String description;
    private final Hall hall;

    private Exposition(Builder builder) {
        this.id = builder.id;
        this.title = builder.title;
        this.theme = builder.theme;
        this.startTime = builder.startTime;
        this.finishTime = builder.finishTime;
        this.ticketPrice = builder.ticketPrice;
        this.description = builder.description;
        this.hall = builder.hall;
    }

    public static Builder builder() {
        return new Builder();
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getTheme() {
        return theme;
    }

    public Timestamp getStartTime() {
        return startTime;
    }

    public Timestamp getFinishTime() {
        return finishTime;
    }

    public BigDecimal getTicketPrice() {
        return ticketPrice;
    }

    public String getDescription() {
        return description;
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
        Exposition that = (Exposition) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(title, that.title) &&
                Objects.equals(theme, that.theme) &&
                Objects.equals(startTime, that.startTime) &&
                Objects.equals(finishTime, that.finishTime) &&
                Objects.equals(ticketPrice, that.ticketPrice) &&
                Objects.equals(description, that.description) &&
                Objects.equals(hall, that.hall);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, theme, startTime, finishTime, ticketPrice, description, hall);
    }

    @Override
    public String toString() {
        return "Exposition{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", theme='" + theme + '\'' +
                ", startTime=" + startTime +
                ", finishTime=" + finishTime +
                ", ticketPrice=" + ticketPrice +
                ", description='" + description + '\'' +
                ", hall=" + hall +
                '}';
    }

    public static class Builder {
        private Long id;
        private String title;
        private String theme;
        private Timestamp startTime;
        private Timestamp finishTime;
        private BigDecimal ticketPrice;
        private String description;
        private Hall hall;

        private Builder() {
        }

        public Exposition build() {
            return new Exposition(this);
        }

        public Builder withId(Long id) {
            this.id = id;
            return this;
        }

        public Builder withTitle(String title) {
            this.title = title;
            return this;
        }

        public Builder withTheme(String theme) {
            this.theme = theme;
            return this;
        }

        public Builder withStartTime(Timestamp startTime) {
            this.startTime = startTime;
            return this;
        }

        public Builder withFinishTime(Timestamp finishTime) {
            this.finishTime = finishTime;
            return this;
        }

        public Builder withTicketPrice(BigDecimal ticketPrice) {
            this.ticketPrice = ticketPrice;
            return this;
        }

        public Builder withDescription(String description) {
            this.description = description;
            return this;
        }

        public Builder withHall(Hall hall) {
            this.hall = hall;
            return this;
        }
    }

}
