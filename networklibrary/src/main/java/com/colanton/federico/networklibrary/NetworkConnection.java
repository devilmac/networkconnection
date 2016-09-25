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

    private Retrofit.Builder initRetrofitInstance() {

        return RetrofitModule.provideRetrofit();
    }

    private Retrofit.Builder addTypeAdapterFactories(Retrofit.Builder builder, TypeAdapterFactory... typeAdapterFactories) {

        if (typeAdapterFactories.length > 0) {

            for (TypeAdapterFactory factory : typeAdapterFactories) {

                GsonBuilder gsonBuilder = new GsonBuilder();
                gsonBuilder.registerTypeAdapterFactory(factory);

                GsonConverterFactory gsonConverterFactory = GsonConverterFactory.create(gsonBuilder.create());

                builder.addConverterFactory(gsonConverterFactory);
            }
        } else {

            builder.addConverterFactory(GsonConverterFactory.create());
        }

        return builder;
    }

    public <S> S initializeServiceInstance(Context context, String baseUrl, Class<S> serviceClass, TypeAdapterFactory... typeAdapterFactories) {

        Retrofit.Builder builder = initRetrofitInstance();

        builder = addTypeAdapterFactories(builder, typeAdapterFactories);

        builder.client(OkHttpModule.provideOkHttpClient(context)).baseUrl(baseUrl);

        Retrofit retrofit = builder.build();

        return retrofit.create(serviceClass);
    }
}
