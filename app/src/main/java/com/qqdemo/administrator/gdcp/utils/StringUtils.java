package com.qqdemo.administrator.gdcp.utils;

/**
 * Created by Administrator on 2016/12/28.
 */

public class StringUtils {

    public static boolean isValidUserName(String name) {
        return name.length()>0;
    }

    public static boolean isValidPassword(String password){
        return password.length()>=6;
    }
    public static boolean isValidCode(String code) {
        return code.length()==4;
    }
}
