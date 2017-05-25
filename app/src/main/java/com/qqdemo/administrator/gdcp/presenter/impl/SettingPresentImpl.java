package com.qqdemo.administrator.gdcp.presenter.impl;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.qqdemo.administrator.gdcp.model.Tittle;
import com.qqdemo.administrator.gdcp.presenter.SettingPresent;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by Administrator on 2017/5/17.
 */

public class SettingPresentImpl implements SettingPresent {
    String TAG="SettingPresentImpl";

    private SharedPreferences mSharedPreferences;

    List<Map<String, Object>> mTittles;
    Context mContext;
    Gson mGson;
    private String mUrl="http://192.168.15.103/homeLogin.action";

    public SettingPresentImpl(Context context) {
         this.mContext = context;
        mGson=new Gson();
        mTittles=new ArrayList<>();
    }

    @Override
    public List<Map<String, Object>> loadTittle() {
        mSharedPreferences = mContext.getSharedPreferences("Tittle", Activity.MODE_PRIVATE);
        String tittles = mSharedPreferences.getString("Tittles", null);
        Log.i(TAG, "loadTittle: "+tittles);
        if (tittles != null) {
            Tittle tittle = mGson.fromJson(tittles, Tittle.class);
            for(Tittle.TittlesBean  t:tittle.getTittles()){
                Map<String, Object> map=new HashMap<>();
                map.put("tittle",t.getTittle());
                map.put("key",t.isKey());
                mTittles.add(map);
            }

        }
        return mTittles;
    }

    @Override
    public void saveTittle(Map<String, Object> list) {
        SharedPreferences.Editor editor = mContext.getSharedPreferences("Tittle", Activity.MODE_PRIVATE).edit();
        String str = mGson.toJson(list);
        editor.putString("Tittles",str);
        editor.commit();
    }

    @Override
    public boolean checkpwd(String pwd) {
//        name=1513157239&userType=1&passwd=123456&loginType=2&rand=8784&imageField.x=26&imageField.y=7
//        name=1513157218&userType=1&passwd=123456&loginType=2&rand=1466&imageField.x=20&imageField.y=14
//        http://192.168.15.103/getCheckpic.action?rand=1111.1
//        http://192.168.15.103/loginstudent.action
//        http://192.168.15.103/loginstudent.action?name=1513157218&userType=1&passwd=123456&loginType=2&rand=1111&imageField.x=20&imageField.y=14
//        http://192.168.15.103/loginstudent.action?name=1513157239&userType=1&passwd=132113&loginType=2&rand=1111&imageField.x=20&imageField.y=14
//        http://192.168.15.103/getpasswdPhoto.action
        OkGo.post(mUrl)
//                .headers("Referer",)
                .params("Button1", "修  改")
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
//
//                        Log.i(TAG, "onSuccess: "+s);
//                        Document doc = Jsoup.parse(s);
//                        String str=doc.select("script").get(0).html().toString();
//                        String result=str.substring(str.indexOf('\'')+1, str.lastIndexOf('\''));
//                        if(result.equals("修改成功！")){
//                            mChangePwdView.onChangeSuccess();
//                        }else{
//                            mChangePwdView.onChangeFailed(result);
//                        };


                    }
                });

        return false;
    }
}
