package com.github.muzhaleks.command.receiver.pages;

import com.github.muzhaleks.command.Command;
import com.github.muzhaleks.command.PageEnum;
import com.github.muzhaleks.command.RoleEnum;
import com.github.muzhaleks.exceptions.UserServiceException;
import com.github.muzhaleks.factory.ServiceFactory;
import com.github.muzhaleks.model.User;
import com.github.muzhaleks.services.UserService;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class UserInfoPageByAdmin implements Command {
    private static final Logger LOGGER = LogManager.getLogger(UserInfoPageByAdmin.class);
    private UserService userService = ServiceFactory.INSTANCE.getUserService();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

        HttpSession session = request.getSession();
        //Check role
        if(!session.getAttribute("role").equals(RoleEnum.ADMINISTRATOR.getValue())){
            return PageEnum.INDEX_JSP.getValue();
        }


        String page;
        long userID = Long.parseLong(request.getParameter("userID"));
        try {
            User user;
            user = userService.getUserById(userID);
            request.setAttribute("user", user);

        } catch (UserServiceException userServiceException) {
            String message = "User not found ";
            request.setAttribute("informMessage", message);
            page = PageEnum.INFORMER_PAGE_JSP.getValue();
            return page;
        }

        return PageEnum.USER_INFO_PAGE_BY_ADMIN_JSP.getValue();
    }
}
