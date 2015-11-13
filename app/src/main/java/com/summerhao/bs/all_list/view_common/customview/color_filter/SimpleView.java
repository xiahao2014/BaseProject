package com.summerhao.bs.all_list.view_common.customview.color_filter;

import android.app.Activity;
import android.content.Context;
import android.graphics.AvoidXfermode;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorMatrix;
import android.graphics.LightingColorFilter;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import com.summerhao.bs.R;
import com.summerhao.bs.utils.MeasureUtil;

/**
 * 自定义view的一些基本的操作
 * 色彩矩阵颜色过滤器 ColorMatrixColorFilter
 * 光照颜色过滤 LightingColorFilter
 * setXfermode(Xfermode xfermode) Xfermode 过渡模式
 * @author xiahao
 * @Description TODO
 * @date 2015/11/12
 * @Copyright: Copyright (c) 2015 Shenzhen Tentinet Technology Co., Ltd. Inc. All rights reserved.
 */
public class SimpleView extends View {

    private Paint mPaint;
    private int radius = 200;
    private Context mContext;
    private Bitmap bitmap;


    private int x;
    private int y;

    private boolean isClick;// 用来标识控件是否被点击过
    private AvoidXfermode avoidXfermode;
    private int w;
    private int h;


    public SimpleView(Context context) {
        super(context);
    }

    public SimpleView(Context context, AttributeSet attrs) {
        super(context, attrs);

        mContext = context;
        initPaint();
        initRes(mContext);


        setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

                if (isClick) {
                    //如果已经被点击了，设置颜色过滤为空还原本色
                    mPaint.setColorFilter(null);
                    isClick = false;
                    System.out.println(isClick);
                } else {
                    //如果未被点击则设置点击过滤颜色为黄色
                    mPaint.setColorFilter(new LightingColorFilter(0xFFFFFFFF, 0x00FFFF00));
                    isClick = true;
                    System.out.println(isClick);
                }
                initPaint();
            }
        });

    }


    private void initPaint() {
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        /**
         * 设置画笔样式为描边，圆环嘛……当然不能填充不然就么意思了
         *
         * 画笔样式分三种：
         * 1.Paint.Style.STROKE：描边
         * 2.Paint.Style.FILL_AND_STROKE：描边并填充
         * 3.Paint.Style.FILL：填充
         */

        mPaint.setStyle(Paint.Style.FILL_AND_STROKE);
        // 设置画笔颜色为浅灰色
        mPaint.setColor(Color.argb(255, 255, 128, 103));
        /**
         * 设置描边的粗细，单位：像素px
         * 注意：当setStrokeWidth(0)的时候描边宽度并不为0而是只占一个像素
         */
        mPaint.setStrokeWidth(10);

        // 生成色彩矩阵
        ColorMatrix colorMatrix = new ColorMatrix(new float[]{


                /*第一行表示的R（红色）的向量
                第二行表示的G（绿色）的向量
                第三行表示的B（蓝色）的向量
                最后一行表示A（透明度）的向量

                矩阵不同的位置表示的RGBA值
                范围在0.0F至2.0F之间，1为保持原图的RGB值
                每一行的第五列数字表示偏移值
                */

//                1, 0, 0, 0, 0,
//                0, 1, 0, 0, 0,
//                0, 0, 1, 0, 0,
//                0, 0, 0, 1, 0,


//                0.5F, 0, 0, 0, 0,
//                0, 0.5F, 0, 0, 0,
//                0, 0, 0.5F, 0, 0,
//                0, 0, 0, 1, 0,

//                0.33F, 0.59F, 0.11F, 0, 0,
//                0.33F, 0.59F, 0.11F, 0, 0,
//                0.33F, 0.59F, 0.11F, 0, 0,
//                0, 0, 0, 1, 0,

//                -1, 0, 0, 1, 1,
//                0, -1, 0, 1, 1,
//                0, 0, -1, 1, 1,
//                0, 0, 0, 1, 0,
                1.438F, -0.122F, -0.016F, 0, -0.03F,
                -0.062F, 1.378F, -0.016F, 0, 0.05F,
                -0.062F, -0.122F, 1.483F, 0, -0.02F,
                0, 0, 0, 1, 0,


        });

        //混合模式
        //  mPaint.setColorFilter(new ColorMatrixColorFilter(colorMatrix));


        // 设置颜色过滤
        //   mPaint.setColorFilter(new PorterDuffColorFilter(Color.RED, PorterDuff.Mode.DARKEN));

        /*
         * 当画布中有跟0XFFFFFFFF色不一样的地方时候才“染”色
         */
        /*

        AvoidXfermode(int opColor, int tolerance, AvoidXfermode.Mode mode)

        该模式下Android会判断画布上的颜色是否会有跟opColor不一样的颜色
        比如我opColor是红色
        那么在TARGET模式下就会去判断我们的画布上是否有存在红色的地方
        如果有，则把该区域“染”上一层我们画笔定义的颜色，否则不“染”色
        而tolerance容差值则表示画布上的像素和我们定义的红色之间的差别该是多少的时候才去“染”的
        比如当前画布有一个像素的色值是(200, 20, 13)，而我们的红色值为(255, 0, 0)
        当tolerance容差值为255时，即便(200, 20, 13)并不等于红色值也会被“染”色
        容差值越大“染”色范围越广反之则反*/

        //AvoidXfermode.Mode.AVOID

       /* 则与TARGET恰恰相反，TARGET是我们指定的颜色是否与画布的颜色一样，
        而AVOID是我们指定的颜色是否与画布不一样，其他的都与TARGET类似*/

        // 16过时 必须关闭硬件加速
        avoidXfermode = new AvoidXfermode(0XFFFFFFFF, 0, AvoidXfermode.Mode.TARGET);

    }

    private void initRes(Context context) {
        // 获取位图
        bitmap = BitmapFactory.decodeResource(context.getResources(), R.mipmap.image);


        /*
         * 计算位图绘制时左上角的坐标使其位于屏幕中心
         * 屏幕坐标x轴向左偏移位图一半的宽度
         * 屏幕坐标y轴向上偏移位图一半的高度
         */
        x = MeasureUtil.getScreenSize((Activity) mContext)[0] / 2 - bitmap.getWidth() / 2;
        y = MeasureUtil.getScreenSize((Activity) mContext)[1] / 2 - bitmap.getHeight() / 2;


         /*
         * 计算位图绘制时左上角的坐标使其位于屏幕中心
         * 屏幕坐标x轴向左偏移位图一半的宽度
         * 屏幕坐标y轴向上偏移位图一半的高度
         */
        w = MeasureUtil.getScreenSize((Activity) mContext)[0] / 2 + bitmap.getWidth() / 2;
        h = MeasureUtil.getScreenSize((Activity) mContext)[1] / 2 + bitmap.getHeight() / 2;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        //绘制圆
        //   canvas.drawCircle(500, 500, radius, mPaint);
        //绘制图片
        //  canvas.drawBitmap(bitmap, x, y, mPaint);


        // 先绘制位图
        canvas.drawBitmap(bitmap, x, y, mPaint);

        // “染”什么色是由我们自己决定的
        mPaint.setARGB(255, 211, 53, 243);

        // 设置AV模式
        mPaint.setXfermode(avoidXfermode);

        // 画一个位图大小一样的矩形
        canvas.drawRect(x, y, w, h, mPaint);
    }
}
