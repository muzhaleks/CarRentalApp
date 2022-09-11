package com.github.muzhaleks.command.receiver.order;

import com.github.muzhaleks.command.Command;
import com.github.muzhaleks.command.PageEnum;
import com.github.muzhaleks.exceptions.CarServiceException;
import com.github.muzhaleks.exceptions.OrderServiceException;
import com.github.muzhaleks.factory.ServiceFactory;
import com.github.muzhaleks.model.Order;
import com.github.muzhaleks.services.CarService;
import com.github.muzhaleks.services.OrderService;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

public class CreateOrder implements Command {
    private static final Logger LOGGER = LogManager.getLogger(CreateOrder.class);
    private OrderService orderServiceImpl = ServiceFactory.INSTANCE.getOrderService();
    private CarService carServiceImpl = ServiceFactory.INSTANCE.getCarService();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String page;
        HttpSession session = request.getSession();
        long userID = (long) session.getAttribute("ActiveUserId");
        int orderCarID = Integer.parseInt(request.getParameter("orderCarID"));
        int rentHours = Integer.parseInt(request.getParameter("rentHours"));
        String notes = request.getParameter("notes");

        try {
            orderServiceImpl.createOrderByUser(userID, orderCarID, rentHours, notes);
            try {
                carServiceImpl.blockCarByOrder(orderCarID);

            } catch (CarServiceException e) {
                String message = "Can not order this car";
                request.setAttribute("informMessage", message);
                page = PageEnum.INFORMER_PAGE_JSP.getValue();
                return page;
            }
        } catch (OrderServiceException e) {
            String message = "Can not create order";
            request.setAttribute("informMessage", message);
            page = PageEnum.INFORMER_PAGE_JSP.getValue();
            return page;
        }

        List<Order> orders = null;
        try {
            orders = orderServiceImpl.getOrderByUserID(userID);
        } catch (OrderServiceException OrderServiceException) {
            String message = "Orders not found";
            request.setAttribute("informMessage", message);
            page = PageEnum.INFORMER_PAGE_JSP.getValue();
            return page;
        }
        request.setAttribute("orders", orders);
        return PageEnum.ORDERS_PAGE.getValue();
    }
}
