package com.github.muzhaleks.command.receiver.pages;

import com.github.muzhaleks.command.Command;
import com.github.muzhaleks.command.PageEnum;
import com.github.muzhaleks.factory.ServiceFactory;
import com.github.muzhaleks.services.CarService;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class CarInfoPage implements Command {
    private static final Logger LOGGER = LogManager.getLogger(CarInfoPage.class);
    private CarService carServiceImpl = ServiceFactory.INSTANCE.getCarService();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String page;
        HttpSession session = request.getSession();

        String infoCarMark = request.getParameter("infoCarMark");
        String infoCarModel = request.getParameter("infoCarModel");

        request.setAttribute("infoCarMark", infoCarMark);
        request.setAttribute("infoCarModel", infoCarModel);

        return PageEnum.CAR_INFO_PAGE.getValue();
    }
}
