package com.lanzhihong.gankio;

import android.app.Application;
import android.content.Context;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.squareup.leakcanary.LeakCanary;

/**
 * Created by lanzhihong on 2016/3/1.
 */
public class GankApplication extends Application {
    private static Context context;   //全局实例的context

    @Override
    public void onCreate() {
        super.onCreate();

        context = this;

        LeakCanary.install(this);  //LeakCanary 的初始化
        Fresco.initialize(context);  //Fresce 的初始化


    }

    public static Context getContext() {
        return context;
    }

}
