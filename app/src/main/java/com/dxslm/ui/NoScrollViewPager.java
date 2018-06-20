package com.dxslm.ui;


import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * 控制viewpager是否可以左右滑动
 * 去除滑动动画
 * setNoScroll(boolean noScroll)
 *
 * @author ex-panyong
 */
public class NoScrollViewPager extends ViewPager {
    private boolean result = false;

    public NoScrollViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public NoScrollViewPager(Context context) {
        super(context);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent arg0) {
        if (result)
            return super.onInterceptTouchEvent(arg0);
        else
            return false;
    }

    @Override
    public boolean onTouchEvent(MotionEvent arg0) {
        if (result)
            return super.onTouchEvent(arg0);
        else
            return false;
    }
    //去除页面切换时的滑动翻页效果
    @Override
    public void setCurrentItem(int item, boolean smoothScroll) {
        // TODO Auto-generated method stub
        super.setCurrentItem(item, smoothScroll);
    }

    @Override
    public void setCurrentItem(int item) {
        // TODO Auto-generated method stub
        super.setCurrentItem(item, false);
    }
}
