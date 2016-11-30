package com.onektower.bezier;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by zhwilson on 2016/11/30.
 * 二阶贝塞尔曲线：有两个数据点，一个控制点
 * <p/>
 * 这里要演示控制点动态控制贝塞尔曲线效果，就不设置控件的大小了，默认全屏
 */
public class QuadBezier extends View {
    private Paint pointPaint;//数据点画笔
    private Paint bezierPaint;//贝塞尔曲线画笔
    private Paint linePaint;//数据点与控制点连线画笔
    private int width;//控件宽度
    private int height;//控件高度
    private PointF dataPoint1, dataPoint2, controlPoint;//数据点与控制点

    public QuadBezier(Context context) {
        this(context, null);
    }

    public QuadBezier(Context context, AttributeSet attrs) {
        this(context, attrs, R.attr.NR_BezierTheme);
    }

    public QuadBezier(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initPaint();
    }

    private void initPaint() {
        pointPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        pointPaint.setColor(Color.GRAY);
        pointPaint.setStrokeWidth(10);

        bezierPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        bezierPaint.setColor(Color.RED);
        bezierPaint.setStyle(Paint.Style.STROKE);
        bezierPaint.setStrokeWidth(5);

        linePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        linePaint.setColor(Color.GRAY);
        linePaint.setStrokeWidth(5);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        width = w;
        height = h;
        dataPoint1 = new PointF(width / 2 - 200, height / 2);
        dataPoint2 = new PointF(width / 2 + 200, height / 2);
        controlPoint = new PointF(width / 2, height / 2 - 100);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawPoint(dataPoint1.x, dataPoint1.y, pointPaint);
        canvas.drawPoint(dataPoint2.x, dataPoint2.y, pointPaint);
        canvas.drawPoint(controlPoint.x, controlPoint.y, pointPaint);

        canvas.drawLine(dataPoint1.x, dataPoint1.y, controlPoint.x, controlPoint.y, linePaint);
        canvas.drawLine(dataPoint2.x, dataPoint2.y, controlPoint.x, controlPoint.y, linePaint);

        Path quadPath = new Path();
        quadPath.moveTo(dataPoint1.x, dataPoint1.y);
        quadPath.quadTo(controlPoint.x, controlPoint.y, dataPoint2.x, dataPoint2.y);
        canvas.drawPath(quadPath, bezierPaint);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getActionMasked() == MotionEvent.ACTION_UP) {
            controlPoint.x = width / 2;
            controlPoint.y = height / 2 - 100;
        } else {
            controlPoint.x = event.getX();
            controlPoint.y = event.getY();
        }
        postInvalidate();
        return true;
    }
}
