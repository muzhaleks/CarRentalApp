package com.github.muzhaleks.command.receiver.user;

import com.github.muzhaleks.command.Command;
import com.github.muzhaleks.command.PageEnum;
import com.github.muzhaleks.exceptions.UserServiceException;
import com.github.muzhaleks.factory.ServiceFactory;
import com.github.muzhaleks.model.User;
import com.github.muzhaleks.services.UserService;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class UserPage implements Command {
    private static final Logger LOGGER = LogManager.getLogger(UserPage.class);
    private UserService userService = ServiceFactory.INSTANCE.getUserService();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String page;
        HttpSession session = request.getSession();

        if (session.getAttribute("role") != null) {
            long ActiveUserId = (long) session.getAttribute("ActiveUserId");

            try {
                User user;
                user = userService.getUserById(ActiveUserId);
                request.setAttribute("user", user);

            } catch (UserServiceException userServiceException) {
                String message = "User not found";
                request.setAttribute("informMessage", message);
                page = PageEnum.INFORMER_PAGE_JSP.getValue();
                return page;
            }

        }
        return PageEnum.USER_PAGE_JSP.getValue();
    }
}
