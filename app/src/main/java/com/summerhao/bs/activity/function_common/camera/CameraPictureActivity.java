package com.summerhao.bs.activity.function_common.camera;

import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.summerhao.bs.R;
import com.summerhao.bs.activity.BaseActivity;
import com.summerhao.bs.activity.ui_common.drawerlayout.StatusBarCompat;
import com.summerhao.bs.activity.view_common.swipeback.BaseSwipeBackActivity;
import com.summerhao.bs.utils.CommonUtil;
import com.summerhao.bs.utils.GetPathFromUri4kitkat;
import com.summerhao.bs.utils.ToastUtil;

import java.io.File;


/**
 * 项目名称：BaseProject
 * 类描述：项目中经常用到 设置头像的功能
 * 创建人：xiahao
 * 创建时间：2015/10/24 16:25
 * 修改人：xiahao
 * 修改时间：2015/10/24 16:25
 * 修改备注：
 */
public class CameraPictureActivity extends BaseSwipeBackActivity implements OnClickListener {

    /**
     * 返回后的图片显示
     */
    private ImageView iv_show;

    private Button btn_camera, btn_picture;

    /**
     * 选择图库的返回码
     */
    private static final int CODE_STOREFRONT_PICTURE_REQUEST = 0xa0;
    /**
     * 选择照相的返回码
     */
    private static final int CODE_STOREFRONT_CAMERA_REQUEST = 0xa1;
    private String capturePath_signboard;

    @Override
    protected int getContentViewId() {
        return R.layout.activity_camer_picture;
    }

    @Override
    protected void initToolBar() {
        StatusBarCompat.compat(this);
        ImageButton iv_left = (ImageButton) findViewById(R.id.iv_left);
        iv_left.setBackground(getResources().getDrawable(R.drawable.back_selector));
        iv_left.setVisibility(View.VISIBLE);
        iv_left.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        TextView tv_title = (TextView) findViewById(R.id.tv_title);
        tv_title.setText("Camera");
    }

    @Override
    protected void findViews() {
        iv_show = (ImageView) findViewById(R.id.iv_show_image);
        btn_camera = (Button) findViewById(R.id.btn_camera);
        btn_picture = (Button) findViewById(R.id.btn_pircture);
    }

    @Override
    protected void init() {

    }


    @Override
    protected void widgetListener() {
        btn_camera.setOnClickListener(this);
        btn_picture.setOnClickListener(this);
    }

    // 从本地相册选取图片作为头像
    private void choseHeadImageFromGallery(int galleryCode) {
        Intent intentFromGallery = new Intent();
        // 设置文件类型
        intentFromGallery.setType("image/*");
        intentFromGallery.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intentFromGallery, galleryCode);
    }

    // 启动手机相机拍摄照片作为头像
    private void choseHeadImageFromCameraCapture(int cameraCode) {
        Intent intentFromCapture = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        // 判断存储卡是否可用，存储照片文件
        if (CommonUtil.hasSdcard()) {
            // String state = Environment.getExternalStorageState();
            String out_file_path = getCustomerServicePath();
            File dir = new File(out_file_path);
            if (!dir.exists()) {
                dir.mkdirs();
            }
            capturePath_signboard = out_file_path + System.currentTimeMillis() + ".jpg";

            intentFromCapture.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(new File(capturePath_signboard)));
        }

        startActivityForResult(intentFromCapture, cameraCode);
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_camera:
                choseHeadImageFromCameraCapture(CODE_STOREFRONT_CAMERA_REQUEST);
                break;
            case R.id.btn_pircture:
                choseHeadImageFromGallery(CODE_STOREFRONT_PICTURE_REQUEST);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {


        // 用户没有进行有效的设置操作，返回
        if (resultCode == RESULT_CANCELED) {
            ToastUtil.showToast(CameraPictureActivity.this, "取消");
            return;
        }
        switch (requestCode) {
            case CODE_STOREFRONT_PICTURE_REQUEST:   // 选择相册返回
                capturePath_signboard = GetPathFromUri4kitkat.getPath(this, intent.getData());
                //此处可根据 自己需要 设置图片的缓存
                iv_show.setImageURI(Uri.parse("file://" + capturePath_signboard));
                // ImageLoader.getInstance().displayImage("file://" + capturePath_signboard, img_storefront, options);
                break;
            case CODE_STOREFRONT_CAMERA_REQUEST:// 照相返回
                if (CommonUtil.hasSdcard()) {// 判断是否有SD卡
                    iv_show.setImageURI(Uri.parse("file://" + capturePath_signboard));
                    //ImageLoader.getInstance().displayImage("file://" + capturePath_signboard, img_storefront, options);
                    //setWidthHeight(img_storefront);
                } else {
                    Toast.makeText(getApplication(), "没有SDCard!", Toast.LENGTH_LONG).show();
                }
                break;
        }


        super.onActivityResult(requestCode, resultCode, intent);
    }


    /**
     * 拍照图片存放地址
     *
     * @return
     */
    public String getCustomerServicePath() {
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            return Environment.getExternalStorageDirectory() + "/juanpi/app/customerService/";
        }
        return null;
    }


}
