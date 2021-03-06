package com.dxslm.ui;


import android.content.Context;
import android.util.AttributeSet;

import android.widget.ListView;

public class ListViewForScrollView extends ListView {
    private OnReSizeListener listener;

    public interface OnReSizeListener{
        void onResize(int w, int h, int oldw, int oldh);
    }

    public void setSizeChangeListener(OnReSizeListener listener) {
        this.listener = listener;
    }

    public ListViewForScrollView(Context context) {
        super(context);
    }
    public ListViewForScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }
    public ListViewForScrollView(Context context, AttributeSet attrs,int defStyle) {
        super(context, attrs, defStyle);
    }
    @Override
    /**
     * 重写该方法，达到使ListView适应ScrollView的效果
     */
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2,
                MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, expandSpec);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        if(listener!=null) {
            listener.onResize(w, h, oldw, oldh);
        }
    }

}
