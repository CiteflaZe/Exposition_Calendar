package com.project.domain;

import java.util.Objects;

public class Hall {
    private final Long id;
    private final String name;
    private final String city;
    private final String street;
    private final Integer houseNumber;

    private Hall(Builder builder) {
        this.id = builder.id;
        this.name = builder.name;
        this.city = builder.city;
        this.street = builder.street;
        this.houseNumber = builder.houseNumber;
    }

    public static Builder builder() {
        return new Builder();
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCity() {
        return city;
    }

    public String getStreet() {
        return street;
    }

    public Integer getHouseNumber() {
        return houseNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Hall hall = (Hall) o;
        return Objects.equals(id, hall.id) &&
                Objects.equals(name, hall.name) &&
                Objects.equals(city, hall.city) &&
                Objects.equals(street, hall.street) &&
                Objects.equals(houseNumber, hall.houseNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, city, street, houseNumber);
    }

    @Override
    public String toString() {
        return "Hall{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", city='" + city + '\'' +
                ", street='" + street + '\'' +
                ", houseNumber=" + houseNumber +
                '}';
    }

    public static class Builder {
        private Long id;
        private String name;
        private String city;
        private String street;
        private Integer houseNumber;

        private Builder() {
        }

        public Hall build() {
            return new Hall(this);
        }

        public Builder withId(Long id) {
            this.id = id;
            return this;
        }

        public Builder withName(String name) {
            this.name = name;
            return this;
        }

        public Builder withCity(String city) {
            this.city = city;
            return this;
        }

        public Builder withStreet(String street) {
            this.street = street;
            return this;
        }

        public Builder withHouseNumber(Integer houseNumber) {
            this.houseNumber = houseNumber;
            return this;
        }
    }
}
