package com.summerhao.bs.activity.ui_common.bookfip;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import com.summerhao.bs.Constant;
import com.summerhao.bs.R;

import java.io.IOException;



/**
 * 项目名称：BaseProject
 * 类描述：小说翻页效果
 * 创建人：xiahao
 * 创建时间：2015/10/26 21:04
 * 修改人：xiahao
 * 修改时间：2015/10/26 21:04
 * 修改备注：
 */
public class BookFipActivity extends Activity {

    /**
     * Called when the activity is first created.
     */
    private PageWidget mPageWidget;
    Bitmap mCurPageBitmap, mNextPageBitmap;
    Canvas mCurPageCanvas, mNextPageCanvas;
    BookPageFactory pagefactory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        requestWindowFeature(Window.FEATURE_NO_TITLE);
//        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
//                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        mPageWidget = new PageWidget(this);
        setContentView(mPageWidget);

        DisplayMetrics dm = new DisplayMetrics();
        // 取得窗口属性
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        Constant.width = dm.widthPixels;
        Constant.height = dm.heightPixels;

        System.out.println(Constant.width);
        System.out.println(Constant.height);

        initData();
        widgetListener();
    }




    protected void initData() {
        mCurPageBitmap = Bitmap.createBitmap(Constant.width, Constant.height,
                Bitmap.Config.ARGB_8888);
        mNextPageBitmap = Bitmap.createBitmap(Constant.width, Constant.height,
                Bitmap.Config.ARGB_8888);

        mCurPageCanvas = new Canvas(mCurPageBitmap);
        mNextPageCanvas = new Canvas(mNextPageBitmap);
        pagefactory = new BookPageFactory(Constant.width, Constant.height);

        pagefactory.setBgBitmap(BitmapFactory.decodeResource(
                this.getResources(), R.mipmap.bg2));

        try {
            pagefactory.openbook("/mnt/sdcard/test.txt");
            pagefactory.onDraw(mCurPageCanvas);
        } catch (IOException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
            Toast.makeText(this, "电子书不存在,请将test.txt放在SD卡根目录下",
                    Toast.LENGTH_LONG).show();
        }

        mPageWidget.setBitmaps(mCurPageBitmap, mCurPageBitmap);
    }


    protected void widgetListener() {
        mPageWidget.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent e) {
                // TODO Auto-generated method stub

                boolean ret = false;
                if (v == mPageWidget) {
                    if (e.getAction() == MotionEvent.ACTION_DOWN) {
                        mPageWidget.abortAnimation();
                        mPageWidget.calcCornerXY(e.getX(), e.getY());

                        pagefactory.onDraw(mCurPageCanvas);
                        if (mPageWidget.DragToRight()) {
                            try {
                                pagefactory.prePage();
                            } catch (IOException e1) {
                                // TODO Auto-generated catch block
                                e1.printStackTrace();
                            }
                            if (pagefactory.isfirstPage())
                                return false;
                            pagefactory.onDraw(mNextPageCanvas);
                        } else {
                            try {
                                pagefactory.nextPage();
                            } catch (IOException e1) {
                                // TODO Auto-generated catch block
                                e1.printStackTrace();
                            }
                            if (pagefactory.islastPage())
                                return false;
                            pagefactory.onDraw(mNextPageCanvas);
                        }
                        mPageWidget.setBitmaps(mCurPageBitmap, mNextPageBitmap);
                    }

                    ret = mPageWidget.doTouchEvent(e);
                    return ret;
                }
                return false;
            }

        });
    }

    @Override
    public void onBackPressed() {
        finish();
        overridePendingTransition(R.anim.push_left_in, R.anim.push_right_out);

    }
}
