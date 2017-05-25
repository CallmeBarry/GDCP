package com.qqdemo.administrator.gdcp.view;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/4/26.
 */

public interface indexFragmentView {
    void onLoadDateFailed();

    void onLoadDateSuccess(List<Map<String,Object>> dateslist);
}
