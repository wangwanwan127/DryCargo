package com.bwei.www.drycargo.fragment;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bwei.www.drycargo.R;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;

import static android.content.Context.MODE_PRIVATE;


public class HomeFragment extends Fragment {

    private View view;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private ArrayList<String> titleList = new ArrayList<>();
    private ArrayList<Fragment> fragmentList = new ArrayList<>();
    private SharedPreferences user;
    private int color;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.homefragment, container, false);

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        EventBus.getDefault().register(this);
        user = getActivity().getSharedPreferences("Color", MODE_PRIVATE);
        color = user.getInt("color", 0);
        initView();
        if (color != 0) {
            tabLayout.setTabTextColors(Color.rgb(132, 131, 142), color);
            tabLayout.setSelectedTabIndicatorColor(color);
        } else {
            color = getResources().getColor(R.color.home);
        }
        initData();
        MyPageAdapter adapter = new MyPageAdapter(getActivity().getSupportFragmentManager());
        viewPager.setAdapter(adapter);
        //设置TabLayout的模式
        tabLayout.setTabMode(TabLayout.MODE_FIXED);
        //让tablayout和Viewpager关联;
        tabLayout.setupWithViewPager(viewPager);

    }

    @Subscribe
    public void onEventMainThread(Object event) {
        if (event != null) {
            color = (int) event;
            if (color != 0) {
                tabLayout.setTabTextColors(Color.rgb(132, 131, 142), color);
                tabLayout.setSelectedTabIndicatorColor(color);
            }
        }
    }

    class MyPageAdapter extends FragmentPagerAdapter {

        public MyPageAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            if (position == 2) {
                return new HomeListFragment3();
            } else if (position == 1) {
                return new HomeListFragment2();
            } else {
                return new HomeListFragment();
            }
        }


        @Override
        public int getCount() {
            return fragmentList.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return titleList.get(position);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    private void initData() {
        titleList.add("ANDROID");
        titleList.add("IOS");
        titleList.add("福利");
//        for (int i = 0; i < titleList.size(); i++) {
//            fragmentList.add(new HomeListFragment());
//        }
        fragmentList.add(new HomeListFragment());
        fragmentList.add(new HomeListFragment2());
        fragmentList.add(new HomeListFragment3());

    }

    private void initView() {
        tabLayout = (TabLayout) view.findViewById(R.id.tabLayout);
        viewPager = (ViewPager) view.findViewById(R.id.viewPager);
    }


}
