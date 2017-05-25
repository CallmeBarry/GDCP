package com.qqdemo.administrator.gdcp.gdcp;

import android.app.Activity;
import android.app.Application;
import android.content.SharedPreferences;
import android.util.Log;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.cache.CacheEntity;
import com.lzy.okgo.cache.CacheMode;
import com.lzy.okgo.cookie.store.MemoryCookieStore;
import com.lzy.okgo.cookie.store.PersistentCookieStore;
import com.qqdemo.administrator.gdcp.model.User;
import com.tencent.bugly.crashreport.CrashReport;

/**
 * Created by Administrator on 2017/4/9.
 */

public class gdcpApplication extends Application {
    String TAG = "gdcpApplication";

    @Override
    public void onCreate() {
        super.onCreate();

        //腾讯的bugly初始化
        CrashReport.initCrashReport(getApplicationContext(), "70db45d6c7", true);
        User.init();
        OkGo.init(this);

        //以下设置的所有参数是全局参数,同样的参数可以在请求的时候再设置一遍,那么对于该请求来讲,请求中的参数会覆盖全局参数
        //好处是全局参数统一,特定请求可以特别定制参数

        //以下都不是必须的，根据需要自行选择,一般来说只需要 debug,缓存相关,cookie相关的 就可以了
        OkGo.getInstance()
                //如果使用默认的 60秒,以下三行也不需要传
                .setConnectTimeout(OkGo.DEFAULT_MILLISECONDS)  //全局的连接超时时间
                .setReadTimeOut(OkGo.DEFAULT_MILLISECONDS)     //全局的读取超时时间
                .setWriteTimeOut(OkGo.DEFAULT_MILLISECONDS)    //全局的写入超时时间

                //可以全局统一设置缓存模式,默认是不使用缓存,可以不传,具体其他模式看 github 介绍 https://github.com/jeasonlzy/
                .setCacheMode(CacheMode.IF_NONE_CACHE_REQUEST)

                //可以全局统一设置缓存时间,默认永不过期,具体使用方法看 github 介绍
                .setCacheTime(CacheEntity.CACHE_NEVER_EXPIRE)

                //可以全局统一设置超时重连次数,默认为三次,那么最差的情况会请求4次(一次原始请求,三次重连请求),不需要可以设置为0
                .setRetryCount(3)


                //如果不想让框架管理cookie（或者叫session的保持）,以下不需要
                .setCookieStore(new MemoryCookieStore())            //cookie使用内存缓存（app退出后，cookie消失）
                .setCookieStore(new PersistentCookieStore());        //cookie持久化存储，如果cookie不过期，则一直有效

        SharedPreferences first = getSharedPreferences("First", Activity.MODE_PRIVATE);
        boolean flag = first.getBoolean("first", true);
        Log.i(TAG, "onCreate: " + flag);
        if (flag) {
            SharedPreferences.Editor tittle = getSharedPreferences("Tittle", Activity.MODE_PRIVATE).edit();
            tittle.putString("Tittles", "{\"tittles\":[{\"tittle\":\"计算机工程学院\",\"key\":true}," +
                    "{\"tittle\":\"土木工程学院\",\"key\":true},{\"tittle\":\"汽车与工程机械学院\",\"key\":true}," +
                    "{\"tittle\":\"运输管理学院\",\"key\":true},{\"tittle\":\"轨道交通学院\",\"key\":true}," +
                    "{\"tittle\":\"海事学院\",\"key\":true},{\"tittle\":\"商贸学院\",\"key\":true}," +
                    "{\"tittle\":\"电子与通信工程学院\",\"key\":true},{\"tittle\":\"机电工程学院\",\"key\":true}]}");
            tittle.commit();
            SharedPreferences.Editor edit = first.edit();
            edit.putBoolean("first", false);
            edit.commit();
        }

    }
}