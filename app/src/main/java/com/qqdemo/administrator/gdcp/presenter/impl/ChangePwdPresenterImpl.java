package com.qqdemo.administrator.gdcp.presenter.impl;

import android.util.Log;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.qqdemo.administrator.gdcp.model.User;
import com.qqdemo.administrator.gdcp.presenter.ChangePwdPresenter;
import com.qqdemo.administrator.gdcp.view.ChangePwdView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by Administrator on 2017/4/10.
 */

public class ChangePwdPresenterImpl implements ChangePwdPresenter {

   String TAG="111111111111111111";

   private  ChangePwdView mChangePwdView;
    public ChangePwdPresenterImpl(ChangePwdView changePwdView) {
        mChangePwdView=changePwdView;
    }

    @Override
    public void changePwd(String oldpwd, String newold,String newold2) {
        String url=User.nav.get("密码修改");
        OkGo.post(url)
                .headers("Referer",User.url)
                .params("__VIEWSTATE", "dDwtMzg5NzE5MDc3Ozs+DeBjIiwl/b6O36DkY8PJIMwGPEg=")
                .params("TextBox2", oldpwd)
                .params("TextBox3", newold)
                .params("Textbox4",newold2)
                .params("Button1", "修  改")
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {

                        Log.i(TAG, "onSuccess: "+s);
                        Document doc = Jsoup.parse(s);
                        String str=doc.select("script").get(0).html().toString();
                        String result=str.substring(str.indexOf('\'')+1, str.lastIndexOf('\''));
                        if(result.equals("修改成功！")){
                            mChangePwdView.onChangeSuccess();
                        }else{
                            mChangePwdView.onChangeFailed(result);
                        };


                    }
                });
    }
}
