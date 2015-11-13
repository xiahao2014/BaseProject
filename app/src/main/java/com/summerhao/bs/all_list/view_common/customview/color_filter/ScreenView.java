package com.summerhao.bs.all_list.view_common.customview.color_filter;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.util.AttributeSet;
import android.view.View;

import com.summerhao.bs.R;
import com.summerhao.bs.utils.MeasureUtil;

/**
 *  测试Screen模式的View(Android提供的几个色彩混合模式中最好的，它可以让图像焦媃幻化，有一种色调均和的感觉)
 * @author xiahao
 * @Description TODO
 * @date 2015/11/12
 * @Copyright: Copyright (c) 2015 Shenzhen Tentinet Technology Co., Ltd. Inc. All rights reserved.
 */
public class ScreenView extends View{

    private Paint mPaint;//画笔
    private Bitmap bitmapSrc;//原图
    private PorterDuffXfermode porterDuffXfermode;//混合模式

    private int x,y;//位图绘制时左上角的起点坐标
    private int screenW,screenH;


    public ScreenView(Context context) {
        super(context);
    }

    public ScreenView(Context context, AttributeSet attrs) {
        super(context, attrs);
        //实例化混合模式
        porterDuffXfermode = new PorterDuffXfermode(PorterDuff.Mode.SCREEN);

        // 初始化画笔
        initPaint();

        // 初始化资源
        initRes(context);
    }
    /**
     * 初始化画笔
     */
    private void initPaint() {
        // 实例化画笔
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    }

    /**
     * 初始化资源
     */
    private void initRes(Context context) {
        // 获取位图
        bitmapSrc = BitmapFactory.decodeResource(context.getResources(), R.mipmap.a3);

        // 获取包含屏幕尺寸的数组
        int[] screenSize = MeasureUtil.getScreenSize((Activity) context);

        // 获取屏幕尺寸
        screenW = screenSize[0];
        screenH = screenSize[1];

		/*
		 * 计算位图绘制时左上角的坐标使其位于屏幕中心
		 * 屏幕坐标x轴向左偏移位图一半的宽度
		 * 屏幕坐标y轴向上偏移位图一半的高度
		 */
        x = screenW / 2 - bitmapSrc.getWidth() / 2;
        y = screenH / 2 - bitmapSrc.getHeight() / 2;

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);


		/*
		 * 将绘制操作保存到新的图层（更官方的说法应该是离屏缓存）我们将在1/3中学习到Canvas的全部用法这里就先follow me
		 */
        int sc = canvas.saveLayer(0, 0, screenW, screenH, null, Canvas.ALL_SAVE_FLAG);

        //先绘制一层带透明度的颜色
        canvas.drawColor(0xCC1C093E);
        //设置混合模式
        canvas.drawBitmap(bitmapSrc,x,y,mPaint);
        //还原混合模式
        mPaint.setXfermode(null);
        //还原画布
        canvas.restoreToCount(sc);
    }
}
