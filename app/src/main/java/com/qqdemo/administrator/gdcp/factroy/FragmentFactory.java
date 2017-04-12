package com.qqdemo.administrator.gdcp.factroy;

import android.support.v4.app.Fragment;

import com.qqdemo.administrator.gdcp.ui.fragment.ScheduleFragment1;
import com.qqdemo.administrator.gdcp.ui.fragment.ScheduleFragment2;
import com.qqdemo.administrator.gdcp.ui.fragment.ScheduleFragment3;

import java.util.List;


/**
 * Created by Administrator on 2016/12/29.
 */

public class FragmentFactory {


    private static FragmentFactory sFragmentFactory;
    private Fragment mScheduleFragment1;
    private Fragment mScheduleFragment2;
    private Fragment mScheduleFragment3;

    private FragmentFactory(){}

    public static FragmentFactory getInstance(){
     if(sFragmentFactory==null){
         synchronized (FragmentFactory.class){
             if(sFragmentFactory==null){
                 sFragmentFactory=new FragmentFactory();
             }
         }
     }
        return sFragmentFactory;
    }



    public Fragment getScheduleFragment1( List<List<String>> mLists) {
        if(mScheduleFragment1==null){
            mScheduleFragment1=new ScheduleFragment1(mLists);
        }
        return mScheduleFragment1;
    }
 public Fragment getScheduleFragment2( List<List<String>> mLists) {
        if(mScheduleFragment2==null){
            mScheduleFragment2=new ScheduleFragment2(mLists);
        }
        return mScheduleFragment2;
    }

    public Fragment getScheduleFragment3( List<List<String>> mLists) {
        if (mScheduleFragment3== null) {
            mScheduleFragment3 = new ScheduleFragment3(mLists);
        }
        return mScheduleFragment3;
    }

}
