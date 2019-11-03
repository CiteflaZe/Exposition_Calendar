package com.project.entity.exposition;

import com.project.entity.hall.HallEntity;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.Objects;

public class ExpositionEntity {
    private final Long id;
    private final String title;
    private final String theme;
    private final LocalDate startTime;
    private final LocalDate finishTime;
    private final BigDecimal ticketPrice;
    private final String description;
    private final HallEntity hall;

    private ExpositionEntity(Builder builder) {
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

    public LocalDate getStartTime() {
        return startTime;
    }

    public LocalDate getFinishTime() {
        return finishTime;
    }

    public BigDecimal getTicketPrice() {
        return ticketPrice;
    }

    public String getDescription() {
        return description;
    }

    public HallEntity getHall() {
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
        ExpositionEntity that = (ExpositionEntity) o;
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
        return "ExpositionEntity{" +
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
        private LocalDate startTime;
        private LocalDate finishTime;
        private BigDecimal ticketPrice;
        private String description;
        private HallEntity hall;

        private Builder() {
        }

        public ExpositionEntity build() {
            return new ExpositionEntity(this);
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

        public Builder withStartTime(LocalDate startTime) {
            this.startTime = startTime;
            return this;
        }

        public Builder withFinishTime(LocalDate finishTime) {
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

        public Builder withHall(HallEntity hall) {
            this.hall = hall;
            return this;
        }
    }
}
