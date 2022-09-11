package com.github.muzhaleks.command.receiver.user;

import com.github.muzhaleks.command.Command;
import com.github.muzhaleks.command.PageEnum;
import com.github.muzhaleks.command.RoleEnum;
import com.github.muzhaleks.exceptions.OrderServiceException;
import com.github.muzhaleks.factory.ServiceFactory;
import com.github.muzhaleks.services.OrderService;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class AdminPage implements Command {
    private static final Logger LOGGER = LogManager.getLogger(AdminPage.class);
    private OrderService orderServiceImpl = ServiceFactory.INSTANCE.getOrderService();


    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

        HttpSession session = request.getSession();
        //Check role
        if(!session.getAttribute("role").equals(RoleEnum.ADMINISTRATOR.getValue())){
            return PageEnum.INDEX_JSP.getValue();
        }

        int countOrdersWhereAdminStatusFalse = 0;

        try {
            countOrdersWhereAdminStatusFalse = orderServiceImpl.getCountOrdersWhereAdminStatusFalse();
        } catch (OrderServiceException e) {
            e.printStackTrace();
        }
        request.setAttribute("countOrder", countOrdersWhereAdminStatusFalse);

        return PageEnum.ADMIN_PAGE_JSP.getValue();
    }
}
