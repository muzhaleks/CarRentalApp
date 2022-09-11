package com.github.muzhaleks.model;

import java.io.Serializable;
import java.util.Objects;

public class CarModel implements Serializable {

    private int id;
    private String modelName;
    private CarMark carMark;

    public CarModel() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getModelName() {
        return modelName;
    }

    public void setModelName(String modelName) {
        this.modelName = modelName;
    }

    public CarMark getCarMark() {
        return carMark;
    }

    public void setCarMark(CarMark carMark) {
        this.carMark = carMark;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        CarModel carModel = (CarModel) object;
        return id == carModel.id &&
                Objects.equals(modelName, carModel.modelName) &&
                Objects.equals(carMark, carModel.carMark);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, modelName, carMark);
    }

    @Override
    public String toString() {
        return "CarModel{" +
                "id=" + id +
                ", modelName='" + modelName + '\'' +
                ", carMark=" + carMark +
                '}';
    }
}
