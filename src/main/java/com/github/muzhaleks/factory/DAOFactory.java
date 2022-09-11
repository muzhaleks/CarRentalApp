package com.github.muzhaleks.factory;

import com.github.muzhaleks.dao.CarDAO;
import com.github.muzhaleks.dao.OrderDAO;
import com.github.muzhaleks.dao.UserDAO;
import com.github.muzhaleks.dao.impl.CarDAOImpl;
import com.github.muzhaleks.dao.impl.OrderDAOImpl;
import com.github.muzhaleks.dao.impl.UserDAOImpl;

public enum  DAOFactory {

    INSTANCE;

    private UserDAO userDAOImpl = new UserDAOImpl();
    private OrderDAO orderDAOImpl = new OrderDAOImpl();
    private CarDAO carDAOImpl = new CarDAOImpl();

    DAOFactory() {
    }


    public UserDAO getUserDao() {
        return userDAOImpl;
    }

    public CarDAO getCarDao() {
        return carDAOImpl;
    }

    public OrderDAO getOrderDao() {
        return orderDAOImpl;
    }
}
