package com.github.muzhaleks.command.receiver.user;

import com.github.muzhaleks.command.Command;
import com.github.muzhaleks.command.PageEnum;
import com.github.muzhaleks.exceptions.UserServiceException;
import com.github.muzhaleks.factory.ServiceFactory;
import com.github.muzhaleks.model.User;
import com.github.muzhaleks.services.OrderService;
import com.github.muzhaleks.services.UserService;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class SignIn implements Command {
    private static final Logger LOGGER = LogManager.getLogger(SignIn.class);
    private UserService userService = ServiceFactory.INSTANCE.getUserService();
    private OrderService orderServiceImpl = ServiceFactory.INSTANCE.getOrderService();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String page = "";

        String login = request.getParameter("login");
        String password = request.getParameter("password");
        User user = null;
        try {
            user = userService.signIn(login, password);
        } catch (UserServiceException userServiceException) {
            String message = "Incorrect login or Password";
            request.setAttribute("informMessage", message);
            page = PageEnum.INFORMER_PAGE_JSP.getValue();
            return page;
        }

        if (user != null) {
            HttpSession session = request.getSession();
            request.setAttribute("user", user);
            Long id = user.getId();
            session.setAttribute("ActiveUserId", id);
            session.setAttribute("activeUser", user);
            session.setAttribute("role", user.getRole().getRole());
            request.setAttribute("user", user);
        } else {
            String message = "User not found";
            request.setAttribute("informMessage", message);
            page = PageEnum.INFORMER_PAGE_JSP.getValue();
        }
        return page;
    }
}
