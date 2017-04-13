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

    public static String url;//
    public static String username;//用户名
    public static String college;//学院
    public static String major;//专业


    public static boolean BindLB;//是否绑定图书账号
    public static int LBid;//图书馆账号
    public static String LBpwd;//图书管账号密码

    public static HashMap<String ,String> nav;//每个功能的url地址
}
