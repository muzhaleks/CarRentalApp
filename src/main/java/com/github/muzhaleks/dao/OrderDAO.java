package com.github.muzhaleks.dao;

import com.github.muzhaleks.exceptions.DAOException;
import com.github.muzhaleks.model.Car;
import com.github.muzhaleks.model.Order;
import com.github.muzhaleks.model.User;

import java.util.List;

public interface OrderDAO {
    void createOrder(User user, Car car, int rentHours, String notes) throws DAOException;
    Order getOrderByOrderID(long orderID) throws DAOException;
    List<Order> getOrdersByUserID(long userID) throws DAOException;
    List<Order> getOrdersByAdminStatus() throws DAOException;
    int getCountOrdersWhereAdminStatusFalse() throws DAOException;
    void changePaymentStatusOrderToApproved(long orderID) throws DAOException;
    void changeAdminStatusOrderToApproved(long orderID) throws DAOException;
    void changeAdminStatusOrderToBlock(long orderID) throws DAOException;
    void completeOrder(long orderID) throws DAOException;
}
