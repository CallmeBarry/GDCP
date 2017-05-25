package com.qqdemo.administrator.gdcp.presenter.impl;

import android.util.Log;

import com.qqdemo.administrator.gdcp.presenter.JSJFragmentPresenter;
import com.qqdemo.administrator.gdcp.utils.ThreadUtils;
import com.qqdemo.administrator.gdcp.view.JSJFragmentView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/5/16.
 */
public class JSJFragmentPresenterImpl implements JSJFragmentPresenter {

    private List<Map<String, Object>> dateslist;
    private HashMap<String, Object> mPoint;
    JSJFragmentView mJSJFragmentView;

    public JSJFragmentPresenterImpl( JSJFragmentView View) {
        mJSJFragmentView=View;
        dateslist=new ArrayList<>();
    }

    @Override
    public void onloadDate() {
        ThreadUtils.runOnBackgroundThread(new Runnable() {
            @Override
            public void run() {
                try {
                    Document doc = Jsoup.parse(new URL("http://jsxy.gdcp.cn/"), 60000);
                    Elements trs = doc.select("td[height=168] tr");
//                    Log.i("11111111", "run: "+trs);
                    for (Element tr : trs) {
                        mPoint = new HashMap<>();
                        mPoint.put("href",tr.select("a").attr("href"));
                        mPoint.put("title",tr.select("a").text());
                        mPoint.put("time", tr.select("td").get(2).text());

                        dateslist.add(mPoint);
                    }
                    ThreadUtils.runOnMainThread(new Runnable() {
                        @Override
                        public void run() {
                            mJSJFragmentView.onLoadDateSuccess(dateslist);
                            Log.i("111111111", "run1111111111: "+dateslist.size());
                        }
                    });

//                    String href = as.attr("href");
//                    Document doc1 = Jsoup.parse(new URL("http://a1.gdcp.cn" + href), 60000);
//                    String href1 = doc1.select("table a").get(1).attr("href");
//                    Document parse = Jsoup.parse(new URL("http://a1.gdcp.cn" + href1), 60000);
//                    Elements trs = parse.select(".nav tr[height=26]");
//                    for (Element tr : trs) {
//                        Elements td= tr.getElementsByTag("td");
//
//                        mPoint = new HashMap<>();
//                        mPoint.put("href", td.get(1).select("a").attr("href"));
//                        mPoint.put("title", td.get(1).text());
//                        mPoint.put("time", td.get(2).text());
//                        Log.i(TAG, "run1111111111: "+mPoint);
//                        dateslist.add(mPoint);
//                    }
//                    ThreadUtils.runOnMainThread(new Runnable() {
//                        @Override
//                        public void run() {
//                            mIndexFragmentView.onLoadDateSuccess(dateslist);
//                        }
//                    });


                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
