package com.github.muzhaleks.command.receiver.car;

import com.github.muzhaleks.command.Command;
import com.github.muzhaleks.command.PageEnum;
import com.github.muzhaleks.exceptions.CarServiceException;
import com.github.muzhaleks.factory.ServiceFactory;
import com.github.muzhaleks.model.Car;
import com.github.muzhaleks.services.CarService;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;

public class CarsList implements Command {
    private CarService carServiceImpl = ServiceFactory.INSTANCE.getCarService();
    private static final Logger LOGGER = LogManager.getLogger(CarsList.class);
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String page;
        HttpSession session = request.getSession();
        ArrayList<Car> cars = null;
        try {
            cars = carServiceImpl.getAllCars();
        } catch (CarServiceException e) {
            String message = "Can not get cars list";
            request.setAttribute("informMessage", message);
            page = PageEnum.INFORMER_PAGE_JSP.getValue();
            return page;
        }
        request.setAttribute("cars", cars);
        page = PageEnum.CARS_LIST_JSP.getValue();
        return page;
    }
}
