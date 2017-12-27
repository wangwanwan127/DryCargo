package com.bwei.www.drycargo.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bwei.www.drycargo.R;
import com.bwei.www.drycargo.activity.WebViewActivity;
import com.bwei.www.drycargo.adapter.CargoListAdapter;
import com.bwei.www.drycargo.bean.ListBean;
import com.bwei.www.drycargo.presenter.ListP;
import com.bwei.www.drycargo.view.IListView;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.ArrayList;
import java.util.List;

/**
 * author:Created by WangZhiQiang on 2017/12/17.
 */

public class ReadFragment extends Fragment implements IListView {
    private List<ListBean.ResultsBean> resultslist = new ArrayList<>();
    private View view;
    private XRecyclerView xrv;
    //    private String title;
    private int page = 1;
    private ListP listP;
    private List<ListBean.ResultsBean> results;
    private CargoListAdapter adapter;
    Handler handler = new Handler();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.readfragment, container, false);
        return view;
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();
//        Toast.makeText(getActivity(), title, Toast.LENGTH_SHORT).show();
        getData("Android", page);
        xrv.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        resultslist.clear();
                        getData("Android", page);
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
                        getData("Android", page++);
                        adapter.notifyDataSetChanged();
                        xrv.loadMoreComplete();
                    }
                }, 1000);
            }
        });
    }

    private void getData(String title, int page) {
        listP = new ListP();
        listP.attahc(this);
        listP.getList(title, page);
    }

    private void initView() {
        xrv = (XRecyclerView) view.findViewById(R.id.xrv);
    }

    @Override
    public void onSuccess(Object object) {
        results = (List<ListBean.ResultsBean>) object;
        resultslist.addAll(results);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        xrv.setLayoutManager(linearLayoutManager);
        adapter = new CargoListAdapter(getActivity(), resultslist);
        xrv.setAdapter(adapter);
        adapter.setOnRecyclerViewItemClick(new CargoListAdapter.OnRecyclerViewItemClick() {
            @Override
            public void onitemClick(View view, String url) {
                Intent intent = new Intent(getActivity(), WebViewActivity.class);
                intent.putExtra("url", url);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onFailed(Exception e) {

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (listP != null) {
            listP.detach();
        }
    }
}
