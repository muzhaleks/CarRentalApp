package com.github.muzhaleks.model;

import java.io.Serializable;
import java.util.Objects;

public class CarStatus implements Serializable {

    private int id;
    private String carStatus;

    public CarStatus() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCarStatus() {
        return carStatus;
    }

    public void setCarStatus(String carStatus) {
        this.carStatus = carStatus;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        CarStatus carStatus1 = (CarStatus) object;
        return id == carStatus1.id &&
                Objects.equals(carStatus, carStatus1.carStatus);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, carStatus);
    }

    @Override
    public String toString() {
        return "CarStatus{" +
                "id=" + id +
                ", carStatus='" + carStatus + '\'' +
                '}';
    }
}
