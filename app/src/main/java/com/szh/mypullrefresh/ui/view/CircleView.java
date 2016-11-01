package com.szh.mypullrefresh.ui.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.graphics.Shader;
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
    private int defaultColor = 0xf0d;

    /** 默认的控件的宽度*/
    private int defaultWidth=0;
    private int defaultHeight=0;

    private int x=0;
    private boolean add=true;

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

//        canvas.drawRect(60, 90, 160, 100, new Paint());// 长方形


        RectF rectF=new RectF(x,0,320-x,320);

        canvas.drawOval(rectF, new Paint());// 椭圆

        if(add && x<=160){
            x=x+4;
            add=true;
        }else if (x >0){
            add=false;
            x=x-4;
        }else{
            add= true;
        }
        invalidate();
        super.onDraw(canvas);

        // 创建画笔
        Paint p = new Paint();
        p.setColor(Color.RED);// 设置红色

        canvas.drawText("画圆：", 10, 20, p);// 画文本
        canvas.drawCircle(60, 20, 10, p);// 小圆
        p.setAntiAlias(true);// 设置画笔的锯齿效果。 true是去除，大家一看效果就明白了
        canvas.drawCircle(120, 20, 20, p);// 大圆

        canvas.drawText("画线及弧线：", 10, 60, p);
        p.setColor(Color.GREEN);// 设置绿色
        canvas.drawLine(60, 40, 100, 40, p);// 画线
        canvas.drawLine(110, 40, 190, 80, p);// 斜线
        //画笑脸弧线
        p.setStyle(Paint.Style.STROKE);//设置空心
        RectF oval1=new RectF(150,20,180,40);
        canvas.drawArc(oval1, 180, 180, false, p);//小弧形 第一个参数外切矩形、第二个是开始角度，顺时针方向。第三个参数弧的于圆心角。第四个是否是扇形
        oval1.set(190, 20, 220, 40);
        canvas.drawArc(oval1, 180, 180, false, p);//小弧形
        oval1.set(160, 30, 210, 60);
        canvas.drawArc(oval1, 0, 180, false, p);//小弧形

        canvas.drawText("画矩形：", 10, 80, p);
        p.setColor(Color.GRAY);// 设置灰色
        p.setStyle(Paint.Style.FILL);//设置填满
        canvas.drawRect(60, 60, 80, 80, p);// 正方形
        canvas.drawRect(60, 90, 160, 100, p);// 长方形

        canvas.drawText("画扇形和椭圆:", 10, 120, p);

        Shader mShader = new LinearGradient(0, 0, 100, 100,
                new int[] { Color.RED, Color.GREEN, Color.BLUE, Color.YELLOW,
                        Color.LTGRAY }, null, Shader.TileMode.REPEAT); // 一个材质,打造出一个线性梯度沿著一条线。
        p.setShader(mShader);
        // p.setColor(Color.BLUE);
        RectF oval2 = new RectF(60, 100, 200, 240);// 设置个新的长方形，扫描测量
        canvas.drawArc(oval2, 200, 130, true, p);
        // 画弧，第一个参数是RectF：该类是第二个参数是角度的开始，第三个参数是多少度，第四个参数是真的时候画扇形，是假的时候画弧线
        //画椭圆，把oval改一下
        oval2.set(210,100,250,130);
        canvas.drawOval(oval2, p);

        canvas.drawText("画三角形：", 10, 200, p);
        // 绘制这个三角形,你可以绘制任意多边形
        Path path = new Path();
        path.moveTo(80, 200);// 此点为多边形的起点
        path.lineTo(120, 250);
        path.lineTo(80, 250);
        path.close(); // 使这些点构成封闭的多边形
        canvas.drawPath(path, p);

        // 你可以绘制很多任意多边形，比如下面画六连形
        p.reset();//重置
        p.setColor(Color.LTGRAY);
        p.setStyle(Paint.Style.STROKE);//设置空心
        Path path1=new Path();
        path1.moveTo(180, 200);
        path1.lineTo(200, 200);
        path1.lineTo(210, 210);
        path1.lineTo(200, 220);
        path1.lineTo(180, 220);
        path1.lineTo(170, 210);
        path1.close();//封闭
        canvas.drawPath(path1, p);


        //画圆角矩形
        p.setStyle(Paint.Style.FILL);//充满
        p.setColor(Color.LTGRAY);
        p.setAntiAlias(true);// 设置画笔的锯齿效果
        canvas.drawText("画圆角矩形:", 10, 260, p);
        RectF oval3 = new RectF(80, 260, 200, 300);// 设置个新的长方形
        canvas.drawRoundRect(oval3, 20, 15, p);//第二个参数是x半径，第三个参数是y半径

        //画贝塞尔曲线
        canvas.drawText("画贝塞尔曲线:", 10, 310, p);
        p.reset();
        p.setStyle(Paint.Style.STROKE);
        p.setColor(Color.GREEN);
        Path path2=new Path();
        path2.moveTo(100, 320);//设置Path的起点
        path2.quadTo(150, 310, 170, 400); //设置贝塞尔曲线的控制点坐标和终点坐标
        canvas.drawPath(path2, p);//画出贝塞尔曲线

        //画点
        p.setStyle(Paint.Style.FILL);
        canvas.drawText("画点：", 10, 390, p);
        canvas.drawPoint(60, 390, p);//画一个点
        canvas.drawPoints(new float[]{60,400,65,400,70,400}, p);//画多个点

        //画图片，就是贴图
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher);
        canvas.drawBitmap(bitmap, 250,360, p);


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
