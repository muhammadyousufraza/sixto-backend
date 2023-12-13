package com.example.practice.project.utilities;

public class Constants {

    private Constants() {
    }

    public static final String VERSION = "1.0.1";

    public static final String API = "/api";
    public static final String AUTH = "/auth";
    public static final String USER = "/user";
    public static final String LOGIN = "/login";
    public static final String CHANGE_PASSWORD = "/change-password";
    public static final String USER_NOT_FOUND = "User not found";
    public static final String REFRESH_TOKEN = "/refresh-token";

    public static final String EMAIL_FORMAT_REGEXP = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$";
    public static final String EMAIL_FORMAT_INCORRECT = "Email Format Incorrect";
    public static final String INVALID_PASSWORD = "invalid password";
    public static final String NEW_AND_OLD_PASSWORD_SAME_ERROR = "new and old password should not be same";
    
}
