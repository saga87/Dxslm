package com.dxslm.ui;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.GridView;

/**
 * Created by fxn on 2017/12/20.
 */

public class GridViewForScrollview extends GridView{
    public GridViewForScrollview(Context context) {
        super(context);
    }

    public GridViewForScrollview(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public GridViewForScrollview(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        // TODO 自动生成的构造函数存根
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        // TODO 自动生成的方法存根
        int expandSpec = View.MeasureSpec.makeMeasureSpec(
                Integer.MAX_VALUE >> 2, View.MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, expandSpec);
    }
}
