package com.github.muzhaleks.validator;

public class LoginPasswordValidator {
    private static final String LOGIN_PASS_PATTERN = "^[a-zA-Z][a-zA-Z0-9-_\\.]{1,20}$";


    public static boolean validationLogin(String login) {
        if (login != null && login.matches(LOGIN_PASS_PATTERN)) {
            return true;
        }
        return false;
    }


    public static boolean validationPassword(String password) {
        if (password != null && password.matches(LOGIN_PASS_PATTERN)) {
            return true;
        }
        return false;
    }
}
