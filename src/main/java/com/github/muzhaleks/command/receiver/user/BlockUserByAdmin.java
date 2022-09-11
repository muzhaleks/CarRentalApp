package com.github.muzhaleks.command.receiver.user;

import com.github.muzhaleks.command.Command;
import com.github.muzhaleks.command.PageEnum;
import com.github.muzhaleks.command.RoleEnum;
import com.github.muzhaleks.exceptions.UserServiceException;
import com.github.muzhaleks.factory.ServiceFactory;
import com.github.muzhaleks.services.UserService;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class BlockUserByAdmin implements Command {
    private static final Logger LOGGER = LogManager.getLogger(BlockUserByAdmin.class);
    private UserService userService = ServiceFactory.INSTANCE.getUserService();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String page;

        HttpSession session = request.getSession();
        //Check role
        if(!session.getAttribute("role").equals(RoleEnum.ADMINISTRATOR.getValue())){
            return PageEnum.INDEX_JSP.getValue();
        }

        long userID = Long.parseLong(request.getParameter("userID"));
        try {
            userService.blockUser(userID);
        } catch (UserServiceException userServiceException) {
            userServiceException.printStackTrace();
            String message = "Не удалось заблокировать пользователя ";
            request.setAttribute("informMessage", message);
            page = PageEnum.INFORMER_PAGE_JSP.getValue();
            return page;
        }

        String message = "Пользователь успешно заблокирован";
        request.setAttribute("informMessage", message);
        page = PageEnum.INFORMER_PAGE_JSP.getValue();
        return page;
    }
}
