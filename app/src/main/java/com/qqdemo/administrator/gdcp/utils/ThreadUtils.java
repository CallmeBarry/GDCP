package com.qqdemo.administrator.gdcp.utils;

import android.os.Handler;
import android.os.Looper;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * Created by Administrator on 2016/12/29.
 */

public class ThreadUtils {

    public static Executor sExecutor= Executors.newSingleThreadExecutor();
    public  static Handler sHandler=new Handler(Looper.getMainLooper());

    public static void runOnBackgroundThread(Runnable run){
        sExecutor.execute(run);
    }

    public static  void  runOnMainThread(Runnable run){
        sHandler.post(run);
    }

}
