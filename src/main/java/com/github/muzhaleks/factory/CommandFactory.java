package com.github.muzhaleks.factory;

import com.github.muzhaleks.command.Command;
import com.github.muzhaleks.command.CommandParameter;
import com.github.muzhaleks.command.JspParameter;

import javax.servlet.http.HttpServletRequest;

public class CommandFactory {
    public Command defineCommand(HttpServletRequest request) {
        return CommandParameter.valueOf(request.getParameter(JspParameter.COMMAND.getValue())).getCommand();
    }
}
