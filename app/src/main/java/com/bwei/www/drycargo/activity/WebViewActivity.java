package com.bwei.www.drycargo.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bwei.www.drycargo.R;

import me.imid.swipebacklayout.lib.SwipeBackLayout;
import me.imid.swipebacklayout.lib.app.SwipeBackActivity;

public class WebViewActivity extends SwipeBackActivity implements View.OnClickListener {

    private WebView webView;
    private ImageView back;
    private SharedPreferences user;
    private int color;
    private LinearLayout ll;
    private SwipeBackLayout mSwipeBackLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);
        // 可以调用该方法，设置是否允许滑动退出
        setSwipeBackEnable(true);
        mSwipeBackLayout = getSwipeBackLayout();
        // 设置滑动方向，可设置EDGE_LEFT, EDGE_RIGHT, EDGE_ALL, EDGE_BOTTOM
        mSwipeBackLayout.setEdgeTrackingEnabled(SwipeBackLayout.EDGE_LEFT);
        // 滑动退出的效果只能从边界滑动才有效果，如果要扩大touch的范围，可以调用这个方法
        mSwipeBackLayout.setEdgeSize(200);
        user = getSharedPreferences("Color", MODE_PRIVATE);
        color = user.getInt("color", 0);
        Intent intent = getIntent();
        String url = intent.getStringExtra("url");
        initView();
        if (color != 0) {
            ll.setBackgroundColor(color);
        }
        listener();
        webView.loadUrl(url);
    }

    private void listener() {
        back.setOnClickListener(this);
    }

    private void initView() {
        webView = (WebView) findViewById(R.id.webview);
        back = (ImageView) findViewById(R.id.back);
        ll = (LinearLayout) findViewById(R.id.ll);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back:
                finish();
                break;

        }
    }
}
