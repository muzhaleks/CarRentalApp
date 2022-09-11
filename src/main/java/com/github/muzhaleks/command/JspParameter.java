package com.github.muzhaleks.command;

public enum  JspParameter {
    COMMAND("command"),
    HREF("href"),
    LANGUAGE("language");

    private String value;

    private JspParameter(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
