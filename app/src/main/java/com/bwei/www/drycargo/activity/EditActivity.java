package com.bwei.www.drycargo.activity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.bwei.www.drycargo.R;

import me.imid.swipebacklayout.lib.SwipeBackLayout;
import me.imid.swipebacklayout.lib.app.SwipeBackActivity;

public class EditActivity extends SwipeBackActivity implements View.OnClickListener {
    private EditText username;
    private EditText password;
    private EditText dizhi;
    private Button baocun;
    private ImageView back;
    private SharedPreferences user;
    private int color;
    private LinearLayout ll;
    private SwipeBackLayout mSwipeBackLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
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
        if (color != 0) {
            ll.setBackgroundColor(color);
        }
        listener();
    }

    private void listener() {
        back.setOnClickListener(this);
        baocun.setOnClickListener(this);
    }

    private void initView() {
        username = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);
        dizhi = (EditText) findViewById(R.id.dizhi);
        baocun = (Button) findViewById(R.id.baocun);
        back = (ImageView) findViewById(R.id.back);
        ll = (LinearLayout) findViewById(R.id.ll);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back:
                finish();
                break;
            case R.id.baocun:
                String s = username.getText().toString().trim();

                if (s.isEmpty()) {
                    Toast.makeText(this, "昵称不能为空", Toast.LENGTH_SHORT).show();
                    return;
                } else if (password.getText().toString().trim().equals("")) {
                    Toast.makeText(this, "昵称不能为空", Toast.LENGTH_SHORT).show();
                    return;
                } else if (dizhi.getText().toString().trim().equals("")) {
                    Toast.makeText(this, "昵称不能为空", Toast.LENGTH_SHORT).show();
                    return;
                } else {
                    user.edit().putString("name", s).commit();
                    Toast.makeText(this, "保存成功", Toast.LENGTH_SHORT).show();
                    finish();
                }
                break;

        }
    }
}
