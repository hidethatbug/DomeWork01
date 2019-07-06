package com.bawei.domework01;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.bawei.domework01.bean.LoginBean;
import com.bawei.domework01.mvp.HomeContract;
import com.bawei.domework01.mvp.HomePresenterImpl;
import com.google.gson.Gson;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class MainActivity extends AppCompatActivity implements HomeContract.HomeView {
    private static final String TAG = "MainActivity";
    private HomeContract.HomePresenter presenter;
    private Unbinder bind;
    @BindView(R.id.login_phone)
    EditText phone;
    @BindView(R.id.login_pwd)
    EditText pwd;

    @OnClick(R.id.login)
    public void setView(View view) {
        String stringphone = phone.getText().toString();
        String stringpwd = pwd.getText().toString();
        presenter.getLogin(stringphone, stringpwd);

    }

    @OnClick(R.id.jump_register)
    public void setPresenter(View view) {
        startActivity(new Intent(MainActivity.this, Main2Activity.class));

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bind = ButterKnife.bind(this);
        presenter = new HomePresenterImpl();
        presenter.bind(this);
        String phone = App.getMy().getString("phone", "");
        String pwd = App.getMy().getString("pwd", "");
        if (!phone.equals("") && !pwd.equals("")) {
            this.phone.setText(phone);
            this.pwd.setText(pwd);
        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        bind.unbind();
        presenter.unbind();
    }

    @Override
    public void getLogin(Object o) {

        LoginBean loginBean = (LoginBean) o;
        if (loginBean.getStatus().equals("0000")) {
            String s = new Gson().toJson(loginBean);
            SharedPreferences.Editor edit = App.getMy().edit();
            edit.putString("name", s);
            edit.commit();
            startActivity(new Intent(MainActivity.this, Main3Activity.class));
            finish();
        }
    }

    @Override
    public void getRegister(Object o) {

    }

    @Override
    public void getHeadPic(Object o) {

    }
}
