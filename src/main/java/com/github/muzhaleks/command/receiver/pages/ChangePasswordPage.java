package com.github.muzhaleks.command.receiver.pages;

import com.github.muzhaleks.command.Command;
import com.github.muzhaleks.command.PageEnum;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ChangePasswordPage implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        return PageEnum.USER_CHANGE_PASSWORD_PAGE.getValue();
    }
}
