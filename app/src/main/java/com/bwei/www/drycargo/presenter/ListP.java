package com.bwei.www.drycargo.presenter;

import com.bwei.www.drycargo.bean.ListBean;
import com.bwei.www.drycargo.model.ListModel;
import com.bwei.www.drycargo.view.IListView;

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subscribers.DisposableSubscriber;

/**
 * author:Created by WangZhiQiang on 2017/12/17.
 */

public class ListP implements IListP {
    private IListView iListView;
    private DisposableSubscriber<ListBean> disposableSubscriber;

    public void attahc(IListView iListView) {
        this.iListView = iListView;
    }

    public void detach() {
        if (iListView != null) {
            iListView = null;
        }
        if (disposableSubscriber.isDisposed()) {
            disposableSubscriber.dispose();
        }
    }

    @Override
    public void getList(String type, int page) {
        ListModel listModel = new ListModel(this);
        listModel.getList(type, page);
    }

    public void getData(Flowable<ListBean> list) {
        disposableSubscriber = list.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSubscriber<ListBean>() {
                    @Override
                    public void onNext(ListBean listBean) {
                        List<ListBean.ResultsBean> results = listBean.getResults();
                        iListView.onSuccess(results);
                    }

                    @Override
                    public void onError(Throwable t) {
                        iListView.onFailed(new Exception(t));
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
