package com.github.muzhaleks.tags;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;
import java.time.LocalDate;

public class CopyrightTag extends TagSupport {
    private static final long serialVersionUID = 1L;

    @Override
    public int doStartTag() throws JspException {
        JspWriter out = pageContext.getOut();
        LocalDate date = LocalDate.now();
        String name = "© Aleks Muzhykov";
        try {
            out.print(name + " " + date.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return SKIP_BODY;
    }
    
}
