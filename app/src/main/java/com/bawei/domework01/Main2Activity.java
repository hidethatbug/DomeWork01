package com.bawei.domework01;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.bawei.domework01.bean.LoginBean;
import com.bawei.domework01.bean.RegisterBean;
import com.bawei.domework01.mvp.HomeContract;
import com.bawei.domework01.mvp.HomePresenterImpl;
import com.google.gson.Gson;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class Main2Activity extends AppCompatActivity implements HomeContract.HomeView {
    private HomeContract.HomePresenter presenter;

    private Unbinder bind;
    @BindView(R.id.register_phone)
    EditText phone;
    @BindView(R.id.register_pwd)
    EditText pwd;

    @OnClick(R.id.register)
    public void setPresenter(View view) {
        String stringphone = phone.getText().toString();
        String stringpwd = pwd.getText().toString();
        presenter.getRegister(stringphone, stringpwd);
        SharedPreferences.Editor edit = App.getMy().edit();
        edit.putString("pwd", stringpwd);
        edit.putString("phone", stringphone);
        edit.commit();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        bind = ButterKnife.bind(this);
        presenter = new HomePresenterImpl();
        presenter.bind(this);


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        bind.unbind();
        presenter.unbind();
    }

    @Override
    public void getLogin(Object o) {

    }


    @Override
    public void getRegister(Object o) {
        RegisterBean registerBean = (RegisterBean) o;
        if (registerBean.getStatus().equals("0000")) {
            startActivity(new Intent(Main2Activity.this, MainActivity.class));
            finish();
        }
    }

    @Override
    public void getHeadPic(Object o) {

    }
}
