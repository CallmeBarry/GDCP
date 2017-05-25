package com.qqdemo.administrator.gdcp.presenter;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/5/17.
 */
public interface SettingPresent {

    List<Map<String,Object>> loadTittle();

    void saveTittle(Map<String,Object> map );

    boolean checkpwd(String pwd);
}
