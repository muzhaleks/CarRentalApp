package com.github.muzhaleks.services.impl;

import com.github.muzhaleks.dao.CarDAO;
import com.github.muzhaleks.dao.OrderDAO;
import com.github.muzhaleks.dao.UserDAO;
import com.github.muzhaleks.exceptions.DAOException;
import com.github.muzhaleks.exceptions.OrderServiceException;
import com.github.muzhaleks.factory.DAOFactory;
import com.github.muzhaleks.model.Car;
import com.github.muzhaleks.model.Order;
import com.github.muzhaleks.model.User;
import com.github.muzhaleks.services.OrderService;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.util.List;

public class OrderServiceImpl implements OrderService {
    private static final Logger LOGGER = LogManager.getLogger(OrderServiceImpl.class);
    private OrderDAO orderDAOImpl = DAOFactory.INSTANCE.getOrderDao();
    private CarDAO carDAOImpl = DAOFactory.INSTANCE.getCarDao();
    private UserDAO userDAOImpl = DAOFactory.INSTANCE.getUserDao();

    @Override
    public List<Order> getOrderByUserID(long userID) throws OrderServiceException {
        try{
            return orderDAOImpl.getOrdersByUserID(userID);
        } catch (DAOException e) {
            LOGGER.warn(e);
            throw new OrderServiceException(e);
        }
    }

    @Override
    public void createOrderByUser(long userID, int carID, int rentHours, String notes) throws OrderServiceException {
        User user;
        Car car;
        try {
            user = userDAOImpl.getUserByID(userID);
        } catch (DAOException e) {
            LOGGER.warn(e);
            throw new OrderServiceException(e);
        }
        try{
            car = carDAOImpl.getCarByID(carID);
        } catch (DAOException e) {
            LOGGER.warn(e);
            throw new OrderServiceException(e);
        }

        try{
            orderDAOImpl.createOrder(user, car, rentHours, notes);
        } catch (DAOException e) {
            LOGGER.warn(e);
            throw new OrderServiceException(e);
        }
    }

    @Override
    public Order getOrderByOrderID(long orderID) throws OrderServiceException {
        try {
            return orderDAOImpl.getOrderByOrderID(orderID);
        } catch (DAOException e) {
            LOGGER.warn(e);
            throw new OrderServiceException(e);
        }
    }

    @Override
    public void paymentOrder(long orderID, float totalCost) throws OrderServiceException {
        Order order;
        try {
            order = orderDAOImpl.getOrderByOrderID(orderID);
        } catch (DAOException e) {
            LOGGER.warn(e);
            throw new OrderServiceException(e);
        }

        if(order.getTotalPrice() == totalCost){
            try {
                orderDAOImpl.changePaymentStatusOrderToApproved(orderID);
            } catch (DAOException e) {
                LOGGER.warn(e);
                throw new OrderServiceException(e);
            }
        }else {
            LOGGER.warn("Total cost and enter cost do not much");
            throw new OrderServiceException("Total cost and enter cost do not much");

        }
    }

    @Override
    public void changePaymentStatusOrderToApproved(long orderID) throws OrderServiceException {

    }

    @Override
    public void changeAdminStatusOrderToApproved(long orderID) throws OrderServiceException {

    }

    @Override
    public void changeAdminStatusOrderToBlock(long orderID) throws OrderServiceException {

    }

    @Override
    public void completeOrder(long orderID) throws OrderServiceException {
        Order order = getOrderByOrderID(orderID);

        int carID = order.getCar().getId();
        try {
            carDAOImpl.unBlockCarByOrder(carID);
        } catch (DAOException e) {
            LOGGER.warn(e);
            throw new OrderServiceException(e);
        }

        try {
            orderDAOImpl.completeOrder(orderID);
        } catch (DAOException e) {
            LOGGER.warn(e);
            throw new OrderServiceException(e);
        }
    }

    @Override
    public int getCountOrdersWhereAdminStatusFalse() throws OrderServiceException {
        int countOrdersWhereAdminStatusFalse = 0;
        try{
            countOrdersWhereAdminStatusFalse = orderDAOImpl.getCountOrdersWhereAdminStatusFalse();
        } catch (DAOException e) {
            LOGGER.warn(e);
            throw new OrderServiceException(e);
        }
        return countOrdersWhereAdminStatusFalse;
    }

    @Override
    public List <Order> getOrderByConfirmStatus() throws OrderServiceException {
        try {
            return orderDAOImpl.getOrdersByAdminStatus();
        } catch (DAOException e) {
            LOGGER.warn(e);
            throw new OrderServiceException(e);
        }
    }
}
