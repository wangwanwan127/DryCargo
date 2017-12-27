package com.bwei.www.drycargo.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.bwei.www.drycargo.R;
import com.bwei.www.drycargo.adapter.CargoListAdapter;
import com.bwei.www.drycargo.bean.ListBean;
import com.bwei.www.drycargo.presenter.ListP;
import com.bwei.www.drycargo.view.IListView;
import com.jcodecraeer.xrecyclerview.ProgressStyle;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.ArrayList;
import java.util.List;

import me.imid.swipebacklayout.lib.SwipeBackLayout;
import me.imid.swipebacklayout.lib.app.SwipeBackActivity;

public class ListActivity extends SwipeBackActivity implements View.OnClickListener, IListView {

    private ImageView back;
    private XRecyclerView xrv;
    private String name;
    private int page = 1;
    Handler handler = new Handler();
    private List<ListBean.ResultsBean> results;
    private List<ListBean.ResultsBean> resultslist = new ArrayList<>();
    private CargoListAdapter adapter;
    private ListP listP;
    private SharedPreferences user;
    private int color;
    private LinearLayout ll;
    private SwipeBackLayout mSwipeBackLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
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
        name = intent.getStringExtra("name");
        Toast.makeText(this, name, Toast.LENGTH_SHORT).show();
        initView();
        if (color != 0) {
            ll.setBackgroundColor(color);
        }
        xrv.setRefreshProgressStyle(ProgressStyle.BallSpinFadeLoader);
        xrv.setLoadingMoreProgressStyle(ProgressStyle.BallClipRotate);
        listener();
        getData(name, page);
        xrv.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        resultslist.clear();
                        getData(name, page);
                        adapter.notifyDataSetChanged();
                        xrv.refreshComplete();
                    }
                }, 1000);
            }

            @Override
            public void onLoadMore() {
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        getData(name, page++);
                        adapter.notifyDataSetChanged();
                        xrv.loadMoreComplete();
                    }
                }, 1000);
            }
        });

    }

    private void getData(String name, int page) {
        listP = new ListP();
        listP.attahc(this);
        listP.getList(name, page);
    }

    private void listener() {
        back.setOnClickListener(this);
    }

    private void initView() {
        back = (ImageView) findViewById(R.id.back);
        xrv = (XRecyclerView) findViewById(R.id.xrv);
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

    @Override
    public void onSuccess(Object object) {
        results = (List<ListBean.ResultsBean>) object;
        resultslist.addAll(results);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        xrv.setLayoutManager(linearLayoutManager);
        adapter = new CargoListAdapter(this, resultslist);
        xrv.setAdapter(adapter);
        adapter.setOnRecyclerViewItemClick(new CargoListAdapter.OnRecyclerViewItemClick() {
            @Override
            public void onitemClick(View view, String url) {
                Intent intent = new Intent(ListActivity.this, WebViewActivity.class);
                intent.putExtra("url", url);
                startActivity(intent);
            }
        });

    }

    @Override
    public void onFailed(Exception e) {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (listP != null) {
            listP.detach();
        }
    }
}
