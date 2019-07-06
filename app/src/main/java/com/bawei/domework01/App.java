package com.bawei.domework01;

import android.app.Application;
import android.content.SharedPreferences;

/**
 * Author:程金柱
 * Date:2019/7/6 16:00
 * Description：
 */

public class App extends Application {

    private static SharedPreferences my;

    public static SharedPreferences getMy() {
        return my;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        my = getSharedPreferences("my", MODE_PRIVATE);

    }
}
