package com.bwei.www.drycargo.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bwei.www.drycargo.R;
import com.bwei.www.drycargo.adapter.DrawableAdapter;
import com.bwei.www.drycargo.fragment.HomeFragment;
import com.bwei.www.drycargo.fragment.MeFragment;
import com.bwei.www.drycargo.fragment.ReadFragment;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.hjm.bottomtabbar.BottomTabBar;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private RecyclerView rv_drawable;
    private BottomTabBar bottomTabBar;
    private ImageView search;
    private ImageView edit;
    private SharedPreferences user;
    private LinearLayout ll;
    private int color;
    private int color1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Fresco.initialize(this);
        EventBus.getDefault().register(this);
        getSupportActionBar().hide();
        user = getSharedPreferences("Color", MODE_PRIVATE);
        color = user.getInt("color", 0);

        initView();
        if (color != 0) {
            ll.setBackgroundColor(color);
        } else {
            color = getResources().getColor(R.color.home);
        }
        listener();
        drawable();
        bottomtabar();
    }
    @Subscribe
    public void onEventMainThread(Object event) {
        if (event != null) {
            color = (int) event;
            if (color != 0) {
                ll.setBackgroundColor(color);
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    private void listener() {
        search.setOnClickListener(this);
        edit.setOnClickListener(this);
    }

    private void bottomtabar() {
        bottomTabBar.init(getSupportFragmentManager())
                .setImgSize(35, 35)
                .setFontSize(12)
                .setTabPadding(4, 6, 10)
                .setChangeColor(color, Color.BLACK)
                .addTabItem("Home", R.drawable.bottom_home, HomeFragment.class)
                .addTabItem("Read", R.drawable.bottom_read, ReadFragment.class)
                .addTabItem("Me", R.drawable.bottom_me, MeFragment.class)
                .isShowDivider(false)
                .setOnTabChangeListener(new BottomTabBar.OnTabChangeListener() {
                    @Override
                    public void onTabChange(int position, String name) {

                    }
                });

    }

    private void drawable() {
//        G:\workSpace\DryCargo\app\src\main\res\drawable\a.png
        ArrayList<String> imgList = new ArrayList<>();
        imgList.add("res://drawable/" + R.drawable.drawer_icon_client);
        imgList.add("res://drawable/" + R.drawable.app);
        imgList.add("res://drawable/" + R.drawable.drawer_icon_recommend);
        imgList.add("res://drawable/" + R.drawable.drawer_icon_resource);
        imgList.add("res://drawable/" + R.drawable.drawer_icon_theme);
        imgList.add("res://drawable/" + R.drawable.drawer_icon_video);
        ArrayList<String> strings = new ArrayList<>();
        strings.add("前端");
        strings.add("瞎推荐");
        strings.add("App");
        strings.add("扩展资源");
        strings.add("休息视频");
        strings.add("主题切换");

        LinearLayoutManager manager = new LinearLayoutManager(this);
        rv_drawable.setLayoutManager(manager);
        DrawableAdapter adapter = new DrawableAdapter(this, strings, imgList);
        rv_drawable.setAdapter(adapter);
        adapter.setOnRecyclerViewItemClick(new DrawableAdapter.OnRecyclerViewItemClick() {
            @Override
            public void onitemClick(View view, int position, String name) {
                if (position == 5) {
                    Intent intent = new Intent(MainActivity.this, ThemeActivity.class);
                    startActivity(intent);
                } else {
                    Intent intents = new Intent(MainActivity.this, ListActivity.class);
                    intents.putExtra("name", name);
                    startActivity(intents);
                }
            }
        });
    }

    private void initView() {
        rv_drawable = (RecyclerView) findViewById(R.id.drawer_layout).findViewById(R.id.rv_drawable);
        bottomTabBar = (BottomTabBar) findViewById(R.id.bottomTabBar);
        search = (ImageView) findViewById(R.id.layout_titlebar).findViewById(R.id.search);
        edit = (ImageView) findViewById(R.id.layout_titlebar).findViewById(R.id.edit);
        ll = (LinearLayout) findViewById(R.id.layout_titlebar).findViewById(R.id.ll);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.search:
//                Toast.makeText(this, "点击了搜索", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(this, SearchActivity.class);
                startActivity(intent);
                break;
            case R.id.edit:
                Intent intents = new Intent(this, EditActivity.class);
                startActivity(intents);
                break;

        }

    }
}
