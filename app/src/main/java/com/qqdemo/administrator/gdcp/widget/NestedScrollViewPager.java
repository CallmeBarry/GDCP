package com.qqdemo.administrator.gdcp.widget;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by Administrator on 2017/4/26.
 */

    public class NestedScrollViewPager extends ViewPager {
        public NestedScrollViewPager(Context context) {
            super(context);
        }

        public NestedScrollViewPager(Context context, AttributeSet attrs) {
            super(context, attrs);
        }

//    private int current;
//    private int height = 0;
//    /**
//     * 保存position与对于的View
//     */
//    private HashMap<Integer, View> mChildrenViews = new LinkedHashMap<Integer, View>();
//    private boolean scrollble = true;
    @Override
        protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

                int height = 0;
                //下面遍历所有child的高度
                for (int i = 0; i < getChildCount(); i++) {
                    View child = getChildAt(i);
                    child.measure(widthMeasureSpec,
                            MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED));
                    int h = child.getMeasuredHeight();
                    if (h > height) //采用最大的view的高度。
                        height = h;
                }

                heightMeasureSpec = MeasureSpec.makeMeasureSpec(height,
                        MeasureSpec.EXACTLY);

                super.onMeasure(widthMeasureSpec, heightMeasureSpec);
//            if (mChildrenViews.size() > current) {
//                View child = mChildrenViews.get(current);
//                child.measure(widthMeasureSpec, MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED));
//                height = child.getMeasuredHeight();
//            }
//
//            heightMeasureSpec = MeasureSpec.makeMeasureSpec(height, MeasureSpec.EXACTLY);
//
//            super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        }
//    public void resetHeight(int current) {
//        this.current = current;
//        if (mChildrenViews.size() > current) {
//
//            LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) getLayoutParams();
//            if (layoutParams == null) {
//                layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, height);
//            } else {
//                layoutParams.height = height;
//            }
//            setLayoutParams(layoutParams);
//        }
//    }
//    /**
//     * 保存position与对于的View
//     */
//    public void setObjectForPosition(View view, int position)
//    {
//        mChildrenViews.put(position, view);
//    }
//
//
//    @Override
//    public boolean onTouchEvent(MotionEvent ev) {
//        if (!scrollble) {
//            return true;
//        }
//        return super.onTouchEvent(ev);
//    }
//
//
//    public boolean isScrollble() {
//        return scrollble;
//    }
//
//    public void setScrollble(boolean scrollble) {
//        this.scrollble = scrollble;
//    }

}


