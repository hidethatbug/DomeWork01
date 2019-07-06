package com.bawei.domework01.mvp;

import com.bawei.domework01.net.DataCallBack;

import java.io.File;

/**
 * Author:程金柱
 * Date:2019/7/6 15:48
 * Description：
 */

public class HomePresenterImpl implements HomeContract.HomePresenter {
    private HomeContract.HomeModel homeModel;
    private HomeContract.HomeView homeView;

    @Override
    public void getLogin(String phone, String pwd) {
        homeModel.getLogin(phone, pwd, new DataCallBack() {
            @Override
            public void getData(Object o) {
                homeView.getLogin(o);
            }

            @Override
            public void getError(String error) {

            }
        });
    }

    @Override
    public void getRegister(String phone, String pwd) {
        homeModel.getRegister(phone, pwd, new DataCallBack() {
            @Override
            public void getData(Object o) {
                homeView.getRegister(o);
            }

            @Override
            public void getError(String error) {

            }
        });
    }

    @Override
    public void getHeadPic(String sid, String pwd, File file) {
        homeModel.getHeadPic(sid, pwd, file, new DataCallBack() {
            @Override
            public void getData(Object o) {
                homeView.getHeadPic(o);
            }

            @Override
            public void getError(String error) {

            }
        });
    }

    @Override
    public void unbind() {
        if (homeModel != null) {
            homeModel = null;
        }
        if (homeView != null) {
            homeView = null;
        }
    }

    @Override
    public void bind(HomeContract.HomeView homeView) {
        this.homeView = homeView;
        homeModel
                = new HomeModelImpl();
    }
}
