package com.github.muzhaleks.dao.impl;

import com.github.muzhaleks.connectionpool.ConnectionPool;
import com.github.muzhaleks.connectionpool.ConnectionProxy;
import com.github.muzhaleks.dao.CarDAO;
import com.github.muzhaleks.exceptions.DAOException;
import com.github.muzhaleks.model.Car;
import com.github.muzhaleks.model.CarMark;
import com.github.muzhaleks.model.CarModel;
import com.github.muzhaleks.model.CarStatus;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CarDAOImpl implements CarDAO {

    private static final Logger LOGGER = LogManager.getLogger(CarDAOImpl.class);
    //fields
    private static final String CAR_ID = "id";
    private static final String CAR_MARK = "mark";
    private static final String CAR_MODEL = "model";
    private static final String CAR_MILLAGE = "millage";
    private static final String CAR_PRICE = "price";
    private static final String CAR_STATUS = "car_status";

    //query
    private static final String GET_ALL_CARS_QUERY = "SELECT car.id, m2.mark, a.model, millage, price, auto.car_status from car\n" +
            "join car_mark m2 on car.mark_id = m2.id join car_model a on\n" +
            "car.model_id = a.id join car_status auto on car.car_status_id = auto.id;";

    private static final String BLOCK_CAR_BY_ORDER = "UPDATE car SET car_status_id = 2 WHERE id = ";

    private static final String UNBLOCK_CAR_BY_ORDER = "UPDATE car SET car_status_id = 1 WHERE id = ";

    private static final String GET_CAR_BY_ID_QUERY = "SELECT car.id, m2.mark, a.model, millage, price, auto.car_status from car\n" +
            "join car_mark m2 on car.mark_id = m2.id join car_model a on\n" +
            "car.model_id = a.id join car_status auto on car.car_status_id = auto.id WHERE car.id = ";


    @Override
    public List<Car> getAllCar() throws DAOException {
        List <Car> cars = new ArrayList<>();
        try (
                ConnectionProxy connection = new ConnectionProxy(ConnectionPool.INSTANCE.getConnection());
                PreparedStatement preparedStatement = connection.prepareStatement(GET_ALL_CARS_QUERY);
                ResultSet resultSet = preparedStatement.executeQuery();
        ) {
            while (resultSet.next()) {
                Car car = new Car();
                CarMark carMark = new CarMark();
                CarStatus carStatus = new CarStatus();
                CarModel carModel = new CarModel();

                car.setId(resultSet.getInt(CAR_ID));
                carMark.setMark(resultSet.getString(CAR_MARK));
                car.setMark(carMark);
                carModel.setModelName(resultSet.getString(CAR_MODEL));
                car.setModel(carModel);
                car.setMillage(resultSet.getInt(CAR_MILLAGE));
                car.setPrice(resultSet.getFloat(CAR_PRICE));
                carStatus.setCarStatus(resultSet.getString(CAR_STATUS));
                car.setCarStatus(carStatus);
                cars.add(car);
            }

        } catch (SQLException e) {
            LOGGER.warn(e);
            throw new DAOException("Can`t get cars list");
        }
        return cars;
    }

    @Override
    public Car getCarByID(int carID) throws DAOException {
        Car car = new Car();
        try (
                ConnectionProxy connection = new ConnectionProxy(ConnectionPool.INSTANCE.getConnection());
                PreparedStatement preparedStatement = connection.prepareStatement(GET_CAR_BY_ID_QUERY + carID);
                ResultSet resultSet = preparedStatement.executeQuery();
        ) {
            if (resultSet.next()) {
                CarMark carMark = new CarMark();
                CarStatus carStatus = new CarStatus();
                CarModel carModel = new CarModel();

                car.setId(resultSet.getInt(CAR_ID));
                carMark.setMark(resultSet.getString(CAR_MARK));
                car.setMark(carMark);
                carModel.setModelName(resultSet.getString(CAR_MODEL));
                car.setModel(carModel);
                car.setMillage(resultSet.getInt(CAR_MILLAGE));
                car.setPrice(resultSet.getFloat(CAR_PRICE));
                carStatus.setCarStatus(resultSet.getString(CAR_STATUS));
                car.setCarStatus(carStatus);
            }
        } catch (SQLException e) {
            LOGGER.warn(e);
            throw new DAOException("Can`t get car by carID");
        }
        return car;
    }

    @Override
    public void blockCarByOrder(int carID) throws DAOException {
        try (
                ConnectionProxy connection = new ConnectionProxy(ConnectionPool.INSTANCE.getConnection());
                PreparedStatement preparedStatement = connection.prepareStatement(BLOCK_CAR_BY_ORDER + carID);
        ) {
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.warn(e);
            throw new DAOException("Can not block car");
        }
    }

    @Override
    public void unBlockCarByOrder(int carID) throws DAOException {
        try (
                ConnectionProxy connection = new ConnectionProxy(ConnectionPool.INSTANCE.getConnection());
                PreparedStatement preparedStatement = connection.prepareStatement(UNBLOCK_CAR_BY_ORDER + carID);
        ) {
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.warn(e);
            throw new DAOException("Can not unblock car");
        }
    }
}
