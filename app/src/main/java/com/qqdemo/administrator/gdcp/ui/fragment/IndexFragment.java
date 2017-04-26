package com.qqdemo.administrator.gdcp.ui.fragment;

import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.qqdemo.administrator.gdcp.R;
import com.youth.banner.Banner;

import butterknife.BindView;

/**
 * Created by Administrator on 2017/4/26.
 */

public class IndexFragment extends BaseFragment {
    @BindView(R.id.banner)
    Banner mBanner;

    String[] images= new String[] {
            "http://218.192.170.132/BS80.jpg",
            "http://img.zcool.cn/community/0166c756e1427432f875520f7cc838.jpg",
            "http://img.zcool.cn/community/018fdb56e1428632f875520f7b67cb.jpg",
            "http://img.zcool.cn/community/01c8dc56e1428e6ac72531cbaa5f2c.jpg",
            "http://img.zcool.cn/community/01fda356640b706ac725b2c8b99b08.jpg",
            "http://img.zcool.cn/community/01fd2756e142716ac72531cbf8bbbf.jpg",
            "http://img.zcool.cn/community/0114a856640b6d32f87545731c076a.jpg"};

    //设置图片标题:自动对应
    String[] titles=new String[]{"十大星级品牌联盟，全场2折起","全场2折起","十大星级品牌联盟","嗨购5折不要停","12趁现在","嗨购5折不要停，12.12趁现在","实打实大顶顶顶顶"};

    @Override
    public int getLayoutResId() {
        return R.layout.fragment_index;
    }

    @Override
    protected void init() {
        super.init();

        mBanner.setBannerStyle(Banner.CIRCLE_INDICATOR_TITLE);

        //设置轮播样式（没有标题默认为右边,有标题时默认左边）
        //可选样式:
        //Banner.LEFT    指示器居左
        //Banner.CENTER    指示器居中
        //Banner.RIGHT    指示器居右
        mBanner.setIndicatorGravity(Banner.CENTER);

        //设置轮播要显示的标题和图片对应（如果不传默认不显示标题）
        mBanner.setBannerTitle(titles);

        //设置是否自动轮播（不设置则默认自动）
        mBanner.isAutoPlay(true)    ;

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


}
