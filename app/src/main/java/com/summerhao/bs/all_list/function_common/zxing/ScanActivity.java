package com.summerhao.bs.all_list.function_common.zxing;

import android.app.Activity;
import android.os.Bundle;
import android.os.PersistableBundle;

import com.summerhao.bs.utils.IntentUtil;


/**
 * @author xiahao
 * @Description TODO
 * @date 2015/11/3
 * @Copyright: Copyright (c) 2015 Shenzhen Tentinet Technology Co., Ltd. Inc. All rights reserved.
 */
public class ScanActivity extends Activity{

    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        IntentUtil.gotoActivity(this, CaptureActivity.class);
    }
}
