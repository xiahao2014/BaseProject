package com.summerhao.bs.activity.ui_common.drawerlayout;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;

import com.summerhao.bs.R;
import com.summerhao.bs.utils.ToastUtil;


/**
 * 侧滑菜单栏的实现
 */
public class DrawerLayoutActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drawerlayout);
        // KJActivityStack.create().addActivity(this);

        Toolbar toolbar = (Toolbar) findViewById(R.id.id_toolbar);
        setSupportActionBar(toolbar);

        final DrawerLayout layout = (DrawerLayout) findViewById(R.id.drawlayout);

        NavigationView navigationView = (NavigationView) findViewById(R.id.id_nv_menu);

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.nav_home:
                        ToastUtil.showToast(DrawerLayoutActivity.this, "点击了home");
                        break;
                }
                return true;
            }
        });
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                layout.openDrawer(Gravity.LEFT);
            }
        });

        StatusBarCompat.compat(this);

    }

    @Override
    public void onBackPressed() {
        finish();
        overridePendingTransition(R.anim.push_left_in, R.anim.push_right_out);

    }

}
