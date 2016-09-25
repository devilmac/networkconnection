package com.colanton.federico.networklibrary.retrofit;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;

public final class RetrofitModule {

    private RetrofitModule() {
    }

    public static Retrofit.Builder provideRetrofit() {

        final Retrofit.Builder builder = new Retrofit.Builder();

        return builder.addCallAdapterFactory(RxJavaCallAdapterFactory.create());
    }
}
