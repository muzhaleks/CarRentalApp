package com.github.muzhaleks.services;

import com.github.muzhaleks.exceptions.CarServiceException;
import com.github.muzhaleks.model.Car;

import java.util.ArrayList;

public interface CarService {
    ArrayList<Car> getAllCars() throws CarServiceException;
    Car getCarByID(int carID)throws CarServiceException;
    void blockCarByOrder(int carID) throws CarServiceException;
    void unBlockCarByOrder(int carID) throws CarServiceException;
}
