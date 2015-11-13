package com.summerhao.bs.all_list.view_common.customview.paint_other;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BlurMaskFilter;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import com.summerhao.bs.R;
import com.summerhao.bs.utils.MeasureUtil;

/**
 * BlurMaskFilter (遮罩过滤器-图片阴影)
 *
 * @author xiahao
 * @Description TODO
 * @date 2015/11/13
 * @Copyright: Copyright (c) 2015 Shenzhen Tentinet Technology Co., Ltd. Inc. All rights reserved.
 */
public class BlurMaskFilterView extends View {

    private Paint shadowPaint; //画笔
    private Context mContext; //上下文环境引用

    private Bitmap srcBitmap, shadowBitmap; //位图和阴影位图

    private int x, y; //位图绘制时左上角的起点坐标

    public BlurMaskFilterView(Context context) {
        super(context);
    }

    public BlurMaskFilterView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        //关闭硬件加速
        setLayerType(LAYER_TYPE_SOFTWARE, null);

        //初始化画笔
        initPaint();
        //初始化资源
        initRes(mContext);
    }

    private void initPaint() {
        shadowPaint = new Paint(Paint.ANTI_ALIAS_FLAG | Paint.DITHER_FLAG);

        shadowPaint.setColor(Color.DKGRAY);

        shadowPaint.setMaskFilter(new BlurMaskFilter(10, BlurMaskFilter.Blur.NORMAL));
    }

    private void initRes(Context context) {
        //获取位图
        srcBitmap = BitmapFactory.decodeResource(context.getResources(), R.mipmap.a);
        // 获取位图的alpha通道图
        shadowBitmap = srcBitmap.extractAlpha();
           /*
         * 计算位图绘制时左上角的坐标使其位于屏幕中心
         */
        x = MeasureUtil.getScreenSize((Activity) mContext)[0] / 2 - srcBitmap.getWidth() / 2;
        y = MeasureUtil.getScreenSize((Activity) mContext)[1] / 2 - srcBitmap.getHeight() / 2;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //先绘制阴影
        canvas.drawBitmap(shadowBitmap, x, y, shadowPaint);

        //再绘制位图
        canvas.drawBitmap(srcBitmap, x, y, null);
    }
}
