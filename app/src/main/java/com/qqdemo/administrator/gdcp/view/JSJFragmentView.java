package com.qqdemo.administrator.gdcp.view;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/5/16.
 */
public interface JSJFragmentView {
    void onLoadDateFailed();

    void onLoadDateSuccess(List<Map<String, Object>> dateslist);
}
