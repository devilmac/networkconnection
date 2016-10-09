package com.colantoni.federico.networklibrary.retrofit;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;

/**
 * Class that provides the instance of {@link retrofit2.Retrofit.Builder}.
 */
public final class RetrofitModule {

    /**
     * Private constructor.
     */
    private RetrofitModule() {
    }

    /**
     * @return An instance of {@link retrofit2.Retrofit.Builder}.
     */
    public static Retrofit.Builder provideRetrofit() {

        final Retrofit.Builder builder = new Retrofit.Builder();

        return builder.addCallAdapterFactory(RxJavaCallAdapterFactory.create());
    }
}
