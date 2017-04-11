package com.qqdemo.administrator.gdcp.presenter.impl;


import android.util.Log;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.qqdemo.administrator.gdcp.model.User;
import com.qqdemo.administrator.gdcp.presenter.PerformancePresenter;
import com.qqdemo.administrator.gdcp.utils.ThreadUtils;
import com.qqdemo.administrator.gdcp.view.PerformanceView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by Administrator on 2017/4/11.
 */

public class PerformancePresenterImpl implements PerformancePresenter {
    String TAG = "PerformancePresenterImpl";

    private PerformanceView mPerformanceView;

    private List<String> mListYear;
    private String mUrl;
    private String mVIEWSTATE;
    private List<HashMap<String, Object>> mScoreList;

    public PerformancePresenterImpl(PerformanceView view) {
        mPerformanceView = view;
        mScoreList = new ArrayList<HashMap<String, Object>>();
    }


    @Override
    public synchronized void init() {

        mUrl = User.nav.get("学习成绩查询");
        Log.i(TAG, "init: " + mUrl);
        OkGo.get(mUrl)
                .headers("Referer", User.url)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                    }

                    @Override
                    public void onAfter(String s, Exception e) {
                        super.onAfter(s, e);
                        Document doc = Jsoup.parse(s);
                        mVIEWSTATE = doc.select("input[name=__VIEWSTATE]").attr("value");
                        Elements select = doc.getElementById("ddlXN").select("option");
                        if (mListYear == null) {
                            mListYear = new ArrayList<String>();
                        }
                        for (Element element : select) {
                            mListYear.add(element.text());
                        }
//                        for (int i = 0; i < 9; i++) {
//                            mListYear.remove(i);
//                        }
                        mListYear.remove(0);
                        int thisyear = Calendar.getInstance().get(Calendar.YEAR);
                        mListYear.add(0, thisyear - 1 + "-" + thisyear);
                        mPerformanceView.setAdapter();
                        mPerformanceView.onInitSuccess();
                    }

                });
    }

    @Override
    public List<String> getListYear() {
        return mListYear;
    }

    @Override
    public synchronized void requestBy(String year, int term) {
        mScoreList.clear();
        Log.i(TAG, "requestBy: " + mVIEWSTATE);
        Log.i(TAG, "requestBy: " + year);
        Log.i(TAG, "requestBy: " + term);
        Log.i(TAG, "requestBy: " + User.url);
        OkGo.post(mUrl)
                .headers("Referer", User.url)
                .params("__VIEWSTATE", mVIEWSTATE)
                .params("ddlXN", year)
                .params("ddlXQ", term)
                .params("Button1", "按学期查询")
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        // Log.i(TAG, "onSuccess: "+s);

                    }

                    @Override
                    public void onAfter(String s, Exception e) {
                        super.onAfter(s, e);
                        Log.i(TAG, "onSuccess: " + s);
                        Document doc = Jsoup.parse(s);
                        Elements tables = doc.select("table");
                        Element table1 = tables.get(0);
                        Elements trs = table1.select("tr");
                        for (Element tr : trs) {
                            Elements tds = tr.select("td");
                            HashMap<String ,Object> iterm=new HashMap<String, Object>();
                            iterm.put("iname",tds.get(3).text());
                            iterm.put("iscore",tds.get(8).text());
                            mScoreList.add(iterm);
                        }
                        Log.i(TAG, "onAfter: "+mScoreList);
                        ThreadUtils.runOnMainThread(new Runnable() {
                            @Override
                            public void run() {
                                mPerformanceView.onLoadScore();
                            }
                        });


                    }
                });

    }

    @Override
    public List<HashMap<String, Object>> getScoreList() {
        return mScoreList;
    }
}