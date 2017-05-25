package com.qqdemo.administrator.gdcp.ui.fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.qqdemo.administrator.gdcp.R;
import com.qqdemo.administrator.gdcp.adapter.DateListAdapter;
import com.qqdemo.administrator.gdcp.presenter.IndexFragmentPresenter;
import com.qqdemo.administrator.gdcp.presenter.impl.IndexFragmentPresenterImpl;
import com.qqdemo.administrator.gdcp.view.indexFragmentView;
import com.youth.banner.Banner;

import java.util.List;
import java.util.Map;

import butterknife.BindView;

/**
 * Created by Administrator on 2017/4/26.
 */

public class IndexFragment extends BaseFragment implements indexFragmentView {
    @BindView(R.id.banner)
    Banner mBanner;

    String[] images = new String[]{
            "http://a1.gdcp.cn/UploadFile/2390/2017/5/16/201751694146706.jpg",
            "http://a1.gdcp.cn/UploadFile/2390/2017/5/8/20175892057930.jpg",
            "http://a1.gdcp.cn/UploadFile/2390/2017/5/16/201751662615944.jpg",
            "http://jsxy.gdcp.cn/UploadFile/2/2017/4/1/20174182586342.jpg",
            "http://jsxy.gdcp.cn/UploadFile/2/2017/3/28/201732891684395.jpg",
            "http://jsxy.gdcp.cn/UploadFile/2/2017/3/27/201732742576882.jpg"
    };
    @BindView(R.id.rv_dates)
    RecyclerView mRvDates;


    @Override
    public int getLayoutResId() {
        return R.layout.fragment_index;
    }

    @Override
    protected void init() {
        super.init();


        IndexFragmentPresenter indexFragmentPresenter = new IndexFragmentPresenterImpl(this);


        indexFragmentPresenter.onloadDate();
        mBanner.setBannerStyle(Banner.CIRCLE_INDICATOR_TITLE);
        //设置轮播样式（没有标题默认为右边,有标题时默认左边）
        //可选样式:
        //Banner.LEFT    指示器居左
        //Banner.CENTER    指示器居中
        //Banner.RIGHT    指示器居右
        // mBanner.setIndicatorGravity(Banner.CENTER);

        //设置轮播要显示的标题和图片对应（如果不传默认不显示标题）
        // mBanner.setBannerTitle(titles);

        //设置是否自动轮播（不设置则默认自动）
        mBanner.isAutoPlay(true);

        //设置轮播图片间隔时间（不设置默认为2000）
        mBanner.setDelayTime(5000);
        //设置图片资源:可选图片网址/资源文件，默认用Glide加载,也可自定义图片的加载框架
        //所有设置参数方法都放在此方法之前执行
        //banner.setImages(images);

        //自定义图片加载框架
        mBanner.setImages(images, new Banner.OnLoadImageListener() {
            @Override
            public void OnLoadImage(ImageView view, Object url) {
                System.out.println("加载中");
                Glide.with(getContext()).load(url).into(view);
                System.out.println("加载完");
            }
        });
        //设置点击事件，下标是从1开始
        mBanner.setOnBannerClickListener(new Banner.OnBannerClickListener() {//设置点击事件
            @Override
            public void OnBannerClick(View view, int position) {
                Toast.makeText(getContext(), "你点击了：" + position, Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public void onLoadDateFailed() {

    }

    @Override
    public void onLoadDateSuccess(List<Map<String, Object>> dateslist) {
        mRvDates.setLayoutManager(new LinearLayoutManager(getContext()));
        mRvDates.setHasFixedSize(true);
        mRvDates.setNestedScrollingEnabled(false);
        mRvDates.setAdapter(new DateListAdapter(dateslist));
    }


}
