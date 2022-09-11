package com.github.muzhaleks.command.receiver.user;

import com.github.muzhaleks.command.Command;
import com.github.muzhaleks.command.PageEnum;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LogOut implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String page;

        HttpSession session = request.getSession();
        session.invalidate();

        page = PageEnum.INDEX_JSP.getValue();
        return page;
    }
}
