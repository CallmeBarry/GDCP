package com.qqdemo.administrator.gdcp.presenter.impl;

import android.util.Log;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.qqdemo.administrator.gdcp.model.User;
import com.qqdemo.administrator.gdcp.presenter.SchedulePresenter;
import com.qqdemo.administrator.gdcp.view.ScheduleView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by Administrator on 2017/4/12.
 */

public class SchedulePresenterImpl implements SchedulePresenter {

    private static final String TAG = "SchedulePresenterImpl";
    private List<List<String>> mLists1;
    private List<List<String>> mLists2;
    private List<List<String>> mLists3;
    ScheduleView mScheduleView;

    public SchedulePresenterImpl(ScheduleView ScheduleView) {
        mScheduleView = ScheduleView;
        intiNet();
    }

    private void intiNet() {
        mLists1 = new ArrayList<>();
        mLists2 = new ArrayList<>();
        mLists3 = new ArrayList<>();
        String mUrl = User.nav.get("个人课表查询");
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

                        initgetSchedule1(doc);
                        initgetSchedule2(doc);
                        initgetSchedule3(doc);
                        mScheduleView.initViewPager();
                    }
                });

    }

    private void initgetSchedule3(Document doc) {
        Element table3 = doc.getElementById("Datagrid2");
        Elements trs = table3.select("tr");
        for (Element tr : trs) {
            List<String> row = new ArrayList<String>();
            Elements tds = tr.select("td");
            for (Element td : tds) {
                row.add(td.text());
            }
            mLists3.add(row);
        }
        Log.i(TAG, "onAfter: " + mLists3);
    }

    private void initgetSchedule2(Document doc) {
        Element table2 = doc.getElementById("DataGrid1");
        Elements trs = table2.select("tr");
        for (Element tr : trs) {
            List<String> row = new ArrayList<String>();
            Elements tds = tr.select("td");
            for (Element td : tds) {
                row.add(td.text());
            }
            mLists2.add(row);
        }
        Log.i(TAG, "onAfter: " + mLists2);
    }

    private void initgetSchedule1(Document doc) {
        boolean flag = true;
        Element table1 = doc.getElementById("Table1");
        Elements trs = table1.select("tr");
        for (Element tr : trs) {
            List<String> row = new ArrayList<String>();
            Elements tds = tr.select("td");
            if (flag) {
                for (Element td : tds) {
                    row.add(td.text());
                }
                if (row.size() > 8) {
                    row.remove(0);
                }
                mLists1.add(row);
                flag = false;


            } else {
                flag = true;
            }
        }
        Log.i(TAG, "onAfter: " + mLists1);
    }

    public List<List<String>> getSchedule1() {
        return mLists1;
    }

    public List<List<String>> getSchedule2() {
        return mLists2;
    }

    public List<List<String>> getSchedule3() {
        return mLists3;
    }
}
