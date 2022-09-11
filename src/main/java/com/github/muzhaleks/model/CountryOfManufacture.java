package com.github.muzhaleks.model;

import java.io.Serializable;
import java.util.Objects;

public class CountryOfManufacture implements Serializable {

    private int id;
    private String country;

    public CountryOfManufacture() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CountryOfManufacture that = (CountryOfManufacture) o;
        return id == that.id &&
                Objects.equals(country, that.country);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, country);
    }

    @Override
    public String toString() {
        return "CountryOfManufacture{" +
                "id=" + id +
                ", country='" + country + '\'' +
                '}';
    }
}
