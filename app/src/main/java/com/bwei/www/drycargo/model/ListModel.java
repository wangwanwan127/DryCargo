package com.bwei.www.drycargo.model;

import com.bwei.www.drycargo.bean.ListBean;
import com.bwei.www.drycargo.presenter.ListP;
import com.bwei.www.drycargo.utils.RetrofitUtils;

import io.reactivex.Flowable;

/**
 * author:Created by WangZhiQiang on 2017/12/17.
 */

public class ListModel implements IListModel {
    private ListP listP;

    public ListModel(ListP listP) {
        this.listP = listP;
    }

    @Override
    public void getList(String type, int page) {
        Flowable<ListBean> list = RetrofitUtils.getInstance().getService().list(type, page);
        listP.getData(list);


    }
}
