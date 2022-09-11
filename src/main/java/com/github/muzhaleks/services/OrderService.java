package com.github.muzhaleks.services;

import com.github.muzhaleks.exceptions.OrderServiceException;
import com.github.muzhaleks.model.Order;

import java.util.List;

public interface OrderService {
    List<Order> getOrderByUserID(long userID) throws OrderServiceException;
    void createOrderByUser(long userID, int carID, int rentHours, String notes)throws OrderServiceException;
    Order getOrderByOrderID(long orderID) throws OrderServiceException;
    void paymentOrder(long orderID, float totalCost) throws OrderServiceException;
    void changePaymentStatusOrderToApproved(long orderID) throws OrderServiceException;
    void changeAdminStatusOrderToApproved(long orderID) throws OrderServiceException;
    void changeAdminStatusOrderToBlock(long orderID) throws OrderServiceException;
    void completeOrder(long orderID) throws OrderServiceException;
    int getCountOrdersWhereAdminStatusFalse() throws OrderServiceException;
    List<Order> getOrderByConfirmStatus() throws OrderServiceException;
}
