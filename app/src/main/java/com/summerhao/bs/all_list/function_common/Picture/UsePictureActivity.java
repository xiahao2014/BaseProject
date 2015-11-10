package com.summerhao.bs.all_list.function_common.Picture;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.zxing.WriterException;
import com.summerhao.bs.R;
import com.summerhao.bs.all_list.ui_common.drawerlayout.StatusBarCompat;
import com.summerhao.bs.all_list.view_common.swipeback.BaseSwipeBackActivity;
import com.summerhao.bs.utils.ToastUtil;
import com.summerhao.bs.utils.ToolPicture;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * @author xiahao
 * @Description 对各种图片的处理操作
 * @date 2015/11/4
 * @Copyright: Copyright (c) 2015 Shenzhen Tentinet Technology Co., Ltd. Inc. All rights reserved.
 */
public class UsePictureActivity extends BaseSwipeBackActivity implements View.OnClickListener {


    @InjectView(R.id.btn_cut)
    Button btnCut;
    @InjectView(R.id.show_image)
    ImageView showImage;
    @InjectView(R.id.btn_angle_0)
    Button btnAngle0;
    @InjectView(R.id.btn_angle_90)
    Button btnAngle90;
    @InjectView(R.id.btn_angle_180)
    Button btnAngle180;
    @InjectView(R.id.btn_angle_270)
    Button btnAngle270;
    @InjectView(R.id.iv_angle)
    ImageView ivAngle;
    @InjectView(R.id.iv_watermark)
    Button ivWatermark;
    @InjectView(R.id.iv_round)
    Button ivRound;
    @InjectView(R.id.iv_reflection)
    Button ivReflection;
    @InjectView(R.id.iv_effect)
    ImageView ivEffect;
    @InjectView(R.id.btn_authcode)
    Button btnAuthcode;
    @InjectView(R.id.iv_authcode)
    ImageView ivAuthcode;


    @Override
    protected int getContentViewId() {
        return R.layout.activity_use_picture;

    }

    @Override
    protected void initToolBar() {
        StatusBarCompat.compat(this);
        ImageButton iv_left = (ImageButton) findViewById(R.id.iv_left);
        iv_left.setBackground(getResources().getDrawable(R.drawable.back_selector));
        iv_left.setVisibility(View.VISIBLE);
        iv_left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        TextView tv_title = (TextView) findViewById(R.id.tv_title);
        tv_title.setText("功能封装");
    }

    @Override
    protected void findViews() {
    }

    @Override
    protected void init() {

    }

    @Override
    protected void widgetListener() {
        btnCut.setOnClickListener(this);
        btnAngle0.setOnClickListener(this);
        btnAngle90.setOnClickListener(this);
        btnAngle180.setOnClickListener(this);
        btnAngle270.setOnClickListener(this);
        ivWatermark.setOnClickListener(this);
        ivRound.setOnClickListener(this);
        ivReflection.setOnClickListener(this);
        ivEffect.setOnClickListener(this);
        btnAuthcode.setOnClickListener(this);
        ivAuthcode.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_cut:
                Bitmap bitmap = null;
                try {
                    bitmap = ToolPicture.makeQRImage("www.baidu.com", 200, 300);
                } catch (WriterException e) {
                    e.printStackTrace();
                }
                showImage.setImageBitmap(bitmap);
                break;
            case R.id.btn_angle_0:
                ivAngle.setImageBitmap(ToolPicture.rotaingBitmap(0, ToolPicture.gainBitmap(this, R.mipmap.back_default)));
                break;
            case R.id.btn_angle_90:
                ivAngle.setImageBitmap(ToolPicture.rotaingBitmap(90, ToolPicture.gainBitmap(this, R.mipmap.back_default)));
                break;
            case R.id.btn_angle_180:
                ivAngle.setImageBitmap(ToolPicture.rotaingBitmap(180, ToolPicture.gainBitmap(this, R.mipmap.back_default)));
                break;
            case R.id.btn_angle_270:
                ivAngle.setImageBitmap(ToolPicture.rotaingBitmap(270, ToolPicture.gainBitmap(this, R.mipmap.back_default)));
                break;
            case R.id.iv_watermark:
                ivEffect.setImageBitmap(ToolPicture.composeWatermark(ToolPicture.gainBitmap(this, R.mipmap.reflection), ToolPicture.gainBitmap(this, R.mipmap.shuiyin)));
                break;
            case R.id.iv_round:
                ivEffect.setImageBitmap(ToolPicture.toRoundCorner(ToolPicture.gainBitmap(this, R.mipmap.reflection), 10));
                break;
            case R.id.iv_reflection:
                ivEffect.setImageBitmap(ToolPicture.makeReflectionImage(ToolPicture.gainBitmap(this, R.mipmap.reflection)));
                break;
            case R.id.btn_authcode:
                ivAuthcode.setImageBitmap(ToolPicture.makeValidateCode(320,480));
                ToastUtil.showToast(this,ToolPicture.gainValidateCodeValue());
                break;

        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.inject(this);
    }
}
