package com.github.muzhaleks.command.receiver.language;

import com.github.muzhaleks.command.Command;
import com.github.muzhaleks.command.JspParameter;
import com.github.muzhaleks.command.receiver.pages.IndexPage;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ChangeLanguage implements Command {
    private static final Logger LOGGER = LogManager.getLogger(ChangeLanguage.class);



    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

        HttpSession session = request.getSession();


        String language = request.getParameter(JspParameter.LANGUAGE.getValue());
        LOGGER.warn(language);
        Language localeType = Language.valueOf(language);
        String languageType = localeType.getLanguage();
        session.setAttribute("language", languageType);
        return new IndexPage().execute(request, response);
    }
}
