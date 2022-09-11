package com.github.muzhaleks.command;

public enum RoleEnum {
    ADMINISTRATOR("admin"),
    USER("user"),
    GUEST("guest");


    private String value;

    private RoleEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
