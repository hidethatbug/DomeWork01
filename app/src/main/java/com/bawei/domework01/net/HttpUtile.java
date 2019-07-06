package com.bawei.domework01.net;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Author:程金柱
 * Date:2019/7/6 15:18
 * Description：
 */

public class HttpUtile {
    private static HttpUtile httpUtile;
    private final Retrofit retrofit;

    private HttpUtile() {
        OkHttpClient okHttpClien = new OkHttpClient.Builder()
                .addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                .build();
        retrofit = new Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClien)
                .baseUrl("http://172.17.8.100/")
                .build();
    }

    public static HttpUtile getHttpUtile() {
        if (httpUtile == null) {
            synchronized (HttpUtile.class) {
                if (httpUtile == null) {
                    httpUtile = new HttpUtile();
                }
            }
        }
        return httpUtile;
    }

    /**
     * 动态 网络请求
     * @param tClass
     * @param <T>
     * @return
     */
    public <T> T getRetift(Class<T> tClass) {
        return retrofit.create(tClass);
    }
}
