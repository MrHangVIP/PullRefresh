package com.szh.mypullrefresh.ui.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import com.szh.mypullrefresh.R;

/**
 * Created by moram on 2016/10/27.
 */
public class CircleView extends View{

    private int mBorderThickness;

    /**  外边框圆的颜色*/
    private int mBorderOutsideColor;
    /** 内部圆的颜色 也就是填充色*/
    private int mBorderInsideColor;
    /** 默认的圆形和边框的颜色*/
    private int defaultColor = 0x00d;

    /** 默认的控件的宽度*/
    private int defaultWidth=0;
    private int defaultHeight=0;



    public CircleView(Context context) {
        super(context);
    }

    public CircleView(Context context, AttributeSet attrs) {
        super(context, attrs);
        setCustomAttributes(attrs,context);
    }

    public CircleView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setCustomAttributes(attrs,context);
    }


    @Override
    protected void onDraw(Canvas canvas) {

        if (defaultWidth == 0) {
            //获取控件的宽度
            defaultWidth = getWidth();

        }
        if (defaultHeight == 0) {
            //获取控件的高度 可用作计算半径
            defaultHeight = getHeight();
        }
        int radius=0;
            radius = (defaultWidth < defaultHeight ? defaultWidth
                    : defaultHeight) / 2;
        drawCircleBorder(canvas, radius ,
                    mBorderInsideColor,Paint.Style.FILL);
        drawCircleBorder(canvas, radius ,
                mBorderOutsideColor,Paint.Style.STROKE);

        super.onDraw(canvas);

    }

    private void setCustomAttributes(AttributeSet attrs,Context context) {
        TypedArray a = context.obtainStyledAttributes(attrs,
                R.styleable.circleView);
        mBorderThickness = a.getDimensionPixelSize(
                R.styleable.circleView_roundedimageview_border_thickness, 0);
        mBorderOutsideColor = a
                .getColor(R.styleable.circleView_roundedimageview_border_outside_color,
                        defaultColor);
        mBorderInsideColor = a.getColor(
                R.styleable.circleView_roundedimageview_border_inside_color, defaultColor);
    }


//    private void drawArc(Canvas canvas,Paint paint) {
//
//        canvas.drawARGB(255, 56, 197, 186);
//
//        RectF rectF = new RectF(100, 100, 300, 300);
//
//        paint.setStrokeWidth(1 * density);//设置线宽
//
//        paint.setColor(0xFF6BB7ED);//设置颜色
//
//        paint.setStyle(Paint.Style.FILL);//默认设置画笔为填充模式
//
////绘制椭圆
//
//        paint.setStyle(Paint.Style.STROKE);//设置画笔为线条模式
//
//        canvas.drawArc(rectF, 0, 359, false, paint);
//
//        paint.setStrokeWidth(1 * density);//设置线宽
//
//        paint.setColor(0xff8bc5ba);//设置颜色
//
//        paint.setStyle(Paint.Style.STROKE);//默认设置画笔为填充模式
//
//        canvas.drawRect(rectF, paint);
//
//        int radius = 0;
//
//
//    }


    /**
     * 画填充圆缘画圆
     */
    private void drawCircleBorder(Canvas canvas, int radius, int color,Paint.Style style) {
        Paint paint = new Paint();
        /* 去锯齿 */
        paint.setAntiAlias(true);
        paint.setFilterBitmap(true);
        paint.setDither(true);
        paint.setColor(color);
        /* 设置paint的　style　为STROKE：空心 */
//        paint.setStyle(Paint.Style.STROKE);
//        paint.setStyle(Paint.Style.FILL);
        paint.setStyle(style);
        /* 设置paint的外框宽度 */
//        paint.setStrokeWidth(10);
        canvas.drawCircle(defaultWidth / 2, defaultHeight / 2, radius, paint);
    }
}
