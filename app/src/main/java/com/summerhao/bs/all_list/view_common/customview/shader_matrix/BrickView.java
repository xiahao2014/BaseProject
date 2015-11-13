package com.summerhao.bs.all_list.view_common.customview.shader_matrix;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.summerhao.bs.R;

/**
 * @author xiahao
 * @Description TODO
 * @date 2015/11/13
 * @Copyright: Copyright (c) 2015 Shenzhen Tentinet Technology Co., Ltd. Inc. All rights reserved.
 */
public class BrickView extends View {

    private Paint mFillPaint, mStrokePaint; //填充和描边的画笔
    private BitmapShader mBitmapShader; //bitmap着色器
    private float posX, posY;//触摸点的XY坐标


    public BrickView(Context context) {
        super(context);
    }

    public BrickView(Context context, AttributeSet attrs) {
        super(context, attrs);
        // 初始化画笔
        initPaint();
    }

    private void initPaint() {
        /**
         * 实例化描边画笔并设置参数
         */
        mStrokePaint = new Paint(Paint.ANTI_ALIAS_FLAG | Paint.DITHER_FLAG);
        mStrokePaint.setColor(0xFF000000);
        mStrokePaint.setStyle(Paint.Style.STROKE);
        mStrokePaint.setStrokeWidth(5);

        // 实例化填充画笔
        mFillPaint = new Paint();
        /**
         * 生成BitmapShader
         */
        Bitmap mBitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.brick);
        mBitmapShader = new BitmapShader(mBitmap, Shader.TileMode.REPEAT, Shader.TileMode.REPEAT);

        //x0和y0表示渐变的起点坐标而x1和y1则表示渐变的终点
        //LinearGradient(float x0, float y0, float x1, float y1, int color0, int color1, Shader.TileMode tile)

        //红色到黄色的渐变起点坐标在整个渐变区域（left, top, right, bottom定义了渐变的区域）的起点，而终点则在渐变区域长度 * 10%的地方，
        // 而黄色到绿色呢则从渐变区域10%开始到50%的地方以此类推，positions可以为空(均匀分布)：
        //mPaint.setShader(new LinearGradient(left, top, right, bottom, new int[] { Color.RED, Color.YELLOW, Color.GREEN, Color.CYAN, Color.BLUE }, null, Shader.TileMode.MIRROR));
        //LinearGradient(float x0, float y0, float x1, float y1, int[] colors, float[] positions, Shader.TileMode tile)

        mFillPaint.setShader(mBitmapShader);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        /*
         * 手指移动时获取触摸点坐标并刷新视图
         */
        if (event.getAction() == MotionEvent.ACTION_MOVE) {
            posX = event.getX();
            posY = event.getY();

            invalidate();
        }
        return true;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawCircle(posX,posY,300,mFillPaint);
        canvas.drawCircle(posX,posY,300,mStrokePaint);

    }
}
