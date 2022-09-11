package com.github.muzhaleks.controller;

import com.github.muzhaleks.command.Command;
import com.github.muzhaleks.command.PageEnum;
import com.github.muzhaleks.command.RoleEnum;
import com.github.muzhaleks.connectionpool.ConnectionPool;
import com.github.muzhaleks.factory.CommandFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/controller")
public class MainController extends HttpServlet {
    private static final CommandFactory COMMAND_FACTORY = new CommandFactory();

    @Override
    public void init() {
        ConnectionPool.INSTANCE.initPool();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        //Check role
        if(session.getAttribute("role").equals(RoleEnum.GUEST.getValue()) || session.getAttribute("role") == null){
            Command command = COMMAND_FACTORY.defineCommand(request);
            String path = command.execute(request, response);
            if (path.equals(PageEnum.SIGN_PAGE_JSP.getValue()) || path.equals(PageEnum.REGISTRATION_PAGE.getValue()) ||
                    path.equals(PageEnum.INFORMER_PAGE_JSP.getValue()) || path.equals(PageEnum.ERROR_PAGE_JSP.getValue())) {
                request.getRequestDispatcher(path).forward(request, response);
            }else {
                request.getRequestDispatcher(PageEnum.INDEX_JSP.getValue()).forward(request, response);
            }
        }

        if(session.getAttribute("role").equals(RoleEnum.USER.getValue()) || session.getAttribute("role").equals(RoleEnum.ADMINISTRATOR.getValue())){
            Command command = COMMAND_FACTORY.defineCommand(request);
            String path = command.execute(request, response);
            if (path != null) {
                request.getRequestDispatcher(path).forward(request, response);
            } else {
                request.getRequestDispatcher(PageEnum.INDEX_JSP.getValue()).forward(request, response);
            }
        }
    }

    @Override
    public void destroy() {
        ConnectionPool.INSTANCE.closePool();
    }
}
