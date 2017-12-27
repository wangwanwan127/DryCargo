package com.bwei.www.drycargo.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bwei.www.drycargo.R;
import com.bwei.www.drycargo.adapter.FuliAdapter;
import com.bwei.www.drycargo.adapter.GridAdapter;
import com.bwei.www.drycargo.bean.ListBean;
import com.bwei.www.drycargo.presenter.ListP;
import com.bwei.www.drycargo.view.IListView;
import com.bwei.www.drycargo.view.SpacesItemDecoration;
import com.getbase.floatingactionbutton.FloatingActionButton;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.ArrayList;
import java.util.List;

public class HomeListFragment3 extends Fragment implements IListView, View.OnClickListener {

    private View view;
    private XRecyclerView xrv;
    private int page = 1;
    private ListP listP;
    private List<ListBean.ResultsBean> results;
    private List<ListBean.ResultsBean> resultslist = new ArrayList<>();
    private FuliAdapter adapter;
    Handler handler = new Handler();
    private FloatingActionButton action_grid;
    private FloatingActionButton action_list;
    private FloatingActionButton action_staggle;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.homefragment3, container, false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();
        listener();
        getData("福利", page);
        xrv.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        resultslist.clear();
                        getData("福利", page);
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
                        getData("福利", page++);
                        adapter.notifyDataSetChanged();
                        xrv.loadMoreComplete();
                    }
                }, 1000);
            }
        });
    }

    private void listener() {
        action_grid.setOnClickListener(this);
        action_list.setOnClickListener(this);
        action_staggle.setOnClickListener(this);
    }

    private void getData(String name, int page) {
        listP = new ListP();
        listP.attahc(this);
        listP.getList(name, page);

    }

    private void initView() {
        xrv = (XRecyclerView) view.findViewById(R.id.xrv);
        action_grid = (FloatingActionButton) getActivity().findViewById(R.id.action_grid);
        action_list = (FloatingActionButton) getActivity().findViewById(R.id.action_list);
        action_staggle = (FloatingActionButton) getActivity().findViewById(R.id.action_staggle);

    }

    @Override
    public void onSuccess(Object object) {
        results = (List<ListBean.ResultsBean>) object;
        resultslist.addAll(results);
        StaggeredGridLayoutManager manager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        xrv.setLayoutManager(manager);
        adapter = new FuliAdapter(getActivity(), resultslist);
        xrv.setAdapter(adapter);
        //设置item之间的间隔
        SpacesItemDecoration decoration = new SpacesItemDecoration(16);
        xrv.addItemDecoration(decoration);
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

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.action_grid:
                GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 2);
                xrv.setLayoutManager(gridLayoutManager);
                GridAdapter gridAdapter = new GridAdapter(getActivity(), resultslist);
                xrv.setAdapter(gridAdapter);
//                adapter.notifyDataSetChanged();

                break;
            case R.id.action_list:
                LinearLayoutManager linearLayout = new LinearLayoutManager(getActivity());
                xrv.setLayoutManager(linearLayout);
                adapter.notifyDataSetChanged();

                break;
            case R.id.action_staggle:
                StaggeredGridLayoutManager manager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
                xrv.setLayoutManager(manager);
                adapter = new FuliAdapter(getActivity(), resultslist);
                xrv.setAdapter(adapter);
                break;
        }
    }
}
