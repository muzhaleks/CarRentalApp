package com.github.muzhaleks.dao;

import com.github.muzhaleks.exceptions.DAOException;
import com.github.muzhaleks.model.Car;

import java.util.List;

public interface CarDAO {
    List<Car> getAllCar() throws DAOException;
    Car getCarByID(int carID) throws DAOException;
    void blockCarByOrder(int carID) throws DAOException;
    void unBlockCarByOrder(int carID) throws DAOException;
}
