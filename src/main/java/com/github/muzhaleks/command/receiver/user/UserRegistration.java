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

public class UserRegistration implements Command {
    private UserService userService = ServiceFactory.INSTANCE.getUserService();
    private static final Logger LOGGER = LogManager.getLogger(UserRegistration.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        User user = null;
        String page="";
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String passportSerialNumber = request.getParameter("passportSerialNumber");
        String driverLicenceNumber = request.getParameter("driverLicenceNumber");
        String email = request.getParameter("email");
        String phoneNumber = request.getParameter("phoneNumber");


        try {
            userService.createUser(login, password, firstName, lastName, passportSerialNumber, driverLicenceNumber, email,
                    phoneNumber);
        } catch (UserServiceException userServiceException) {
            String message = "Cant create user";
            request.setAttribute("informMessage", message);
            page = PageEnum.INFORMER_PAGE_JSP.getValue();
            return page;
        }

        try {
            user = userService.getUserByLogin(login);
        } catch (UserServiceException userServiceException) {
            userServiceException.printStackTrace();
        }
        if (user != null) {
            HttpSession session = request.getSession();
            Long id = user.getId();
            session.setAttribute("ActiveUserId", id);
            session.setAttribute("role", user.getRole().getRole());
            session.setAttribute("activeUser", user);
            request.setAttribute("user", user);
            String message = "Вы зарегестрированны";
            request.setAttribute("informMessage", message);
            page = PageEnum.INFORMER_PAGE_JSP.getValue();

        } else {
            String message = "User not found";
            request.setAttribute("informMessage", message);
            page = PageEnum.INFORMER_PAGE_JSP.getValue();
        }
        return page;
    }
}
