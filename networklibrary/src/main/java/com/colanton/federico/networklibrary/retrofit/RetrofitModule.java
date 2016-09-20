package com.colanton.federico.networklibrary.retrofit;

import android.content.Context;

import com.colanton.federico.networklibrary.retrofit.okhttp.OkHttpModule;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public final class RetrofitModule {

    private RetrofitModule() {
    }

    public static Retrofit provideRetrofit(Context context, String baseUrl) {

        final Retrofit.Builder builder = new Retrofit.Builder();

        builder.baseUrl(baseUrl).client(OkHttpModule.provideOkHttpClient(context));

        final GsonConverterFactory factory = GsonConverterFactory.create();

        return builder.addConverterFactory(factory).addCallAdapterFactory(RxJavaCallAdapterFactory.create()).build();
    }
}
