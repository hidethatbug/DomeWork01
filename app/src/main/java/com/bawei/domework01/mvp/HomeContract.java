package com.bawei.domework01.mvp;

import com.bawei.domework01.net.DataCallBack;

import java.io.File;

/**
 * Author:程金柱
 * Date:2019/7/6 15:32
 * Description：
 */

public interface HomeContract {
    interface HomeModel {
        void getLogin(String phone, String pwd, DataCallBack dataCallBack);

        void getRegister(String phone, String pwd, DataCallBack dataCallBack);

        void getHeadPic(String phone, String pwd,File file, DataCallBack dataCallBack);
    }

    interface HomeView {
        void getLogin(Object o);

        void getRegister(Object o);

        void getHeadPic(Object o);
    }

    interface HomePresenter {
        void getLogin(String phone, String pwd);

        void getRegister(String phone, String pwd);

        void getHeadPic(String sid, String pwd, File file);

        void unbind();

        void bind(HomeView homeView);
    }
}
