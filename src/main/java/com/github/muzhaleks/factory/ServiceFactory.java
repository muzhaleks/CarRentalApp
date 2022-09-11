package com.github.muzhaleks.factory;

import com.github.muzhaleks.services.CarService;
import com.github.muzhaleks.services.OrderService;
import com.github.muzhaleks.services.UserService;
import com.github.muzhaleks.services.impl.CarServiceImpl;
import com.github.muzhaleks.services.impl.OrderServiceImpl;
import com.github.muzhaleks.services.impl.UserServiceImpl;

public enum ServiceFactory {
    INSTANCE;

    private UserService userServiceImpl = new UserServiceImpl();
    private CarService carServiceImpl = new CarServiceImpl();
    private OrderService orderServiceImpl = new OrderServiceImpl();


    ServiceFactory() {
    }

    public UserService getUserService() {
        return userServiceImpl;
    }

    public CarService getCarService() {
        return carServiceImpl;
    }

    public OrderService getOrderService(){
        return orderServiceImpl;
    }
}
