package com.qqdemo.administrator.gdcp.presenter.impl;

import android.util.Log;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.qqdemo.administrator.gdcp.model.MyEvent;
import com.qqdemo.administrator.gdcp.presenter.ComputerPresent;
import com.qqdemo.administrator.gdcp.utils.ThreadUtils;
import com.qqdemo.administrator.gdcp.view.ComputerView;

import org.greenrobot.eventbus.EventBus;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import okhttp3.Call;
import okhttp3.Response;

import static com.bumptech.glide.gifdecoder.GifHeaderParser.TAG;

/**
 * Created by Administrator on 2017/4/16.
 */

public class ComputerPresentImpl implements ComputerPresent {
    String indexurl = "http://www2.gdcp.cn/lin/sjsx/main2011.html";
    String urlhearder = "http://www2.gdcp.cn/lin/sjsx/";

    private ComputerView mComputerView;
    private List<String> mList;
    List<HashMap<String, String>> mItemList1;
    private String mHeaderurl;
    private List<String> mList1;

    public ComputerPresentImpl(ComputerView view) {
        mComputerView = view;
        mList = new ArrayList<>();
        mItemList1 = new ArrayList<HashMap<String, String>>();
    }

    @Override
    public void getindex() {

        OkGo.get(indexurl).execute(new StringCallback() {

            @Override
            public void onSuccess(String s, Call call, Response response) {
            }

            @Override
            public void onAfter(String s, Exception e) {
                super.onAfter(s, e);
                Document doc = Jsoup.parse(s);
                Elements a = doc.getElementsByTag("a");
                mList.add(a.get(0).attr("href"));
                mList.add(a.get(2).attr("href"));

            }
        });
    }

    @Override
    public void getWeekData1() {
        ThreadUtils.runOnBackgroundThread(new Runnable() {

            @Override
            public void run() {
                try {
                    mItemList1.clear();
                    String url = urlhearder + mList.get(0);
                    mHeaderurl = url.substring(0, url.lastIndexOf("/") + 1);
                    Log.i(TAG, "run: " + url);
                    Document doc = Jsoup.parse(new URL(url), 60000);
                    Elements as = doc.select("td >p a");
                    for (Element a : as) {
                        String name = a.text();
                        HashMap<String, String> map = new HashMap<String, String>();
                        map.put("name", name);
                        map.put("href", a.attr("href"));
                        mItemList1.add(map);
                    }
                    ;

                    mItemList1.remove(mItemList1.size() - 1);
                    mItemList1.remove(0);
                    mItemList1.remove(0);
                    HashMap<String, String> map = new HashMap<String, String>();
                    map.put("name", "第1周");
                    map.put("href", "01.html");
                    mItemList1.add(0, map);
                    mComputerView.openResideMenuofRigth(mItemList1);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        });


    }

    @Override
    public void getWeekData2() {

    }

    @Override
    public void onloadData1(final String url) {
        mList1 = new ArrayList();
        Log.i(TAG, "onloadData1: " + mHeaderurl + url);
        ThreadUtils.runOnBackgroundThread(new Runnable() {
            @Override
            public void run() {
                try {
                    Document parse = Jsoup.parse(new URL(mHeaderurl + url), 60000);
                    Elements tables = parse.select("table");
                    for (Element table : tables) {
                        Log.i(TAG, "run: "+table.text());
                        mList1.add(table.text().toString());
                    }
                    EventBus.getDefault().post(new MyEvent(mList1));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });


}
        }
