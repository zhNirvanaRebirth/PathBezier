package com.onektower.bezier;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by zhwilson on 2016/11/30.
 * 二阶贝塞尔曲线：有两个数据点，一个控制点
 */
public class QuadBezier extends View {
    public QuadBezier(Context context) {
        this(context, null);
    }

    public QuadBezier(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public QuadBezier(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
}
