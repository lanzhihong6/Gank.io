package com.lanzhihong.gankio.util;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.lanzhihong.gankio.GankApplication;
import com.lanzhihong.gankio.bean.Android;
import com.lanzhihong.gankio.bean.Meizhi;
import com.squareup.okhttp.Cache;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.logging.HttpLoggingInterceptor;

import java.io.File;
import java.util.concurrent.TimeUnit;

import retrofit.GsonConverterFactory;
import retrofit.Retrofit;
import retrofit.RxJavaCallAdapterFactory;
import retrofit.http.GET;
import retrofit.http.Path;
import rx.Observable;

/**
 * Created by lanzhihong on 2016/3/3.
 */

public class ApiManger {
    private ApiService apiService;
    private Retrofit retrofit;
    private static ApiManger apiManger;
    private static final String BASIC_URL = "http://gank.io";
    private static final OkHttpClient mOkHttpClient = new OkHttpClient();

    private ApiManger() {

    }

    public static ApiManger getInstance() {
        if (apiManger == null) {
            apiManger = new ApiManger();
        }
        return apiManger;
    }

    //服务器返回的数据为json的话可以这样写

    public ApiService getService() {
        if (retrofit == null) {

            Context context = GankApplication.getContext().getApplicationContext();
            //设定30秒超时
            mOkHttpClient.setConnectTimeout(30, TimeUnit.SECONDS);
            mOkHttpClient.setReadTimeout(30,TimeUnit.SECONDS);
            //设置拦截器，以用于自定义Cookies的设置
//            mOkHttpClient.networkInterceptors()
//                    .add(new CookiesInterceptor(context));
            //设置缓存目录
            File cacheDirectory = new File(context.getCacheDir()
                    .getAbsolutePath(), "HttpCache");
            Cache cache = new Cache(cacheDirectory, 20 * 1024 * 1024);

            HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
            logging.setLevel(HttpLoggingInterceptor.Level.BASIC);

            mOkHttpClient.interceptors().add(logging);  //打印 http 日志
            mOkHttpClient.setCache(cache);

            Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
            retrofit = new Retrofit
                    .Builder().baseUrl(BASIC_URL)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .client(mOkHttpClient)
                    .build();
        }
        if (apiService == null) {
            apiService = retrofit.create(ApiService.class);
        }
        return apiService;
    }


    public interface ApiService {

        @GET("/api/data/Android/{count}/{page}")
        Observable<Android> getAndroidList(@Path("count") int count, @Path("page") int page);

        @GET("/api/data/福利/{count}/{page}")
        Observable<Meizhi> getMeizhiList(@Path("count") int count, @Path("page") int page);

    }

}
