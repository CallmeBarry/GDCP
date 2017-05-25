package com.qqdemo.administrator.gdcp.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/4/1.
 */

public class MyEvent {

    public MyEvent(List<String> list) {
        mList = list;
    }
    public MyEvent(){
        mMaps=new ArrayList<>();
    }

    private List<String> mList;
    private List<Map<String, Object>> mMaps;

    public List<Map<String, Object>> getMaps() {
        return mMaps;
    }

    public void setMaps(List<Map<String, Object>> maps) {
        mMaps = maps;
    }



    public List<String> getList() {
        return mList;
    }

    public void setList(List<String> list) {
        mList = list;
    }
}
