package com.bwei.www.drycargo.utils;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * author:Created by WangZhiQiang on 2017/12/17.
 */

public class RetrofitUtils {
    private static volatile RetrofitUtils instance;
    private final RetrofitService service;

    private RetrofitUtils() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://gank.io/api/data/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        service = retrofit.create(RetrofitService.class);
    }

    public static RetrofitUtils getInstance() {
        if (instance == null) {
            synchronized (RetrofitUtils.class) {
                if (null == instance) {
                    instance = new RetrofitUtils();
                }
            }
        }
        return instance;
    }

    public RetrofitService getService() {
        return service;
    }

}
