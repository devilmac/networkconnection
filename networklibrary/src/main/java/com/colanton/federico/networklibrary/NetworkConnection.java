package com.colanton.federico.networklibrary;

import android.content.Context;

import com.colanton.federico.networklibrary.retrofit.RetrofitModule;

import retrofit2.Retrofit;

public class NetworkConnection {

    private static NetworkConnection ourInstance = new NetworkConnection();

    private NetworkConnection() {
    }

    public static NetworkConnection getInstance() {

        return ourInstance;
    }

    public <S> S initializeRetrofitInstance(Context context, String baseUrl, Class<S> serviceClass) {

        Retrofit retrofit = RetrofitModule.provideRetrofit(context, baseUrl);

        return retrofit.create(serviceClass);
    }
}
