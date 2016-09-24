package com.colanton.federico.networklibrary;

import android.content.Context;

import com.colanton.federico.networklibrary.retrofit.RetrofitModule;
import com.colanton.federico.networklibrary.retrofit.okhttp.OkHttpModule;
import com.google.gson.GsonBuilder;
import com.google.gson.TypeAdapterFactory;

import java.io.Serializable;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NetworkConnection implements Serializable {

    private static volatile NetworkConnection instance = null;

    private NetworkConnection() {
    }

    public static NetworkConnection getInstance() {

        if (instance == null) {

            synchronized (NetworkConnection.class) {

                if (instance == null) {

                    instance = new NetworkConnection();
                }
            }
        }

        return instance;
    }

    private Retrofit.Builder addGsonConverterToRetrofit(TypeAdapterFactory typeAdapters) {

        Retrofit.Builder retrofitBuilder = RetrofitModule.provideRetrofit();

        if (typeAdapters != null) {

            GsonBuilder gsonBuilder = new GsonBuilder();
            gsonBuilder.registerTypeAdapterFactory(typeAdapters);

            GsonConverterFactory gsonConverterFactory = GsonConverterFactory.create(gsonBuilder.create());

            retrofitBuilder.addConverterFactory(gsonConverterFactory);
        } else {

            retrofitBuilder.addConverterFactory(GsonConverterFactory.create());
        }

        return retrofitBuilder;
    }

    public <S> S initializeServiceInstance(Context context, String baseUrl, TypeAdapterFactory typeAdapterFactory, Class<S> serviceClass) {

        Retrofit.Builder builder = addGsonConverterToRetrofit(typeAdapterFactory);

        builder.client(OkHttpModule.provideOkHttpClient(context)).baseUrl(baseUrl);

        Retrofit retrofit = builder.build();

        return retrofit.create(serviceClass);
    }
}
