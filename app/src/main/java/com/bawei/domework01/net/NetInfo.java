package com.bawei.domework01.net;

import android.util.Log;

import com.bawei.domework01.bean.HeadPicBean;
import com.bawei.domework01.bean.LoginBean;
import com.bawei.domework01.bean.RegisterBean;

import io.reactivex.Observable;
import okhttp3.MultipartBody;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Url;

/**
 * Author:程金柱
 * Date:2019/7/6 15:23
 * Description：
 */

public interface NetInfo {
    @POST
    @FormUrlEncoded
    Observable<LoginBean> getLogin(
            @Url String url,
            @Field("phone") String phone,
            @Field("pwd") String pwd

    );

    @POST
    @FormUrlEncoded
    Observable<RegisterBean> getRegister(
            @Url String url,
            @Field("phone") String phone,
            @Field("pwd") String pwd

    );


    @Multipart
    @POST
    Observable<HeadPicBean> getHeadPic(
            @Url String url,
            @Header("userId") String phone,
            @Header("sessionId") String pwd,
            @Part MultipartBody.Part MultipartBody

    );
}
