package com.github.muzhaleks.services.impl;

import com.github.muzhaleks.dao.CarDAO;
import com.github.muzhaleks.exceptions.CarServiceException;
import com.github.muzhaleks.exceptions.DAOException;
import com.github.muzhaleks.factory.DAOFactory;
import com.github.muzhaleks.model.Car;
import com.github.muzhaleks.services.CarService;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

public class CarServiceImpl implements CarService {
    private static final Logger LOGGER = LogManager.getLogger(CarService.class);
    private CarDAO carDAOImpl = DAOFactory.INSTANCE.getCarDao();


    @Override
    public ArrayList<Car> getAllCars() throws CarServiceException {
        List<Car> carsDAO = null;
        try {
            carsDAO = carDAOImpl.getAllCar();
        } catch (DAOException e) {
            LOGGER.warn(e);
            throw new CarServiceException(e);
        }

        return new ArrayList<>(carsDAO);
    }

    @Override
    public Car getCarByID(int carID) throws CarServiceException {
        try{
            return carDAOImpl.getCarByID(carID);
        } catch (DAOException e) {
            LOGGER.warn(e);
            throw new CarServiceException(e);
        }
    }

    @Override
    public void blockCarByOrder(int carID) throws CarServiceException {
        try {
            carDAOImpl.blockCarByOrder(carID);
        } catch (DAOException e) {
            LOGGER.warn(e);
            throw new CarServiceException(e);
        }
    }

    @Override
    public void unBlockCarByOrder(int carID) throws CarServiceException {
        try {
            carDAOImpl.unBlockCarByOrder(carID);
        } catch (DAOException e) {
            LOGGER.warn(e);
            throw new CarServiceException(e);
        }
    }
}
