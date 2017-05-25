package com.qqdemo.administrator.gdcp.factroy;

import android.support.v4.app.Fragment;
import android.util.Log;

import com.qqdemo.administrator.gdcp.ui.fragment.DZFragment;
import com.qqdemo.administrator.gdcp.ui.fragment.GDFragment;
import com.qqdemo.administrator.gdcp.ui.fragment.HSFragment;
import com.qqdemo.administrator.gdcp.ui.fragment.IndexFragment;
import com.qqdemo.administrator.gdcp.ui.fragment.JDFragment;
import com.qqdemo.administrator.gdcp.ui.fragment.JSJFragment;
import com.qqdemo.administrator.gdcp.ui.fragment.QCFragment;
import com.qqdemo.administrator.gdcp.ui.fragment.SMFragment;
import com.qqdemo.administrator.gdcp.ui.fragment.ScheduleFragment1;
import com.qqdemo.administrator.gdcp.ui.fragment.ScheduleFragment2;
import com.qqdemo.administrator.gdcp.ui.fragment.ScheduleFragment3;
import com.qqdemo.administrator.gdcp.ui.fragment.TMFragment;
import com.qqdemo.administrator.gdcp.ui.fragment.YSFragment;

import java.util.List;


/**
 * Created by Administrator on 2016/12/29.
 */

public class FragmentFactory {
    String TAG="FragmentFactory";


    private static FragmentFactory sFragmentFactory;
    private Fragment mScheduleFragment1;
    private Fragment mScheduleFragment2;
    private Fragment mScheduleFragment3;


    private Fragment mIndexFragment;
    private Fragment mJSJFragment;
    private Fragment mTMFragment;
    private Fragment mQCFragment;
    private Fragment mYSFragment;
    private Fragment mGDFragment;
    private Fragment mHSFragment;
    private Fragment mSMFragment;
    private Fragment mDZFragment;
    private Fragment mJDFragment;



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
    public Fragment getFragment(String name) {
        Log.i(TAG, "getFragment: "+name.substring(0,2));
        switch (name.substring(0,2)) {
            case "首页":
                return getIndexFragment();
            case "计算":
                return getJSJFragment();
            case "土木":
                return getTMFragment();
            case "汽车":
                return getQCFragment();
            case "运输":
                return getYSFragment();
            case "轨道":
                return getGDFragment();
            case "海事":
                return getHSFragment();
            case "商贸":
                return getSMFragment();
            case "电子":
                return getDZFragment();
             case "机电":
                return getJDFragment();
        }
        return null;
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

    public Fragment getJSJFragment( ) {
        if (mJSJFragment== null) {
            mJSJFragment = new JSJFragment();
        }
        return mJSJFragment;
    }

    public Fragment getIndexFragment() {
        if (mIndexFragment== null) {
            mIndexFragment = new IndexFragment();
        }
        return mIndexFragment;
    }

    public Fragment getTMFragment() {
        if (mTMFragment== null) {
            mTMFragment = new TMFragment();
        }
        return mTMFragment;
    }

    public Fragment getQCFragment() {
        if (mQCFragment== null) {
            mQCFragment = new QCFragment();
        }
        return mQCFragment;
    }

    public Fragment getYSFragment() {
        if (mYSFragment== null) {
            mYSFragment = new YSFragment();
        }
        return mYSFragment;
    }

    public Fragment getGDFragment() {
        if (mGDFragment== null) {
            mGDFragment = new GDFragment();
        }
        return mGDFragment;
    }

    public Fragment getHSFragment() {
        if (mHSFragment== null) {
            mHSFragment = new HSFragment();
        }
        return mHSFragment;
    }

    public Fragment getSMFragment() {
        if (mSMFragment== null) {
            mSMFragment = new SMFragment();
        }
        return mSMFragment;
    }

    public Fragment getDZFragment() {
        if (mDZFragment== null) {
            mDZFragment = new DZFragment();
        }
        return mDZFragment;
    }

    public Fragment getJDFragment() {
        if (mJDFragment== null) {
            mJDFragment = new JDFragment();
        }
        return mJDFragment;
    }

}
