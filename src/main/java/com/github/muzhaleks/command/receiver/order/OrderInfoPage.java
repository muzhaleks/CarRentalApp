package com.github.muzhaleks.command.receiver.order;

import com.github.muzhaleks.command.Command;
import com.github.muzhaleks.command.PageEnum;
import com.github.muzhaleks.exceptions.OrderServiceException;
import com.github.muzhaleks.factory.ServiceFactory;
import com.github.muzhaleks.model.Order;
import com.github.muzhaleks.services.OrderService;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class OrderInfoPage implements Command {
    private static final Logger LOGGER = LogManager.getLogger(OrderInfoPage.class);
    private OrderService orderServiceImpl = ServiceFactory.INSTANCE.getOrderService();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String page;
        Order order;
        long orderID = Long.parseLong(request.getParameter("orderID"));

        try {
            order = orderServiceImpl.getOrderByOrderID(orderID);
        } catch (OrderServiceException e) {
            String message = "Order is not found";
            request.setAttribute("informMessage", message);
            page = PageEnum.INFORMER_PAGE_JSP.getValue();
            return page;
        }
        request.setAttribute("order", order);

        page = PageEnum.ORDER_INFO_PAGE.getValue();
        return page;
    }
}
