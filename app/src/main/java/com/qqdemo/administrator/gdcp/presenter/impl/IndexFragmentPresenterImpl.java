package com.qqdemo.administrator.gdcp.presenter.impl;

import android.util.Log;

import com.qqdemo.administrator.gdcp.presenter.IndexFragmentPresenter;
import com.qqdemo.administrator.gdcp.utils.ThreadUtils;
import com.qqdemo.administrator.gdcp.view.indexFragmentView;

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
 * Created by Administrator on 2017/4/26.
 */

public class IndexFragmentPresenterImpl implements IndexFragmentPresenter {

    private String TAG = "IndexFragment";

    private List<Map<String, Object>> dateslist;
    private Map<String, Object> mPoint;


    private indexFragmentView mIndexFragmentView;

    public IndexFragmentPresenterImpl(indexFragmentView view) {
        dateslist = new ArrayList<>();
        mIndexFragmentView = view;
    }

    @Override
    public void onloadDate() {
        ThreadUtils.runOnBackgroundThread(new Runnable() {
            @Override
            public void run() {
                try {
                    Document doc = Jsoup.parse(new URL("http://a1.gdcp.cn"), 60000);
                    Elements as = doc.select(".ztmore >a");
                    String href = as.attr("href");
                    Document doc1 = Jsoup.parse(new URL("http://a1.gdcp.cn" + href), 60000);
                    String href1 = doc1.select("table a").get(1).attr("href");
                    Document parse = Jsoup.parse(new URL("http://a1.gdcp.cn" + href1), 60000);
                    Elements trs = parse.select(".nav tr[height=26]");
                    Log.i(TAG, "run: "+trs);
                    for (Element tr : trs) {
                        Elements td= tr.getElementsByTag("td");

                        mPoint = new HashMap<>();
                        mPoint.put("href", td.get(1).select("a").attr("href"));
                        mPoint.put("title", td.get(1).text());
                        mPoint.put("time", td.get(2).text());
                        Log.i(TAG, "run1111111111: "+mPoint);
                        dateslist.add(mPoint);
                    }
                    ThreadUtils.runOnMainThread(new Runnable() {
                        @Override
                        public void run() {
                            mIndexFragmentView.onLoadDateSuccess(dateslist);
                        }
                    });


                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
//        Log.i(TAG, "onloadDate: "+"加载数据");
//        OkGo.get("http://a1.gdcp.cn")
//                .execute(new StringCallback() {
//                    @Override
//                    public void onSuccess(String s, Call call, Response response) {
//
//                    }
//
//                    @Override
//                    public void onAfter(String s, Exception e) {
//                        super.onAfter(s, e);
//
//                        Document parse = Jsoup.parse(s);
//                        Elements a = parse.select(".ztmore >a");
//                        String href = a.attr("href");
//                        OkGo.get("http://a1.gdcp.cn"+href).execute(new StringCallback() {
//                            @Override
//                            public void onAfter(String s, Exception e) {
//                                super.onAfter(s, e);
//                                Document parse1 = Jsoup.parse(s);
//                                Element table = parse1.select("table").get(2);
//                                String attr = table.select("a").get(0).attr("href");
//
//                                OkGo.get("http://a1.gdcp.cn"+attr).execute(new StringCallback() {
//
//
//
//                                    @Override
//                                    public void onSuccess(String s, Call call, Response response) {
//
//                                    }
//
//                                    @Override
//                                    public void onAfter(String s, Exception e) {
//                                        super.onAfter(s, e);
//                                        Document parse2 = Jsoup.parse(s);
//                                        Elements select = parse2.select("tr[height=26] a");
//                                        Log.i(TAG, "onAfter: "+select);
//                                        for( Element a:select ){
//                                            mPoint = new HashMap<>();
//                                            mPoint.put("href",a.attr("href"));
//                                            mPoint.put("title",a.text());
//                                            dateslist.add(mPoint);
//                                        }
//                                        mIndexFragmentView.onLoadDateSuccess(dateslist);
//
//                                    }
//                                });
//                            }
//
//                            @Override
//                            public void onSuccess(String s, Call call, Response response) {
//
//                            }
//                        });
//                    }
//
//                });
    }
}
