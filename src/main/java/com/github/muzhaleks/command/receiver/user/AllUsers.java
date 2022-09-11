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
import java.util.ArrayList;

public class AllUsers implements Command {
    private static final Logger LOGGER = LogManager.getLogger(AllUsers.class);
    private UserService userServiceImpl = ServiceFactory.INSTANCE.getUserService();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String page;
        ArrayList<User> users = null;
        try {
            users = (ArrayList<User>) userServiceImpl.geaAllUsers();
        } catch (UserServiceException userServiceException) {
            String message = "Users not found";
            request.setAttribute("informMessage", message);
            page = PageEnum.INFORMER_PAGE_JSP.getValue();
            return page;
        }



        request.setAttribute("users", users);
        page = PageEnum.ALL_USER_JSP.getValue();
        return page;
    }
}
