package com.bwei.www.drycargo.utils;

import com.bwei.www.drycargo.bean.ListBean;

import io.reactivex.Flowable;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * author:Created by WangZhiQiang on 2017/12/17.
 */

public interface RetrofitService {
//    @GET("data/Android/{id1}/{id2}")
//    Call<Message<List<Child_bean>>> get(@Path("id1") int id1, @Path("id2") int id2);

    //        http://gank.io/api/data/App/10/1
    @GET("{type}/10/{page}")
    Flowable<ListBean> list(@Path("type") String type, @Path("page") int page);
}
