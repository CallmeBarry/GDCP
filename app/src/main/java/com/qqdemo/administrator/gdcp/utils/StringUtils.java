package com.qqdemo.administrator.gdcp.utils;

/**
 * Created by Administrator on 2016/12/28.
 */

public class StringUtils {
    public static final String REGEX_USER_NAME = "^[a-zA-Z]\\w{2,19}$";
    public static final String REGEX_PASSWORD = "^[0-9]{3,20}$";

    public static boolean isValidUserName(String name) {
        return name.matches(REGEX_USER_NAME);
    }

    public static boolean isValidPassword(String password){
        return password.matches(REGEX_PASSWORD) ;
    }
}
