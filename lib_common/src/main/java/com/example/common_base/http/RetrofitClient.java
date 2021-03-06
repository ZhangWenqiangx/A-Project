package com.example.common_base.http;

import com.example.common_base.base.BaseApplication;
import com.franmontiel.persistentcookiejar.PersistentCookieJar;
import com.franmontiel.persistentcookiejar.cache.SetCookieCache;
import com.franmontiel.persistentcookiejar.persistence.SharedPrefsCookiePersistor;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.LoggingEventListener;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {

    private static final String API_HOST = "https://www.wanandroid.com/";
    private static RetrofitClient instance;
    private static OkHttpClient okHttpClient;
    private static Retrofit retrofit;

    private RetrofitClient() {

        PersistentCookieJar cookieJar = new PersistentCookieJar(new SetCookieCache(),
                new SharedPrefsCookiePersistor(BaseApplication.sApplication));

        okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(15, TimeUnit.SECONDS)
                .readTimeout(15, TimeUnit.SECONDS)
                .cookieJar(cookieJar)
                .eventListenerFactory(new LoggingEventListener.Factory())
                .addInterceptor(InterceptorUtil.headerInterceptor())
                .addInterceptor(InterceptorUtil.logInterceptor())
                .build();
        retrofit = new Retrofit.Builder()
                .baseUrl(API_HOST)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build();
    }

    public static RetrofitClient getInstance() {
        if (instance == null) {
            synchronized (RetrofitClient.class) {
                if (instance == null) {
                    instance = new RetrofitClient();
                }
            }
        }
        return instance;
    }

    public OkHttpClient getOkHttpClient() {
        return okHttpClient;
    }

    public Retrofit getRetrofit() {
        return retrofit;
    }
}