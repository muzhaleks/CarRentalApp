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
import java.util.List;

public class OrderByConfirmStatus implements Command {
    private static final Logger LOGGER = LogManager.getLogger(OrderByConfirmStatus.class);
    private OrderService orderServiceImpl = ServiceFactory.INSTANCE.getOrderService();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String page;
        List<Order> orders = null;
        try {
            orders = orderServiceImpl.getOrderByConfirmStatus();
        } catch (OrderServiceException e) {
            String message = "Orders not found";
            request.setAttribute("informMessage", message);
            page = PageEnum.INFORMER_PAGE_JSP.getValue();
            return page;
        }

        request.setAttribute("orders", orders);
        page = PageEnum.ORDERS_PAGE.getValue();
        return page;
    }
}
