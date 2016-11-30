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
 * 三阶贝塞尔曲线:两个数据点，两个控制点
 */
public class CubicBezier extends View {
    public static final int CONTROL_POINT_ONE = 1;
    public static final int CONTROL_POINT_TWO = CONTROL_POINT_ONE + 1;
    private Paint pointPaint;//数据点画笔
    private Paint bezierPaint;//贝塞尔曲线画笔
    private Paint linePaint;//数据点与控制点连线画笔
    private int width;//控件宽度
    private int height;//控件高度
    private PointF dataPoint1, dataPoint2, controlPoint1, controlPoint2;//数据点与控制点
    private int controlPoint = CONTROL_POINT_ONE;

    public CubicBezier(Context context) {
        this(context, null);
    }

    public CubicBezier(Context context, AttributeSet attrs) {
        this(context, attrs, R.attr.NR_BezierTheme);
    }

    public CubicBezier(Context context, AttributeSet attrs, int defStyleAttr) {
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
        bezierPaint.setStrokeWidth(8);

        linePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        linePaint.setColor(Color.GRAY);
        linePaint.setStrokeWidth(5);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        width = w;
        height = h;
        dataPoint1 = new PointF(width / 2 - 300, height / 2);
        dataPoint2 = new PointF(width / 2 + 300, height / 2);
        controlPoint1 = new PointF(width / 2 - 300, height / 2 - 100);
        controlPoint2 = new PointF(width / 2 - 300, height / 2 - 100);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawPoint(dataPoint1.x, dataPoint1.y, pointPaint);
        canvas.drawPoint(dataPoint2.x, dataPoint2.y, pointPaint);
        canvas.drawPoint(controlPoint1.x, controlPoint1.y, pointPaint);
        canvas.drawPoint(controlPoint2.x, controlPoint2.y, pointPaint);

        canvas.drawLine(dataPoint1.x, dataPoint1.y, controlPoint1.x, controlPoint1.y, linePaint);
        canvas.drawLine(dataPoint2.x, dataPoint2.y, controlPoint2.x, controlPoint2.y, linePaint);
        canvas.drawLine(controlPoint1.x, controlPoint1.y, controlPoint2.x, controlPoint2.y, linePaint);

        Path cubicPath = new Path();
        cubicPath.moveTo(dataPoint1.x, dataPoint1.y);
        cubicPath.cubicTo(controlPoint1.x, controlPoint1.y, controlPoint2.x, controlPoint2.y, dataPoint2.x, dataPoint2.y);
        canvas.drawPath(cubicPath, bezierPaint);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (controlPoint == CONTROL_POINT_ONE) {
            controlPoint1.x = event.getX();
            controlPoint1.y = event.getY();
        } else if (controlPoint == CONTROL_POINT_TWO) {
            controlPoint2.x = event.getX();
            controlPoint2.y = event.getY();
        }
        postInvalidate();
        return true;
    }

    public void setControlPoint(int controlPoint) {
        this.controlPoint = controlPoint;
    }
}
