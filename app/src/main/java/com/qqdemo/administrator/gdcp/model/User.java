package com.qqdemo.administrator.gdcp.model;

import java.util.HashMap;

/**
 * Created by Administrator on 2017/4/10.
 */

public class User {
    private User(){}

    public static void init(){
        nav=new HashMap<String ,String>();
    }

    public static String url;
    public static String username;
    public static String college;
    public static String major;

    public static HashMap<String ,String> nav;
}
