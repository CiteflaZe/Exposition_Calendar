package com.project.entity.hall;

import com.project.entity.exposition.ExpositionEntity;

import java.util.List;
import java.util.Objects;

public class HallEntity {
    private final Long id;
    private final String name;
    private final String city;
    private final String street;
    private final Integer houseNumber;
    private final List<ExpositionEntity> expositions;

    private HallEntity(Builder builder) {
        this.id = builder.id;
        this.name = builder.name;
        this.city = builder.city;
        this.street = builder.street;
        this.houseNumber = builder.houseNumber;
        this.expositions = builder.expositions;
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

    public List<ExpositionEntity> getExpositions() {
        return expositions;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        HallEntity that = (HallEntity) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(name, that.name) &&
                Objects.equals(city, that.city) &&
                Objects.equals(street, that.street) &&
                Objects.equals(houseNumber, that.houseNumber) &&
                Objects.equals(expositions, that.expositions);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, city, street, houseNumber, expositions);
    }

    @Override
    public String toString() {
        return "HallEntity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", city='" + city + '\'' +
                ", street='" + street + '\'' +
                ", houseNumber=" + houseNumber +
                ", expositions=" + expositions +
                '}';
    }

    public static class Builder {
        private Long id;
        private String name;
        private String city;
        private String street;
        private Integer houseNumber;
        private List<ExpositionEntity> expositions;

        private Builder() {
        }

        public HallEntity build() {
            return new HallEntity(this);
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

        public Builder withExpositions(List<ExpositionEntity> expositions) {
            this.expositions = expositions;
            return this;
        }
    }
}
