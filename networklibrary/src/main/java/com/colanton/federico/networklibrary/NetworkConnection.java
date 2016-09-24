package com.colanton.federico.networklibrary;

import android.content.Context;

import com.colanton.federico.networklibrary.retrofit.RetrofitModule;

import java.io.Serializable;

import retrofit2.Retrofit;

public class NetworkConnection implements Serializable {

    private static volatile NetworkConnection instance = null;

    private NetworkConnection() {
    }

    public static NetworkConnection getInstance() {

        if (instance == null) {    // check 1

            synchronized (NetworkConnection.class) {

                if (instance == null) {    // check 2

                    instance = new NetworkConnection();
                }
            }
        }

        return instance;
    }

    public <S> S initializeRetrofitInstance(Context context, String baseUrl, Class<S> serviceClass) {

        Retrofit retrofit = RetrofitModule.provideRetrofit(context, baseUrl);

        return retrofit.create(serviceClass);
    }
}
