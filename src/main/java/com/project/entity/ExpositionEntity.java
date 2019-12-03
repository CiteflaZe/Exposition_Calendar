package com.project.entity;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

public class ExpositionEntity {
    private final Long id;
    private final String title;
    private final String theme;
    private final LocalDate startDate;
    private final LocalDate endDate;
    private final BigDecimal ticketPrice;
    private final String description;
    private final HallEntity hall;

    private ExpositionEntity(Builder builder) {
        this.id = builder.id;
        this.title = builder.title;
        this.theme = builder.theme;
        this.startDate = builder.startDate;
        this.endDate = builder.endDate;
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

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
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
                Objects.equals(startDate, that.startDate) &&
                Objects.equals(endDate, that.endDate) &&
                Objects.equals(ticketPrice, that.ticketPrice) &&
                Objects.equals(description, that.description) &&
                Objects.equals(hall, that.hall);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, theme, startDate, endDate, ticketPrice, description, hall);
    }

    @Override
    public String toString() {
        return "ExpositionEntity{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", theme='" + theme + '\'' +
                ", startTime=" + startDate +
                ", finishTime=" + endDate +
                ", ticketPrice=" + ticketPrice +
                ", description='" + description + '\'' +
                ", hall=" + hall +
                '}';
    }

    public static class Builder {
        private Long id;
        private String title;
        private String theme;
        private LocalDate startDate;
        private LocalDate endDate;
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

        public Builder withStartDate(LocalDate startDate) {
            this.startDate = startDate;
            return this;
        }

        public Builder withEndDate(LocalDate endDate) {
            this.endDate = endDate;
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
