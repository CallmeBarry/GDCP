package com.qqdemo.administrator.gdcp.presenter;

import java.util.HashMap;
import java.util.List;

/**
 * Created by Administrator on 2017/4/11.
 */

public interface PerformancePresenter {

    void init();

    List<String> getListYear();

    void requestBy(String year, int term);

    List<HashMap<String, Object>> getScoreList();

    String getPeople();

    String getGPA();

    String getXF();


}
