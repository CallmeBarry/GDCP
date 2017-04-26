package com.qqdemo.administrator.gdcp.model;

import java.util.List;

/**
 * Created by Administrator on 2017/4/1.
 */

public class MyEvent {

    private List<String> mList;

    public MyEvent(List<String> list) {
        mList = list;
    }

    public List<String> getList() {
        return mList;
    }

    public void setList(List<String> list) {
        mList = list;
    }
}
