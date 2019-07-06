package com.bawei.domework01.mvp;

import com.bawei.domework01.bean.HeadPicBean;
import com.bawei.domework01.bean.LoginBean;
import com.bawei.domework01.bean.RegisterBean;
import com.bawei.domework01.net.DataCallBack;
import com.bawei.domework01.net.HttpUtile;
import com.bawei.domework01.net.NetInfo;

import java.io.File;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

/**
 * Author:程金柱
 * Date:2019/7/6 15:37
 * Description：
 */

public class HomeModelImpl implements HomeContract.HomeModel {
    @Override
    public void getLogin(String phone, String pwd, final DataCallBack dataCallBack) {
        NetInfo retift = HttpUtile.getHttpUtile().getRetift(NetInfo.class);
        retift.getLogin("small/user/v1/login/", phone, pwd)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<LoginBean>() {
                    @Override
                    public void accept(LoginBean loginBean) throws Exception {
                        dataCallBack.getData(loginBean);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {

                    }
                });
    }

    @Override
    public void getRegister(String phone, String pwd, final DataCallBack dataCallBack) {
        NetInfo retift = HttpUtile.getHttpUtile().getRetift(NetInfo.class);
        retift.getRegister("small/user/v1/register/", phone, pwd)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<RegisterBean>() {
                    @Override
                    public void accept(RegisterBean registerBean) throws Exception {
                        dataCallBack.getData(registerBean);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {

                    }
                });
    }

    @Override
    public void getHeadPic(String phone, String pwd, File file, final DataCallBack dataCallBack) {
        NetInfo retift = HttpUtile.getHttpUtile().getRetift(NetInfo.class);
        RequestBody requestBody = MultipartBody.create(MediaType.parse("image/*"), file);
        MultipartBody.Part image = MultipartBody.Part.createFormData("image", file.getName(), requestBody);
        retift.getHeadPic("small/user/verify/v1/modifyHeadPic", phone, pwd, image)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<HeadPicBean>() {
                    @Override
                    public void accept(HeadPicBean headPicBean) throws Exception {
                        dataCallBack.getData(headPicBean);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {

                    }
                });
    }
}
