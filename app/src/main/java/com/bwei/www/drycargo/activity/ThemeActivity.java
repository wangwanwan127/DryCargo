package com.bwei.www.drycargo.activity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bwei.www.drycargo.R;

import org.greenrobot.eventbus.EventBus;

import me.imid.swipebacklayout.lib.SwipeBackLayout;
import me.imid.swipebacklayout.lib.app.SwipeBackActivity;

public class ThemeActivity extends SwipeBackActivity implements View.OnClickListener {

    private ImageView back;
    private Button green;
    private Button blue;
    private Button yellow;
    private Button pink;
    private Button zi;
    private Button red;

    private SharedPreferences user;
    private LinearLayout title;
    private int color;
    private SwipeBackLayout mSwipeBackLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_theme);
        // 可以调用该方法，设置是否允许滑动退出
        setSwipeBackEnable(true);
        mSwipeBackLayout = getSwipeBackLayout();
        // 设置滑动方向，可设置EDGE_LEFT, EDGE_RIGHT, EDGE_ALL, EDGE_BOTTOM
        mSwipeBackLayout.setEdgeTrackingEnabled(SwipeBackLayout.EDGE_LEFT);
        // 滑动退出的效果只能从边界滑动才有效果，如果要扩大touch的范围，可以调用这个方法
        mSwipeBackLayout.setEdgeSize(200);
        user = getSharedPreferences("Color", MODE_PRIVATE);
        color = user.getInt("color", 0);
        initView();
        listener();

    }

    @Override
    protected void onResume() {
        super.onResume();
        if (color != 0) {
            title.setBackgroundColor(color);
        }
    }

    private void listener() {
        back.setOnClickListener(this);
        green.setOnClickListener(this);
        blue.setOnClickListener(this);
        yellow.setOnClickListener(this);
        pink.setOnClickListener(this);
        zi.setOnClickListener(this);
        red.setOnClickListener(this);
    }

    private void initView() {
        title = (LinearLayout) findViewById(R.id.title);
        back = (ImageView) findViewById(R.id.back);
        green = (Button) findViewById(R.id.green);
        blue = (Button) findViewById(R.id.blue);
        yellow = (Button) findViewById(R.id.yellow);
        pink = (Button) findViewById(R.id.pink);
        zi = (Button) findViewById(R.id.zi);
        red = (Button) findViewById(R.id.red);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back:
                finish();
                break;
            case R.id.green:
                int color = getResources().getColor(R.color.GREEN);
                EventBus.getDefault().post(color);
                user.edit().putInt("color", color).commit();
//                mLayout.setBackgroundColor(ContextCompat.getColor(this, R.color.colorAccent));
                title.setBackgroundColor(ContextCompat.getColor(this, R.color.GREEN));
                break;
            case R.id.blue:
                int color2 = getResources().getColor(R.color.BLUE);
                EventBus.getDefault().post(color2);
                user.edit().putInt("color", color2).commit();
                title.setBackgroundColor(ContextCompat.getColor(this, R.color.BLUE));
                break;
            case R.id.yellow:
                int color3 = getResources().getColor(R.color.YELLOW);
                EventBus.getDefault().post(color3);
                user.edit().putInt("color", color3).commit();
                title.setBackgroundColor(ContextCompat.getColor(this, R.color.YELLOW));

                break;
            case R.id.pink:
                int color4 = getResources().getColor(R.color.PINK);
                EventBus.getDefault().post(color4);
                user.edit().putInt("color", color4).commit();
                title.setBackgroundColor(ContextCompat.getColor(this, R.color.PINK));
                break;
            case R.id.zi:
                int color5 = getResources().getColor(R.color.ZI);
                EventBus.getDefault().post(color5);
                user.edit().putInt("color", color5).commit();
                title.setBackgroundColor(ContextCompat.getColor(this, R.color.ZI));
                break;
            case R.id.red:
                int color6 = getResources().getColor(R.color.RED);
                EventBus.getDefault().post(color6);
                user.edit().putInt("color", color6).commit();
                title.setBackgroundColor(ContextCompat.getColor(this, R.color.RED));
                break;
        }
    }
}
