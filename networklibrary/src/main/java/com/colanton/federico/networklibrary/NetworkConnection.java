package com.colanton.federico.networklibrary;

import android.content.Context;

import com.colanton.federico.networklibrary.retrofit.RetrofitModule;

import retrofit2.Retrofit;

public class NetworkConnection {

    private static NetworkConnection ourInstance = new NetworkConnection();

    private Retrofit retrofit;

    private NetworkConnection() {
    }

    public static NetworkConnection getInstance() {

        return ourInstance;
    }

    public void createRetrofitInstance(Context context, String baseUrl) {

        this.retrofit = RetrofitModule.provideRetrofit(context, baseUrl);
    }

    public <S> S getRetrofitService(Class<S> serviceClass) {

        return retrofit.create(serviceClass);
    }
}
