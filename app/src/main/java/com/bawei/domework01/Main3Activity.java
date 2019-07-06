package com.bawei.domework01;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.bawei.domework01.bean.HeadPicBean;
import com.bawei.domework01.bean.LoginBean;
import com.bawei.domework01.mvp.HomeContract;
import com.bawei.domework01.mvp.HomePresenterImpl;
import com.google.gson.Gson;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.config.PictureMimeType;
import com.luck.picture.lib.entity.LocalMedia;

import java.io.File;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class Main3Activity extends AppCompatActivity implements HomeContract.HomeView {
    private static final String TAG = "";
    private HomeContract.HomePresenter presenter;
    @BindView(R.id.noclick)
    RelativeLayout noclick;
    @BindView(R.id.xiangche)
    Button xiangche;
    @BindView(R.id.img_head)
    ImageView img_head;
    @BindView(R.id.xiangji)
    Button xiangji;
    @BindView(R.id.quxiao)
    Button quxiao;
    private Unbinder bind;

    private int ONE = 1;
    private int TWO = 2;
    private String name;

    @OnClick(R.id.head_huan)
    public void setPresenter() {
        noclick.setVisibility(View.VISIBLE);
    }

    @OnClick(R.id.quxiao)
    public void setQuxiao() {
        noclick.setVisibility(View.GONE);
    }

    @OnClick(R.id.xiangche)
    public void setXiangche() {
        PictureSelector.create(Main3Activity.this)
                .openGallery(PictureMimeType.ofImage())
                .maxSelectNum(1)
                .minSelectNum(1)
                .forResult(PictureConfig.CHOOSE_REQUEST);
    }

    @OnClick(R.id.xiangji)
    public void setXiangji() {
        PictureSelector.create(Main3Activity.this)
                .openCamera(PictureMimeType.ofImage())
                .maxSelectNum(1)
                .minSelectNum(1)
                .forResult(PictureConfig.REQUEST_CAMERA);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        presenter = new HomePresenterImpl();
        presenter.bind(this);
        bind = ButterKnife.bind(this);
        initDaata();
        noclick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });


    }

    private void initDaata() {
        name = App.getMy().getString("name", "");
        if (!name.equals("")) {
            LoginBean loginBean = new Gson().fromJson(name, LoginBean.class);
            BaseGlide.getGlide(this, loginBean.getResult().getHeadPic(), img_head);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        LoginBean loginBean = new Gson().fromJson(name, LoginBean.class);


            switch (requestCode) {
                case PictureConfig.CHOOSE_REQUEST:
                    List<LocalMedia> localMedia = PictureSelector.obtainMultipleResult(data);
                    LocalMedia media = localMedia.get(0);
                    String cutPath = media.getPath();
                    Log.i(TAG, "onActivityResult: "+cutPath);
                    File file = new File(cutPath);
                    LoginBean.ResultBean result = loginBean.getResult();
                    int userId = result.getUserId();
                    presenter.getHeadPic(
                            String.valueOf(userId), result.getSessionId()
                            , file);
                    break;
                case PictureConfig.REQUEST_CAMERA:
                    List<LocalMedia> localMedia2 = PictureSelector.obtainMultipleResult(data);
                    LocalMedia media2 = localMedia2.get(0);
                    String cutPath2 = media2.getPath();
                    File file2 = new File(cutPath2);
                    LoginBean.ResultBean result2 = loginBean.getResult();
                    int userId2 = result2.getUserId();
                    presenter.getHeadPic(
                            String.valueOf(userId2), result2.getSessionId()
                            , file2);
                    break;
            }
            noclick.setVisibility(View.GONE);


    }

    @Override
    public void getLogin(Object o) {

    }

    @Override
    public void getRegister(Object o) {

    }

    @Override
    public void getHeadPic(Object o) {
        HeadPicBean headPicBean = (HeadPicBean) o;
        BaseGlide.getGlide(this, headPicBean.getHeadPath(), img_head);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.unbind();
        bind.unbind();
    }
}
